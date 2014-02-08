package com.example.MyMap;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by ddz on 14-2-7.
 * 此服务用于实时向服务器传送当前位置
 */
public class SendMessage extends Service {
    private IntentFilter intentFilter = new IntentFilter("SendLocation");
    private double latitude,longitude;
    private Record record;
    private String address = "115.219.53.48";
    private int port = 8888;

    @Override
    public void onCreate() {
        super.onCreate();
        record = (Record)getApplication();                 //获取Application
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                latitude = record.getLatitude();         //获得经度
                longitude = record.getLongitude();       //获得纬度
                Thread thread = new Thread(new Send());    //防止和gui线程阻塞新开辟一个线程
                thread.start();
            }
        }, intentFilter);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 新开辟一个任务执行上传过程
     */
    class Send implements Runnable {

        @Override
        public void run() {
            try {
                Socket socket = new Socket(address, port);
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeDouble(latitude);
                output.writeDouble(longitude);
                record.exit();
                output.close();
            } catch (Exception e) {

            }
        }
    }
}

