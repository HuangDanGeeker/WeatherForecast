package wang.com.weatherforecast;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout locationArea;
    private LinearLayout tempertureArea;
    private TextView selectedCityTextView;
    private TextView selectedTUTextView;
    private TextView enableNotifyTextView;
    private CheckBox enableNotifyChkBox;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //获取前端的各种控件
        locationArea = (LinearLayout) findViewById(R.id.locationArea);
        tempertureArea = (LinearLayout) findViewById(R.id.tempertureArea);
        selectedCityTextView = (TextView) findViewById(R.id.selectedCityTextView);
        selectedTUTextView = (TextView) findViewById(R.id.selectedTUTextView);
        enableNotifyTextView = (TextView) findViewById(R.id.enableNotifyTextView);
        enableNotifyChkBox = (CheckBox) findViewById(R.id.enableNotifyRadoBtn);

        //为组件添加事件监听
        enableNotifyChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    enableNotifyTextView.setText("Enabled");
                }else{
                    enableNotifyTextView.setText("Disabled");
                }
                //TODO send broadcast
            }
        });

        locationArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog(R.layout.bottom_dialog_localtion, "location_changed");
            }
        });

        tempertureArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog(R.layout.bottom_dialog_temper_unit, "temper_unit_changed");
            }
        });


    }

    public void displayDialog(final Integer resouce, final String dialogType){
        final Dialog dialog = new Dialog(SettingsActivity.this);
        LinearLayout dialogCompnent = (LinearLayout) LayoutInflater.from(SettingsActivity.this).inflate(resouce, null);
        dialog.setContentView(dialogCompnent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //添加事件监听
        for(int i = 0; i < dialogCompnent.getChildCount(); i++){
            dialogCompnent.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getBaseContext(), "ssss", Toast.LENGTH_SHORT).show();
                    intent = new Intent();
                    intent.setAction("com.WeatherForcast.settingChanged");
                    intent.putExtra("type", dialogType);
                    intent.putExtra("value", ((TextView)v).getText().toString());
                    SettingsActivity.this.sendBroadcast(intent);
                    dialog.dismiss();
                }
            });
        }

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        dialogCompnent.measure(0, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        lp.height = dialogCompnent.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        window.setAttributes(lp);
        dialog.show();
    }


}
