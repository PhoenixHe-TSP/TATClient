package com.example.MyMap;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by ddz on 14-2-7
 * 此服务用于实时定位当前位置并广播出去.
 */
public class Location extends Service {
    private LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();
    private double latitude;
    private double longitude;
    private Record record;

    @Override
    public void onCreate() {
        super.onCreate();
        record = (Record)getApplication();                           //获取Application
        mLocationClient = new LocationClient(getApplicationContext());  //获取定位端
        mLocationClient.setAK("Cvph4eGC35hgGrjqtDt66ToT");           //注册key
        mLocationClient.registerLocationListener( myListener );         //注册监听器
        latitude = record.getLatitude();
        longitude = record.getLongitude();

        LocationClientOption option = new LocationClientOption();            //获取定位设置
        option.setOpenGps(true);                                            //开启gps
        option.setAddrType("all");
        option.setCoorType("bd09ll");
        option.setScanSpan(1100);
        option.disableCache(true);
        option.setPoiNumber(5);    
        option.setPoiDistance(1000);
        option.setPoiExtraInfo(true); 
        mLocationClient.setLocOption(option);                             //设置

        mLocationClient.start();                                          //开始定位
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return ;
            latitude = location.getLatitude();                             //获取当前经度
            longitude = location.getLongitude();                           //获取当前纬度
            record.setLatitude(latitude);                                  //更新经度
            record.setLongitude(longitude);                               //更新纬度
            Intent intent = new Intent("SendLocation");
            sendBroadcast(intent);                                            //广播这条消息



        }
        public void onReceivePoi(BDLocation poiLocation) {
            /*
                同onReceiveLocation
             */
            if (poiLocation == null){
                return ;
            }
            latitude = poiLocation.getLatitude();
            longitude = poiLocation.getLongitude();
            record.setLatitude(latitude);
            record.setLongitude(longitude);
            Intent intent = new Intent("SendLocation");
            sendBroadcast(intent);
        }
    }
}
