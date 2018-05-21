package wang.com.weatherforecast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<WeatherItem> weatherItemAdapter;
    ListView weatherItemView;
    Button menuBtn;
    private Intent intent;
    private static final int NOTIFY_WEATHER = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<WeatherItem> weatherItems = new ArrayList<>();
        weatherItems.add(new WeatherItem("22","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItemAdapter = new WeatherItemAdapter(MainActivity.this, R.layout.weather_item, weatherItems);
        weatherItemView = (ListView) findViewById(R.id.weather_item_view);
        weatherItemView.setAdapter(weatherItemAdapter);
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

        notifyWeather("bad weather");
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
}
