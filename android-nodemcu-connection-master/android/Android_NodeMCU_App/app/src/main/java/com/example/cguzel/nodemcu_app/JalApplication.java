package com.example.cguzel.nodemcu_app;

import android.app.Application;

public class JalApplication extends Application {

    private double calibration = 0;
    private double phSensor = 7;
    private double phSensor_read = 7;
    private double oneptval=0 , twoptval =0;


    public double getCalibration() {
        return calibration;
    }

//    PhSensor implementation

    public void phSensorCalib (double read) {



        if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            this.calibration = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("oneptcalibval", "0"));
            oneptval = read + this.calibration;
            String str = Double.toString(oneptval);
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("oneptval", str).commit();
            phSensor_read = read;

        }

        if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

            phSensor_read = read;
            twoptval = twoptcalibrationvalue();
            String str = Double.toString(twoptval);
            getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).edit().putString("twoptval", str).commit();
        }

    }

//   function to evaluate two point calibrated value
    private double twoptcalibrationvalue() {
        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("Phpointer", 0) == 1) {
            double result = (((getphSensor_read() -Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("val1", "0")) ) * (Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("val4", "0")) - Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("val3", "0")))) / (Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("val2", "0")) - Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("val1", "0")))) + Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("val3", "0"));
            return result;
        }
        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("ECpointer", 0) == 1)
        {
            double result = (((getphSensor_read() -Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("val1", "0")) ) * (Double.parseDouble(getSharedPreferences("MY_ElectricalConductivity", MODE_PRIVATE).getString("val4", "0")) - Double.parseDouble(getSharedPreferences("MY_ElectricalConductivity", MODE_PRIVATE).getString("val3", "0")))) / (Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("val2", "0")) - Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("val1", "0")))) + Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("val3", "0"));
            return result;
        }
        if(getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("DO", 0) == 1)
        {
            double result = (((getphSensor_read() -Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("val1", "0")) ) * (Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("val4", "0")) - Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("val3", "0")))) / (Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("val2", "0")) - Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("val1", "0")))) + Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("val3", "0"));
            return result;
        }
        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("TEMP", 0) == 1)
        {
            double result = (((getphSensor_read() -Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("val1", "0")) ) * (Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("val4", "0")) - Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("val3", "0")))) / (Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("val2", "0")) - Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("val1", "0")))) + Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("val3", "0"));
            return result;
        }
        return 9999;
    }

//     ElectricalConductivity sensor implementation

    public void ElectricalconductivityCalib(double read) {



        if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            this.calibration = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("oneptcalibval", "0"));
            oneptval = read + this.calibration;
            String str = Double.toString(oneptval);
            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("oneptval", str).commit();
            phSensor_read = read;

        }

        if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

            phSensor_read = read;
            twoptval = twoptcalibrationvalue();
            String str = Double.toString(twoptval);
            getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).edit().putString("twoptval", str).commit();
        }

    }

//   Dissolved Oxygen sensor implementation

    public void DOCalib(double read) {



        if (getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            this.calibration = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("oneptcalibval", "0"));
            oneptval = read + this.calibration;
            String str = Double.toString(oneptval);
            getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).edit().putString("oneptval", str).commit();
            phSensor_read = read;
        }

        if (getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

            phSensor_read = read;
            twoptval = twoptcalibrationvalue();
            String str = Double.toString(twoptval);
            getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).edit().putString("twoptval", str).commit();
        }

    }

  //  Temperature sensor implementation

    public void TEMPCalib(double read) {


        if (getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            this.calibration = Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("oneptcalibval", "0"));
            oneptval = read + this.calibration;
            String str = Double.toString(oneptval);
            getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).edit().putString("oneptval", str).commit();
            phSensor_read = read;

        }

        if (getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

            phSensor_read = read;
            twoptval = twoptcalibrationvalue();
            String str = Double.toString(twoptval);
            getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).edit().putString("twoptval", str).commit();
        }

    }

//  All if conditions bundled up in functions
    public double PhVal()
    {
        if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("oneptval", "0"));

        } if (getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getInt("twoptkey", 0) == 1) {

            return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_PhSensor", MODE_PRIVATE).getString("twoptval", "0"));

    }
    return 9999;
    }

    public double ECVal()
    {
        if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("oneptval", "0"));

        } if (getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getInt("twoptkey", 0) == 1){

        return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_ElectricalConductivity", MODE_PRIVATE).getString("twoptval", "0"));

    }
    return 9999;
    }

    public double DOVal()
    {
        if (getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("oneptval", "0"));

        } if (getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getInt("twoptkey", 0) == 1){

        return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_DO", MODE_PRIVATE).getString("twoptval", "0"));

    }
    return 9999;
    }

    public double TEMPVal()
    {

        if (getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getInt("oneptkey", 0) == 1) {

            return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("oneptval", "0"));

        } if (getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getInt("twoptkey", 0) == 1){

        return Double.parseDouble(getSharedPreferences("MY_PREFERENCE_TEMP", MODE_PRIVATE).getString("twoptval", "0"));

    }
    return 9999;
    }

// Return the respective sensor value

    public double getSensorVal() {

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("Phpointer", 0) == 1) {

            return PhVal();
        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("ECpointer", 0) == 1) {

            return ECVal();
        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("DO", 0) == 1) {

            return DOVal();
        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("TEMP", 0) == 1) {

            return TEMPVal();
        }

        return 9999;
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

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("DO", 0) == 1) {

            DOCalib(read);
        }

        if (getSharedPreferences("MY_POINTERS", MODE_PRIVATE).getInt("TEMP", 0) == 1) {

            TEMPCalib(read);
        }


    }

}
