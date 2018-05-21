package wang.com.weatherforecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView dateTextView ;
    private TextView weekTextView ;
    private TextView weatherTypeTextView ;
    private TextView maxTemperTextView ;
    private TextView minTemperTextView ;
    private TextView humidityTextView ;
    private TextView pressureTextView ;
    private TextView windTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //TODO
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        WeatherItem weatherItem = (WeatherItem) getIntent().getSerializableExtra("WeatherItem");

        weekTextView = (TextView) findViewById(R.id.weekTextView);
        weatherTypeTextView = (TextView) findViewById(R.id.weatherTypeTextView);
        maxTemperTextView = (TextView) findViewById(R.id.maxTemperTextView);
        minTemperTextView = (TextView) findViewById(R.id.minTemperTextView);
        humidityTextView = (TextView) findViewById(R.id.humidityTextView);
        pressureTextView = (TextView) findViewById(R.id.pressureTextView);
        windTextView = (TextView) findViewById(R.id.windTextView);

        weekTextView.setText(weatherItem.getDate());
        weatherTypeTextView.setText(weatherItem.getWeatherType());
        maxTemperTextView.setText(weatherItem.getMaxTemper());
        minTemperTextView.setText(weatherItem.getMinTemper());
        humidityTextView.setText(weatherItem.getHumidity());
        pressureTextView.setText(weatherItem.getPressure());
        windTextView.setText(weatherItem.getWind());
    }

}
