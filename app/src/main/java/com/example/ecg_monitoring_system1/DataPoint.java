package com.example.ecg_monitoring_system1;

public class DataPoint {
    int xValue, yValue;

    public DataPoint(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public DataPoint() {
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    }

}
