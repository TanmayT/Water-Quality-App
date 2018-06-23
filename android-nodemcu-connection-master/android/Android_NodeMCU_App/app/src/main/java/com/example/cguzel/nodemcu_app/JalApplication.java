package com.example.cguzel.nodemcu_app;

import android.app.Application;

public class JalApplication extends Application {

    private double calibration = 0;
    private double phSensor = 7;
    private double phSensor_read = 7;
    private double dummyval = 0;
    private double oneptval=0 , twoptval =0;


    public double getCalibration() {
        return calibration;
    }

    public void phSensorCalib(double read) {
        dummyval = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("twoptcalibval", "0"));

        if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            this.calibration = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("oneptcalibval", "0"));
            oneptval = read + this.calibration;
            String str = Double.toString(oneptval);
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("oneptval", str).commit();
            phSensor_read = read;

        }

        if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

            twoptval = dummyval;
            String str = Double.toString(twoptval);
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("twoptval", str).commit();
        }

    }

    public void ElectricalconductivityCalib(double read) {
        dummyval = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("twoptcalibval", "0"));

        if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            this.calibration = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("oneptcalibval", "0"));
            oneptval = read + this.calibration;
            String str = Double.toString(oneptval);
            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("oneptval", str).commit();
            phSensor_read = read;

        }

        if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

            twoptval = dummyval;
            String str = Double.toString(twoptval);
            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("twoptval", str).commit();
        }

    }

    public double getphSensor() {
        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("Phpointer", 0) == 1) {

            if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

                return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("oneptval", "0"));
            } if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("twoptkey", 0) == 1){
               return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("twoptval", "0"));
            }
        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("ECpointer", 0) == 1) {

            if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

                return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("oneptval", "0"));
            } if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("twoptkey", 0) == 1){
                return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("twoptval", "0"));
            }
        }
        return 13;
    }

    public double getphSensor_read() {
        return phSensor_read;
    }

    public void setValue(double read) {

//         Used Shared Preferences for saving and retrieving data
        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("Phpointer", 0) == 1) {
            phSensorCalib(read);

        }
        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("ECpointer", 0) == 1) {
            ElectricalconductivityCalib(read);
        }


    }

}
