package wang.com.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> weatherItemAdapter;
    ListView weatherItemView;
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
    }
}
