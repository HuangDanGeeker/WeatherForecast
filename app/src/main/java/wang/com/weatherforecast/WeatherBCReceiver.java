package wang.com.weatherforecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 1 on 2018/5/23.
 */

public class WeatherBCReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "receive", Toast.LENGTH_LONG).show();
        Log.w("receiver", "received");
    }
}
