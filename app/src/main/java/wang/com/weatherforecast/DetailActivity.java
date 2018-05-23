package wang.com.weatherforecast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends Activity {

    WeatherItem weatherItem;

    private TextView dateTextView ;
    private TextView weekTextView ;
    private TextView weatherTypeTextView ;
    private TextView maxTemperTextView ;
    private TextView minTemperTextView ;
    private TextView humidityTextView ;
    private TextView pressureTextView ;
    private TextView windTextView ;

    private ImageView shareImgView;
    private ImageView msgImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_detail);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,      // 注意顺序
                R.layout.activity_detail_title);

        weatherItem = (WeatherItem) getIntent().getSerializableExtra("WeatherItem");

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

        LayoutInflater factory = LayoutInflater.from(DetailActivity.this);
        View layout = factory.inflate(R.layout.activity_detail_title, null);
        shareImgView = (ImageView) layout.findViewById(R.id.shareImg);
        msgImgView = (ImageView) layout.findViewById(R.id.msgImg);

        shareImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this, "mmmmm", Toast.LENGTH_SHORT).show();
                SmsManager smsManager = SmsManager.getDefault();
                String content = weatherItem.toString();
                ArrayList<String> list = smsManager.divideMessage(content);
                for (int i = 0; i < list.size(); i++) {
                    smsManager.sendTextMessage("10086", null, list.get(i), null, null);
                }
            }
        });

        msgImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this, "sssss", Toast.LENGTH_SHORT).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, weatherItem.toString());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share to..."));
            }
        });



    }




}
