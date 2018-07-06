package com.example.cguzel.nodemcu_app;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//  Variables to point which activity is initiated

    private int Phpointer, ECpointer, DO, TEMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void CameraClick (View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }
        // display location updates
        public void getLocation(View view){

            Intent intent = new Intent(MainActivity.this,LocationActivity.class);
            startActivity(intent);
        }

        public void PhSensorCLick(View view)
        {
            Phpointer = 1; ECpointer = 0; DO = 0; TEMP = 0;
//          Shared Preferences used for storing values
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
