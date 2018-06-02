package com.example.cguzel.nodemcu_app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SmsReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.i("Receiver", "Broadcast received: " + action);

        if(action.equals("my.action.string")){
            String state = intent.getExtras().getString("extra");

        }
    }
}
