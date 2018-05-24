package wang.com.weatherforecast;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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
    private ImageView imgSymbolImgView;

    private Intent intent;
    private WeatherItem weatherItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //TODO
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        weatherItem = (WeatherItem) getIntent().getSerializableExtra("WeatherItem");

        weekTextView = (TextView) findViewById(R.id.weekTextView);
        weatherTypeTextView = (TextView) findViewById(R.id.weatherTypeTextView);
        maxTemperTextView = (TextView) findViewById(R.id.maxTemperTextView);
        minTemperTextView = (TextView) findViewById(R.id.minTemperTextView);
        humidityTextView = (TextView) findViewById(R.id.humidityTextView);
        pressureTextView = (TextView) findViewById(R.id.pressureTextView);
        windTextView = (TextView) findViewById(R.id.windTextView);
        imgSymbolImgView = (ImageView) findViewById(R.id.imgSymbol);

        weekTextView.setText(weatherItem.getDate());
        weatherTypeTextView.setText(weatherItem.getWeatherType());
        maxTemperTextView.setText(weatherItem.getMaxTemper());
        minTemperTextView.setText(weatherItem.getMinTemper());
        humidityTextView.setText(weatherItem.getHumidity());
        pressureTextView.setText(weatherItem.getPressure());
        windTextView.setText(weatherItem.getWind());
        imgSymbolImgView.setImageResource(weatherItem.getImgSymbol());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.detail_activiity_more_operation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.sendToSMS){
            intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+"10086"));
            intent.putExtra("sms_body", weatherItem.toString()); startActivity(intent);
        }else if(itemId == R.id.shareTo){
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Share To ...");
            intent.putExtra(Intent.EXTRA_TEXT, weatherItem.toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent, getTitle()));
        }

        return super.onOptionsItemSelected(item);
    }
}
