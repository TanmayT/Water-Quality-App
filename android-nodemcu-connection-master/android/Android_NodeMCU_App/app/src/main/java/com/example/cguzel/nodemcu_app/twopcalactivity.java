package com.example.cguzel.nodemcu_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

//   Two point calibration activity
public class twopcalactivity extends AppCompatActivity {

    private EditText Lowrefsense, Highrefsense;
    private JalApplication app;
    private double val1,val2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twopointcalib);

        app = ((JalApplication) this.getApplication());
        Lowrefsense = (EditText) findViewById(R.id.lowrefsense);
        Highrefsense = (EditText) findViewById(R.id.highrefsense);

    }

//     Reading at lower end of measurement scale
    public void RawLowCLick (View view)
    {
        Check1 connection = new Check1("10.42.0.1");
        connection.execute();
    }



//     Reading at the higher end of measurement scale
    public void RawHighCLick (View view)
    {
        Check connection = new Check("10.42.0.1");
        connection.execute();
    }
// Corresponding to RawHigh value
    private class Check extends AsyncTask<String, Void, String> {

        private String serverAdress;

        public Check(String serverAdress) {
            this.serverAdress = serverAdress;
        }

        @Override
        protected void onPreExecute() {
            //Setup precondition to execute some task
        }

        @Override
        protected String doInBackground(String... params) {

            Socket s = null;
            try {
                s = new Socket(serverAdress, 8080);
            } catch (Exception e) {
            } finally {
                if (s != null)
                    try {
                        s.close();
                    } catch (Exception e) {
                    }

            }

            return "dummy";
        }

        @Override
        protected void onPostExecute(String s) {
            //Log.d("msg", "POST Execute!!!");


            serverAdress = "10.42.0.1" + ":" + "8080";
            HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
            requestTask.execute();


        }
    }
//   corresponding to RawLow value
    private class Check1 extends AsyncTask<String, Void, String> {

        private String serverAdress;

        public Check1(String serverAdress) {
            this.serverAdress = serverAdress;
        }

        @Override
        protected void onPreExecute() {
            //Setup precondition to execute some task
        }

        @Override
        protected String doInBackground(String... params) {

            Socket s = null;
            try {
                s = new Socket(serverAdress, 8085);
            } catch (Exception e) {
            } finally {
                if (s != null)
                    try {
                        s.close();
                    } catch (Exception e) {
                    }

            }

            return "dummy";
        }

        @Override
        protected void onPostExecute(String s) {
            //Log.d("msg", "POST Execute!!!");


            serverAdress = "10.42.0.1" + ":" + "8085";
            HttpRequestTask1 requestTask = new HttpRequestTask1(serverAdress);
            requestTask.execute();


        }
    }

// Corresponding to RawHigh value
    private class HttpRequestTask extends AsyncTask<String, Void, String> {

        private String serverAdress;
        private String serverResponse = "";

        public HttpRequestTask(String serverAdress) {
            this.serverAdress = serverAdress;
        }

        @Override
        protected String doInBackground(String... params) {

            final String url = "http://" + serverAdress + "/com/push/" + "?led=" + "0";
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
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("Test", "the acivity still runs in the background");
            String temp = serverResponse;
            String data[] = temp.split("/");
            //Log.d("VROOM", serverResponse);
            val2 = Double.parseDouble(data[1]);

//            Intent i = new Intent("my.action").putExtra("data", data[1]);
//            context.sendBroadcast(i);
        }

        @Override
        protected void onPreExecute() {

        }
    }
//  Corresponding to RawLow value
    private class HttpRequestTask1 extends AsyncTask<String, Void, String> {

        private String serverAdress;
        private String serverResponse = "";

        public HttpRequestTask1(String serverAdress) {
            this.serverAdress = serverAdress;
        }

        @Override
        protected String doInBackground(String... params) {

            final String url = "http://" + serverAdress + "/com/push/" + "?led=" + "0";
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
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("Test", "the acivity still runs in the background");
            String temp = serverResponse;
            String data[] = temp.split("/");
            val1 = Double.parseDouble(data[1]);
        }

        @Override
        protected void onPreExecute() {

        }
    }



//     Two point calibration
    public void twobuttonClick(View view) {
        double  val3, val4;

//      val3, val4 are reference values
        val3 = Double.parseDouble(Lowrefsense.getText().toString());
        val4 = Double.parseDouble(Highrefsense.getText().toString());
        String str1 = Double.toString(val1);
        String str2 = Double.toString(val2);
        String str3 = Double.toString(val3);
        String str4 = Double.toString(val4);

//      storing calibration settings for each sensor in different shared preference
        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("Phpointer", 0) == 1) {


            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("val1", str1).commit();
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("val2", str2).commit();
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("val3", str3).commit();
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("val4", str4).commit();
        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("ECpointer", 0) == 1) {


            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("val1", str1).commit();
            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("val2", str2).commit();
            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("val3", str3).commit();
            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("val4", str4).commit();
        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("DO", 0) == 1) {


            getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).edit().putString("val1", str1).commit();
            getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).edit().putString("val2", str2).commit();
            getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).edit().putString("val3", str3).commit();
            getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).edit().putString("val4", str4).commit();

        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("TEMP", 0) == 1) {


            getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).edit().putString("val1", str1).commit();
            getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).edit().putString("val2", str2).commit();
            getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).edit().putString("val3", str3).commit();
            getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).edit().putString("val4", str4).commit();

        }

    }

}

