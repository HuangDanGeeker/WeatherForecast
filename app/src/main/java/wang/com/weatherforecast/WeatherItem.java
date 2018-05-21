package wang.com.weatherforecast;

import java.io.Serializable;

/**
 * Created by 1 on 2018/5/20.
 */

public class WeatherItem  implements Serializable {

    String date;
    String weatherType;
    String maxTemper;
    String minTemper;



    String Humidity;
    String wind;
    String pressure;


    String imgSymbol;

    public  WeatherItem(String data, String weatherType, String maxTemper, String minTemper, String imgSymbol, String Humidity,  String pressure, String wind){
        this.date = data;
        this.weatherType = weatherType;
        this.maxTemper = maxTemper;
        this.minTemper = minTemper;
        this.imgSymbol = imgSymbol;
        this.Humidity = Humidity;
        this.wind = wind;
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return this.date + weatherType + maxTemper + minTemper + imgSymbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getImgSymbol() {
        return imgSymbol;
    }

    public String getHumidity() {
        return Humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }

}
