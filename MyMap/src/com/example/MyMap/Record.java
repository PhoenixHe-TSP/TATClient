package com.example.MyMap;

import android.app.Application;

/**
 * Created by ddz on 14-2-6.
 * Record类继承自Application，记录这个app中需要记录的数据
 */
public class Record extends Application {
    private double latitude = 39.915;
    private double longitude = 116.404;

    /**
     * 设置经度
     * @param latitude : 经度
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * 返回经度
     * @return ：经度
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *设置纬度
     * @param longitude  纬度
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * 返回纬度
     * @return  纬度
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 退出整个应用
     */
    public void exit(){
        System.exit(0);
    }
}
