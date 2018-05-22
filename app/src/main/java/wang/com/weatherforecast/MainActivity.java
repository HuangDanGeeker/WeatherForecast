package wang.com.weatherforecast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<WeatherItem> weatherItemAdapter;
    ListView weatherItemView;
    Button menuBtn;
    private Intent intent;
    private static final int NOTIFY_WEATHER = 201;
    private static final int RESPONSE_WEATHER = 101;
    private String defaultCity = "长沙";

    private Handler handler;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weatherItemView = (ListView) findViewById(R.id.weather_item_view);


        //为ListView设置ItemClick监听器
        weatherItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WeatherItem item = (WeatherItem) MainActivity.this.weatherItemAdapter.getItem(position);
//                Toast.makeText(getBaseContext(), item.getDate(), Toast.LENGTH_LONG).show();
                intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("WeatherItem", item);
                startActivity(intent);
            }
        });


        menuBtn = (Button) findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "点击", Toast.LENGTH_LONG).show();
                intent = new Intent();
                //TODO
                intent.setData(Uri.parse("baidumap://map/show?center=40.057406655722,116.29644071728&zoom=11&traffic=on&bounds=37.8608310000,112.5963090000,42.1942670000,118.9491260000"));
                startActivity(intent);
            }
        });

//        notifyWeather("bad weather");
        queryWeather(defaultCity);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case RESPONSE_WEATHER:
                        List<WeatherItem> weatherItems = (List<WeatherItem>) msg.obj;
                        weatherItemAdapter = new WeatherItemAdapter(MainActivity.this, R.layout.weather_item, weatherItems);
                        weatherItemView.setAdapter(weatherItemAdapter);
                        weatherItemAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    public void notifyWeather(String weatherType){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("WeatherForecast")
                .setContentText("today's weather is " + weatherType)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
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
                HttpGet httpGet = new HttpGet("http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=870ea3f91bdb99ca599ca042eca9eb52&q=Guangzhou,1809858");
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
                                    (String)innerItem.get("dt_txt"),
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
        getMenuInflater().inflate(R.menu.more_operation, menu);
        return true;
    }
}
