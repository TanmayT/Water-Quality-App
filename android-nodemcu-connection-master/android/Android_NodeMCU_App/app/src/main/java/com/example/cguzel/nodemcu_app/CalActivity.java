package com.example.cguzel.nodemcu_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Random;

public class CalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        Intent intent = getIntent();
        //String message = intent.getStringExtra(com.example.cguzel.nodemcu_app.MainActivity.EXTRA_MESSAGE);

    }

}