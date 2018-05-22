package wang.com.weatherforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                    //TODO
                }
            }
        });

        locationArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tempertureArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
