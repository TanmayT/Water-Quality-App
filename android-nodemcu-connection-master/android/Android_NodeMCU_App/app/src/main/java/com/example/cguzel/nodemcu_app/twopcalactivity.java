package com.example.cguzel.nodemcu_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class twopcalactivity extends AppCompatActivity {

    private EditText Lowrefsense,Highrefsense;
    private JalApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twopointcalib);

        app = ((JalApplication) this.getApplication());
        Lowrefsense = (EditText) findViewById(R.id.lowrefsense);
        Highrefsense = (EditText) findViewById(R.id.highrefsense);

        Intent intent = getIntent();
    }

    public void twobuttonClick (View view)
    {
        double val1,val2,val3,val4,result;

            val1= app.getphSensor_read();
            val2= app.getphSensor_read();
            val3= Double.parseDouble(Lowrefsense.getText().toString());
            val4= Double.parseDouble(Highrefsense.getText().toString());

            //caliberated value
            result = (((app.getphSensor_read()-val1)*(val4-val3))/(val2-val1))+val3;
            String str = Double.toString(result);
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("twoptcalibval", str).commit();
    }
}
