package wang.com.weatherforecast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements WeatherBCReceiver.SettingMessageHandler{
    private ArrayAdapter<WeatherItem> weatherItemAdapter;
    List<WeatherItem> weatherItems;
    ListView weatherItemView;
    private Intent intent;
    private static final int NOTIFY_WEATHER = 201;
    private static final int RESPONSE_WEATHER = 101;
    private static final int SETTING_CHANGED_LOCATION = 301;
    private static final int SETTING_CHANGED_TEMPER_UNIT = 302;
    private static final int SETTING_CHANGED_NOTIFYCATION = 303;
    private String defaultCity = "长沙";
    private Handler handler;

    private TextView dateTextView ;
    private TextView weatherTypeTextView ;
    private TextView maxTemperTextView ;
    private TextView minTemperTextView ;
    private TextView humidityTextView ;
    private TextView pressureTextView ;
    private TextView windTextView ;
    private ImageView imgSymbolImgView;
    private TextView cityNameTextView;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //获取组件
        weatherItemView = (ListView) findViewById(R.id.weather_item_view);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        weatherTypeTextView = (TextView) findViewById(R.id.weatherTypeTextView);
        maxTemperTextView = (TextView) findViewById(R.id.maxTemperTextView);
        minTemperTextView = (TextView) findViewById(R.id.minTemperTextView);
        imgSymbolImgView = (ImageView) findViewById(R.id.imgSymbol);
        cityNameTextView= (TextView) findViewById(R.id.cityNameTextView);

        //为ListView设置ItemClick监听器
        weatherItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WeatherItem weatherItem = (WeatherItem) MainActivity.this.weatherItemAdapter.getItem(position);
//                Toast.makeText(getBaseContext(), item.getDate(), Toast.LENGTH_LONG).show();
                if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    intent = new Intent(getBaseContext(), DetailActivity.class);
                    intent.putExtra("WeatherItem", weatherItem);
                    startActivity(intent);
                } else {
                    //获取组件
                    setupDisplayArea(weatherItem);
                }

            }
        });


        WeatherBCReceiver bcReceiver = new WeatherBCReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.WeatherForcast.settingChanged");
        registerReceiver(bcReceiver, intentFilter);

        //查询当前城市的天气
        queryWeather(defaultCity);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case RESPONSE_WEATHER:
                        weatherItems = (List<WeatherItem>) msg.obj;
                        weatherItemAdapter = new WeatherItemAdapter(MainActivity.this, R.layout.weather_item, weatherItems);
                        weatherItemView.setAdapter(weatherItemAdapter);
                        setupDisplayArea(weatherItems.get(0));
                        weatherItemAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    public void notifyWeather(String weatherType, Integer iconId){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("WeatherForecast")
                .setContentText("today's weather is " + weatherType)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(iconId)
                .build();
        manager.notify(NOTIFY_WEATHER, notification);
    }

    public void queryWeather(String cityName){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建HttpClient对象
                HttpClient httpClient = new DefaultHttpClient();
               //创建请求对象
                HttpGet httpGet = new HttpGet("http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=870ea3f91bdb99ca599ca042eca9eb52&q=" + new LocationMap().getCityCode(cityName));
                try{
                    //请求
                    HttpResponse response = httpClient.execute(httpGet);
                    //检查响应状态
                    if (response.getStatusLine().getStatusCode() == 200) {
                       //取出数据
                        HttpEntity entity = response.getEntity();
                        String responseEntityStr = EntityUtils.toString(entity, "utf-8");
                        Gson gson = new Gson();
                        ResponseEntity responseEntityJson = gson.fromJson(responseEntityStr, ResponseEntity.class);
                        List responseList = responseEntityJson.getList();
                        List<WeatherItem> weatherList = new ArrayList(15);
                        WeatherItem tempItem = null;
                        LinkedTreeMap<String, Object> innerItem = null;
                        for (int i = 0; i < 15; i++) {
                            innerItem = (LinkedTreeMap<String, Object>)responseList.get(i);
                            tempItem = new WeatherItem(
                                    (String)innerItem.get("dt_txt"),
                                    ((LinkedTreeMap<String, String>)((ArrayList)innerItem.get("weather")).get(0)).get("main"),
                                    new Double(((LinkedTreeMap<String, Double>)innerItem.get("main")).get("temp_max") - 273.15 ).toString().substring(0,4),
                                    new Double(((LinkedTreeMap<String, Double>)innerItem.get("main")).get("temp_min") - 273.15 ).toString().substring(0,4),
                                    ((LinkedTreeMap<String, String>)((ArrayList)innerItem.get("weather")).get(0)).get("main"),
                                    ((LinkedTreeMap<String, Double>)innerItem.get("main")).get("humidity").toString(),
                                    ((LinkedTreeMap<String, Double>)innerItem.get("main")).get("pressure").toString(),
                                    ((LinkedTreeMap<String, Double>)innerItem.get("wind")).get("speed").toString()
                            );

                            weatherList.add(tempItem);
                        }
                        Message message = new Message();
                        message.what = RESPONSE_WEATHER;
                        message.obj = weatherList;
                        handler.sendMessage(message);
                    }else{
                    }
                }catch (Exception e){
                    Log.d("ss", e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_activiity_more_operation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.mapLocationItem){
            intent = new Intent();
            intent.setData(Uri.parse("baidumap://map/show?center=40.057406655722,116.29644071728&zoom=11&traffic=on&bounds=37.8608310000,112.5963090000,42.1942670000,118.9491260000"));
            startActivity(intent);
        }else if(itemId == R.id.settingsItem){
            intent = new Intent(getBaseContext(), SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTemperUnits(String value){
        //java 1.8 failed
//        weatherItems.stream().forEach(a -> a.updateTemperUnit(value));
        WeatherItem item = null;
        for(int i = 0; i < weatherItems.size(); i++){
            item = ((WeatherItem)weatherItems.get(i)).updateTemperUnit(value);
            weatherItems.remove(i);
            weatherItems.add(i, item);
        }
        weatherItemAdapter = new WeatherItemAdapter(MainActivity.this, R.layout.weather_item, weatherItems);
        weatherItemView.setAdapter(weatherItemAdapter);
        weatherItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleMessage(int type, String value) {
        switch (type){
            case SETTING_CHANGED_LOCATION:
                defaultCity = value;
                queryWeather(value);
                Toast.makeText(MainActivity.this, "SETTING_CHANGED_LOCATION", Toast.LENGTH_SHORT).show();
                break;
            case SETTING_CHANGED_TEMPER_UNIT:
                setTemperUnits(value);
                Toast.makeText(MainActivity.this, "SETTING_CHANGED_TEMPER_UNIT", Toast.LENGTH_SHORT).show();
                break;
            case SETTING_CHANGED_NOTIFYCATION:
                boolean isChecked = Boolean.valueOf(value);
                if(isChecked){
                    if (weatherItems != null) {
                        WeatherItem item = weatherItems.get(0);
                        if (item != null){
                            notifyWeather(item.getWeatherType(), item.getImgSymbol());
                        }
                    }
                }

                Toast.makeText(MainActivity.this, "SETTING_CHANGED_NOTIFYCATION", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this, "UNKWON MESSAGE", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setupDisplayArea(WeatherItem weatherItem){
        //与方向无关的组件-设置属性
        dateTextView.setText(weatherItem.getDate());
        weatherTypeTextView.setText(weatherItem.getWeatherType());
        maxTemperTextView.setText(weatherItem.getMaxTemper());
        minTemperTextView.setText(weatherItem.getMinTemper());
        imgSymbolImgView.setImageResource(weatherItem.getImgSymbol());
        //方向相关组件设置
        if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            cityNameTextView.setText(defaultCity);
        } else {
            //横屏
            //获取组件
            humidityTextView = (TextView) findViewById(R.id.humidityTextView);
            pressureTextView = (TextView) findViewById(R.id.pressureTextView);
            windTextView = (TextView) findViewById(R.id.windTextView);

            //设置参数
            humidityTextView.setText(weatherItem.getHumidity());
            pressureTextView.setText(weatherItem.getPressure());
            windTextView.setText(weatherItem.getWind());
        }
    }
}
