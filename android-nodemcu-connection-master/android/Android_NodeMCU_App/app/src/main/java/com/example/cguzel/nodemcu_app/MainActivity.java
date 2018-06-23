package com.example.cguzel.nodemcu_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int Phpointer,ECpointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void PhSensorCLick(View view)
        {
            Phpointer = 1; ECpointer = 0;
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("Phpointer", Phpointer).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("ECpointer", ECpointer).commit();
            Intent intent = new Intent(MainActivity.this, SensorActivity.class);
            startActivity(intent);
        }

        public void ElectricalConductivityCLick (View view)
        {

            Phpointer = 0; ECpointer = 1;
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("Phpointer", Phpointer).commit();
            getSharedPreferences("MY_POINTERS", MODE_PRIVATE).edit().putInt("ECpointer", ECpointer).commit();
            Intent intent = new Intent(MainActivity.this, SensorActivity.class);
            startActivity(intent);
        }

}
