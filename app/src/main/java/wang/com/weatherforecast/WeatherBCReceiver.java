package wang.com.weatherforecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by 1 on 2018/5/23.
 */

public class WeatherBCReceiver extends BroadcastReceiver {

    private Handler handler = new Handler();
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "receive", Toast.LENGTH_LONG).show();
        String type = intent.getStringExtra("type");
        String value = intent.getStringExtra("value");
        if("location_changed".equals(type)){
            //TODO
//            MainActivity.this.queryWeather(value);
            handler.sendMessage()
        }else if("temper_unit_changed".equals(type)){
//            MainActivity.this.
        }else{
            Toast.makeText(context, "receive : unkwon type", Toast.LENGTH_LONG).show();
        }
    }
}
