package com.example.cguzel.nodemcu_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int Phpointer,ECpointer,DO,TEMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void PhSensorCLick(View view)
        {
            Phpointer = 1; ECpointer = 0; DO = 0; TEMP = 0;
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("Phpointer", Phpointer).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("ECpointer", ECpointer).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("DO", DO).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("TEMP", TEMP).commit();
            Intent intent = new Intent(MainActivity.this, SensorActivity.class);
            startActivity(intent);
        }

        public void ElectricalConductivityCLick (View view)
        {

            Phpointer = 0; ECpointer = 1; DO = 0; TEMP = 0;
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("Phpointer", Phpointer).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("ECpointer", ECpointer).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("DO", DO).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("TEMP", TEMP).commit();
            Intent intent = new Intent(MainActivity.this, SensorActivity.class);
            startActivity(intent);
        }

    public void DissolvedOxygenClick (View view)
    {

        Phpointer = 0; ECpointer = 0; DO = 1;TEMP = 0;
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("Phpointer", Phpointer).commit();
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("ECpointer", ECpointer).commit();
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("DO", DO).commit();
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("TEMP", TEMP).commit();
        Intent intent = new Intent(MainActivity.this, SensorActivity.class);
        startActivity(intent);
    }

    public void TemperatureClick (View view)
    {

        Phpointer = 0; ECpointer = 0; DO = 0;TEMP = 1;
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("Phpointer", Phpointer).commit();
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("ECpointer", ECpointer).commit();
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("DO", DO).commit();
        getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("TEMP", TEMP).commit();
        Intent intent = new Intent(MainActivity.this, SensorActivity.class);
        startActivity(intent);

    }

}
