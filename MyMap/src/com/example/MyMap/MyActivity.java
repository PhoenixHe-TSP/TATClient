package com.example.MyMap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/**
 * Created by ddz on 14-2-7
 * 此类是程序的主界面
 */
public class MyActivity extends Activity {

    private BMapManager mBMapMan = null;
    private MapView mMapView = null;
    private MapController mMapController;
    private GeoPoint point;
    private double latitude,longitude;
    private Record record;
    private TextView textView;
    private MyLocationOverlay myLocationOverlay;
    private LocationData locData;
    private boolean satellite,follow;
    private IntentFilter intentFilter = new IntentFilter("SendLocation");
    private int count = 0;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        record = (Record)getApplication();                    //获取Application
        Intent intent1 = new Intent(this,Location.class);
        startService(intent1);                                 //定位服务开启
        Intent intent2 = new Intent(this,SendMessage.class);
        startService(intent2);                                 //数据上传服务开启

        mBMapMan=new BMapManager(getApplication());          //获取百度地图入口
        mBMapMan.init("Cvph4eGC35hgGrjqtDt66ToT", null);
        setContentView(R.layout.main);
        mMapView=(MapView)findViewById(R.id.bmapsView);     //获取百度地图组件
        textView = (TextView)findViewById(R.id.TextView1);  //获取文本框

        latitude = record.getLatitude();                    //获取经纬度
        longitude = record.getLongitude();
        satellite = true;                                    //显示卫星地图
        follow = false;                                       //不跟随用户当前位置
        mMapView.setBuiltInZoomControls(true);               //可以缩放地图
        mMapView.setSatellite(satellite);

        mMapController=mMapView.getController();             //获得地图控制器
        point =new GeoPoint((int)(latitude* 1E6),(int)(longitude* 1E6));
        mMapController.setCenter(point);                     //设置地图中心点
        mMapController.setZoom(19);                           //设置缩放比例

        myLocationOverlay = new MyLocationOverlay(mMapView);   //获得用户图层
        locData = new LocationData();                           //新建用户点
        locData.latitude = latitude;                          //设置用户点位置
        locData.longitude = longitude;
        locData.direction = 2.0f;
        myLocationOverlay.setData(locData);                   //用户图层设置位置
        mMapView.getOverlays().add(myLocationOverlay);         //添加用户图层
        mMapView.refresh();                                      //更新地图
        mMapView.getController().animateTo(new GeoPoint((int)(locData.latitude*1e6),
                (int)(locData.longitude* 1e6)));               //跟踪位置点


        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {                 //注册广播接收器
                count++;
                latitude = record.getLatitude();                 //从application获得经纬度
                longitude = record.getLongitude();
                point =new GeoPoint((int)(latitude* 1E6),(int)(longitude* 1E6));
                locData.latitude = latitude;                    //更新用户图层点位置
                locData.longitude = longitude;
                locData.direction = 2.0f;
                myLocationOverlay.setData(locData);
                if (count == 1 || follow)                       //是否跟踪用户
                    mMapController.setCenter(point);
                mMapView.refresh();                              //更新地图
            }
        },intentFilter);
    }
    @Override
    protected void onDestroy(){
        mMapView.destroy();
        if(mBMapMan!=null){
            mBMapMan.destroy();
            mBMapMan=null;
        }
        super.onDestroy();
    }
    @Override
    protected void onPause(){
        mMapView.onPause();
        if(mBMapMan!=null){
            mBMapMan.stop();
        }
        super.onPause();
    }
    @Override
    protected void onResume(){
        mMapView.onResume();
        if(mBMapMan!=null){
            mBMapMan.start();
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem1 = menu.add(0,1,1,"切换地图模式");
        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
               satellite = !satellite;
                mMapView.setSatellite(satellite);
                return true;
            }
        });

        MenuItem menuItem2 = menu.add(0,2,2,"回到所在位置");
        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                point =new GeoPoint((int)(latitude* 1E6),(int)(longitude* 1E6));
                mMapController.setCenter(point);
                return true;
            }
        });
        MenuItem menuItem3 = menu.add(0,3,3,"切换跟踪模式");
        menuItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                follow = !follow;
                return true;
            }
        });
        MenuItem menuItem4 =  menu.add(0,4,4,"退出");
        menuItem4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                record.exit();
                return true;
            }
        });
        return true;
    }

}
