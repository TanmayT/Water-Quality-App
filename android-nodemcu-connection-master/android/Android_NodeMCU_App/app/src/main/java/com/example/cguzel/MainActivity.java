package com.example.cguzel.nodemcu_app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by cguzel on 26.04.2016.
 */

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private EditText ipAddress, sensorRead, millisRead, led_info;
    private Button ledOn, ledOff, start;
    private int ss = 0, ff = 0;
    public  String led_val = "0";
    public boolean bb = false;

    private Handler handler_data = new Handler();

    public void foo() {
        handler_data.postDelayed(runnable_data, 100);
    }

    private Runnable runnable_data = new Runnable() {
        @Override
        public void run() {
            String serverAdress = ipAddress.getText().toString();
            Check connection = new Check(serverAdress);
            connection.execute();

            if(bb == true)
                handler_data.postDelayed(runnable_data, 500);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddress = (EditText) findViewById(R.id.edt_ip);
        ledOn = (Button) findViewById(R.id.btn_ledOn);
        ledOff = (Button) findViewById(R.id.btn_ledOff);
        sensorRead = (EditText) findViewById(R.id.sensor);
        millisRead = (EditText) findViewById(R.id.millis);
        start = (Button) findViewById(R.id.fetch);
        led_info = (EditText) findViewById(R.id.led_status);

    }

    /** When the button clicks this method   executes**/
    public void buttonClick(View view) {

        if (ipAddress.getText().toString().equals(""))
            Toast.makeText(MainActivity.this, "Please enter the ip address...", Toast.LENGTH_SHORT).show();

        else  if(view == start){
            Log.d("Listen", ipAddress.getText().toString());

            ff = 1;
            if(ss == 0){
                Toast.makeText(MainActivity.this, "Checking connection....", Toast.LENGTH_SHORT).show();
                String serverAdress = ipAddress.getText().toString();
                Check connection = new Check(serverAdress);
                connection.execute();

            }
            else{
                Toast.makeText(MainActivity.this, "Already Connected!", Toast.LENGTH_SHORT).show();
            }

         }
        else if(ss == 1 && view == ledOn) led_val = "1";
        else if(ss == 1 && view == ledOff) led_val = "0";

    }

    public void graphButtonClick(View view){

    }
    private class HttpRequestTask extends AsyncTask<String, Void, String> {

        private String serverAdress;
        private String serverResponse = "";

        public HttpRequestTask(String serverAdress) {
            this.serverAdress = serverAdress;
        }

        @Override
        protected String doInBackground(String... params) {

            final String url = "http://" + serverAdress + "/com/push/" + "?led=" + led_val;
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet getRequest = new HttpGet();
                getRequest.setURI(new URI(url));
                HttpResponse response = client.execute(getRequest);

                InputStream inputStream = null;
                inputStream = response.getEntity().getContent();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream));

                serverResponse = bufferedReader.readLine();
                inputStream.close();

            } catch (URISyntaxException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (IllegalArgumentException e){
                e.printStackTrace();;
                serverResponse = e.getMessage();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            String temp  = serverResponse;
            String data[] = temp.split("/");
            //Log.d("VROOM", serverResponse);
            if(data[0].equals("0"))
                led_info.setText("Led pin is now :" + "Off");
            else
                led_info.setText("Led pin is now :" + "On");

            sensorRead.setText("Sensor :" + data[1] + " mm");
            millisRead.setText("Millis :" + data[2]);
        }

        @Override
        protected void onPreExecute() {

        }
    }

    private class Check extends AsyncTask<String, Void, String>{

        private String serverAdress;
        public Check(String serverAdress){
            this.serverAdress = serverAdress;
        }

        @Override
        protected void onPreExecute() {
            //Setup precondition to execute some task
        }

        @Override
        protected String doInBackground(String... params) {

            Socket s = null;
            try
            {
                s = new Socket(serverAdress, 8000);
                bb = true;
            }
            catch (Exception e)
            {
                bb = false;
            }
            finally
            {
                if(s != null)
                    try {s.close();}
                    catch(Exception e){}
            }

            return "dummy";
        }

        @Override
        protected void onPostExecute(String s) {
            //Log.d("msg", "POST Execute!!!");
            if(bb){
                if(ss == 0){
                    Toast.makeText(MainActivity.this, "Connected!!", Toast.LENGTH_SHORT).show();
                    ss = 1;
                    foo();
                }

                serverAdress = ipAddress.getText().toString() + ":" + "8000";
                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                requestTask.execute();

            }
            else{
                //Log.d("msg", "heree!!!");
                if(ss == 1){
                    Toast.makeText(MainActivity.this, "Connection lost :(", Toast.LENGTH_SHORT).show();
                    handler_data.removeCallbacks(runnable_data);
                    ss = 0;
                    ff = 0;
                }
                else if(ss == 0 && ff == 1){
                    Toast.makeText(MainActivity.this, "Failed to connect :(", Toast.LENGTH_SHORT).show();
                    ff = 0;
                }
            }
        }
    }


}
