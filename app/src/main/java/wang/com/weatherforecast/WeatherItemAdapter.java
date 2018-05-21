package wang.com.weatherforecast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 1 on 2018/5/20.
 */

public class WeatherItemAdapter extends ArrayAdapter {

    private final int resourceId;

    public WeatherItemAdapter(Context context, int textViewResourceId, List<WeatherItem> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WeatherItem item = (WeatherItem) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        ImageView imgSymbol = (ImageView) view.findViewById(R.id.imgSymbol);//获取该布局内的图片视图
        TextView data = (TextView) view.findViewById(R.id.date);//获取该布局内的文本视图
        TextView maxTemper = (TextView) view.findViewById(R.id.maxTemper);//获取该布局内的文本视图
        TextView minTemper= (TextView) view.findViewById(R.id.minTemper);//获取该布局内的文本视图
        TextView weatherType = (TextView) view.findViewById(R.id.weatherType);//获取该布局内的文本视图
        imgSymbol.setImageResource(item.getImgSymbol());//为图片视图设置图片资源
        data.setText(item.getDate());
        maxTemper.setText(item.getMaxTemper());
        minTemper.setText(item.getMinTemper());
        weatherType.setText(item.getWeatherType());
        return view;
    }
}
