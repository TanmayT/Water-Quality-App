package com.example.cguzel.nodemcu_app;

import android.app.Application;

public class JalApplication extends Application {

    private double calibration = 0;
    private double phSensor = 7;
    private double phSensor_read = 7;
    private double dummyval = 0;


    public double getCalibration() {
        return calibration;
    }

    public void setCalibration(double calibration) {
        this.calibration = calibration;
    }

    public double getphSensor() {
        return phSensor ;
    }

    public double getphSensor_read() {
        return phSensor_read ;
    }

    public void setPhSensor(double read) {

//         Used Shared Preferences for saving and retrieving data
        dummyval = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("twoptcalibval", "0"));


        if(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("oneptkey",0) == 1 && getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("twoptkey", 0) == 0) {

            this.calibration = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("oneptcalibval", "0"));
            phSensor = read + this.calibration;
            phSensor_read = read;

        }

            if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("oneptkey",0) == 0 && getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

                phSensor = dummyval;

            }



    }

}
