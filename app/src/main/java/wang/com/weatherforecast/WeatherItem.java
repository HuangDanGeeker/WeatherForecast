package wang.com.weatherforecast;

import java.io.Serializable;

/**
 * Created by 1 on 2018/5/20.
 */

public class WeatherItem  implements Serializable {

    String data;
    String weatherType;
    String maxTemper;
    String minTemper;
    Integer imhSymbol;

    public  WeatherItem(String data, String weatherType, String maxTemper, String minTemper, Integer imhSymbol){
        this.data = data;
        this.weatherType = weatherType;
        this.maxTemper = maxTemper;
        this.minTemper = minTemper;
        this.imhSymbol = imhSymbol;

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getMaxTemper() {
        return maxTemper;
    }

    public void setMaxTemper(String maxTemper) {
        this.maxTemper = maxTemper;
    }

    public String getMinTemper() {
        return minTemper;
    }

    public void setMinTemper(String minTemper) {
        this.minTemper = minTemper;
    }

    public Integer getImhSymbol() {
        return imhSymbol;
    }

    public void setImhSymbol(Integer imhSymbol) {
        this.imhSymbol = imhSymbol;
    }


}
