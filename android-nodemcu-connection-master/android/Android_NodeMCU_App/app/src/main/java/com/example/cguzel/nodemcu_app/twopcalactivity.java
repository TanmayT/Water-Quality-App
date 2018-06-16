package com.example.cguzel.nodemcu_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class twopcalactivity extends AppCompatActivity {

    private EditText Lowsense,Highsense,Lowrefsense,Highrefsense;
    private JalApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twopointcalib);

        app = ((JalApplication) this.getApplication());
        Lowsense = (EditText) findViewById(R.id.lowsense);
        Highsense = (EditText) findViewById(R.id.highsense);
        Lowrefsense = (EditText) findViewById(R.id.lowrefsense);
        Highrefsense = (EditText) findViewById(R.id.highrefsense);

        Intent intent = getIntent();
    }

    public void twobuttonClick (View view)
    {
        double val1,val2,val3,val4,result;

            val1= Double.parseDouble(Lowsense.getText().toString());
            val2= Double.parseDouble(Highsense.getText().toString());
            val3= Double.parseDouble(Lowrefsense.getText().toString());
            val4= Double.parseDouble(Highrefsense.getText().toString());
            result = (((app.getphSensor_read()-val1)*(val4-val3))/(val2-val1))+val3;


            app.settwocaliberation(result);


    }
}
