package com.example.cguzel.nodemcu_app;

import android.app.Application;

public class JalApplication extends Application{
    private int calibration;

    public int getCalibration() {
        return calibration;
    }

    public void setCalibration(int calibration) {
        this.calibration = calibration;
    }
}