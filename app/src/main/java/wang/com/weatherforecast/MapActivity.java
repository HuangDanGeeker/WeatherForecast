package wang.com.weatherforecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends AppCompatActivity {

    public LocationClient mLocationClient = null;
    private BDAbstractLocationListener myListener = new MyLocationListener();
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);

        //获取位置信息
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5*60*1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();

        //百度地图控件
        mMapView = (MapView) findViewById(R.id.bdmapView);
        mBaiduMap = mMapView.getMap();
        //调用BaiduMap对象的setOnMarkerDragListener方法设置Marker拖拽的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
            }
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
            }
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
            }
        });


    }


    private class MyLocationListener extends BDAbstractLocationListener {
        String originCity = null;
        boolean isFirstLocation = true;
        @Override
        public void onReceiveLocation(BDLocation location){

//            double latitude = location.getLatitude();    //获取纬度信息
//            double longitude = location.getLongitude();    //获取经度信息
//            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
//            String coorType = location.getCoorType();
//            int errorCode = location.getLocType();

//            String addr = location.getAddrStr();    //获取详细地址信息
//            String country = location.getCountry();    //获取国家
//            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            if(null == originCity) {
                originCity = city;
            }else if(!city.equals(originCity)){
                Toast.makeText(getBaseContext(), city, Toast.LENGTH_SHORT).show();
            }

//            String district = location.getDistrict();    //获取区县
//            String street = location.getStreet();    //获取街道信息

//            mBaiduMap.setMyLocationEnabled(true);
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(100).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//
//            // 设置定位数据
//            mBaiduMap.setMyLocationData(locData);
//            BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
//                    .fromResource(R.drawable.icon_geo);
//            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
//            mBaiduMap.setMyLocationConfiguration(config);
//            mBaiduMap.setMyLocationEnabled(false);

            /*
                在地图上绘制点
             */
            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());

            //构建Marker图标

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_geo);

            //构建MarkerOption，用于在地图上添加Marker

            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap)
                    .draggable(true)
                    .perspective(true);

            //在地图上添加Marker，并显示

            Marker marker = (Marker) (mBaiduMap.addOverlay(option));



            //初始化 当前地点为中间点
            if(isFirstLocation){
                //获取经纬度
                LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                //mBaiduMap.setMapStatus(status);//直接到中间
                mBaiduMap.animateMapStatus(status);//动画的方式到中间
                isFirstLocation = false;
            }
        }
    }
}
