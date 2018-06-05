package com.example.cguzel.nodemcu_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalActivity extends AppCompatActivity {

    private EditText phValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        phValue = (EditText) findViewById(R.id.pH_val);
        Intent intent = getIntent();
        //String message = intent.getStringExtra(com.example.cguzel.nodemcu_app.MainActivity.EXTRA_MESSAGE);

    }

    public void buttonClick(View view) {
        double val;
        try{
            val = Double.parseDouble(phValue.getText().toString());
            if(true){
                JalApplication app = ((JalApplication) this.getApplication());
                app.setCalibration(val - app.getphSensor_read());
                Log.d("Check", String.valueOf(app.getCalibration()));
            }
            else{
                Toast.makeText(CalActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            Toast.makeText(CalActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

}