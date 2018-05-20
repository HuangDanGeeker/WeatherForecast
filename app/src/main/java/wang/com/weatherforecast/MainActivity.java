package wang.com.weatherforecast;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> weatherItemAdapter;
    ListView weatherItemView;
    Button menuBtn;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<WeatherItem> weatherItems = new ArrayList<>();
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
        weatherItems.add(new WeatherItem("1","1","1","1", R.drawable.icon_geo));
        weatherItemAdapter = new WeatherItemAdapter(MainActivity.this, R.layout.weather_item, weatherItems);
        weatherItemView = (ListView) findViewById(R.id.weather_item_view);
        weatherItemView.setAdapter(weatherItemAdapter);


        menuBtn = (Button) findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "点击", Toast.LENGTH_LONG).show();
                intent = new Intent();
                //TODO
                intent.setData(Uri.parse(" baidumap://map/show?center=40.057406655722,116.29644071728&zoom=11&traffic=on&bounds=37.8608310000,112.5963090000,42.1942670000,118.9491260000"));
                startActivity(intent);

            }
        });
    }
}
