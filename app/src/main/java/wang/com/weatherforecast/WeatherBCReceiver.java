package wang.com.weatherforecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 1 on 2018/5/23.
 */

public class WeatherBCReceiver extends BroadcastReceiver {


    private static final int SETTING_CHANGED_LOCATION = 301;
    private static final int SETTING_CHANGED_TEMPER_UNIT = 302;
    private SettingMessageHandler handler;

    public WeatherBCReceiver(SettingMessageHandler handler){
        this.handler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int type = intent.getIntExtra("type", 0);
        if(type == SETTING_CHANGED_LOCATION || SETTING_CHANGED_TEMPER_UNIT == type){
            handler.handleMessage(type, intent.getStringExtra("value"));
        }else{
            Toast.makeText(context, "receive : unkwon type", Toast.LENGTH_LONG).show();
        }
    }

    public void setMEssageHandler(SettingMessageHandler handler){
        this.handler = handler;
    }

    public interface SettingMessageHandler{
        public void handleMessage(int type, String value);
    }
}
