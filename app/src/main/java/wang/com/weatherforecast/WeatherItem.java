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
    Integer imgSymbol;
    String temperUnit;

    public  WeatherItem(String data, String weatherType, String maxTemper, String minTemper, String imgSymbol, String Humidity,  String pressure, String wind){
        this.date = data;
        this.weatherType = weatherType;
        this.maxTemper = maxTemper +" C";
        this.minTemper = minTemper+ " C";
//        this.imgSymbol = imgSymbol;
        this.Humidity = Humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.temperUnit = "Celsius";

        if(imgSymbol.equalsIgnoreCase("clouds")){
            this.imgSymbol = R.drawable.weather_clouds;
        }else  if(imgSymbol.equalsIgnoreCase("rain")){
            this.imgSymbol = R.drawable.weather_rain;
        }else if(imgSymbol.equalsIgnoreCase("clear")){
            this.imgSymbol = R.drawable.weather_clear;
        }else{
            this.imgSymbol = R.drawable.weather_clear;
        }
    }

    public WeatherItem updateTemperUnit(String value){
//        "Celsius";
//        "Kelvins";
//        "Fahrenheit";
        float maxTemperInt = Float.valueOf(this.maxTemper.substring(0, maxTemper.length()-1));
        float minTemperInt = Float.valueOf(this.minTemper.substring(0, maxTemper.length()-1));
        String unit = "";
        if(temperUnit.equals("Celsius")){
            if(value.equals("Fahrenheit")){
                unit = " F";
                maxTemperInt = maxTemperInt * 9/5 + 32;
                minTemperInt = minTemperInt * 9/5 + 32;
            }else if(value.equals("Kelvins")){
                unit = " K";
                maxTemperInt = maxTemperInt + 273;
                minTemperInt = minTemperInt + 273;
            }else{
                return this;
            }
        }else if(temperUnit.equals("Kelvins")){
            if(value.equals("Fahrenheit")){
                unit = " F";
                maxTemperInt = (maxTemperInt - 273) * 9/5 + 32;
                maxTemperInt = (maxTemperInt - 273) * 9/5 + 32;
            }else if(value.equals("Celsius")){
                unit = " C";
                maxTemperInt = maxTemperInt - 273;
                minTemperInt = minTemperInt - 273;
            }else{
                return this;
            }
        }else if(temperUnit.equals("Fahrenheit")){
            if(value.equals("Celsius")){
                unit = " C";
                maxTemperInt = (maxTemperInt - 32) * 5/9;
                minTemperInt = (minTemperInt - 32) * 5/9;
            }else if(value.equals("Kelvins")){
                unit = " K";
                maxTemperInt = (maxTemperInt - 32) * 5/9 - 273;
                minTemperInt = (minTemperInt - 32) * 5/9 - 273;
            }else{
                return this;
            }
        }
        this.maxTemper = String.valueOf(maxTemperInt);
        this.minTemper = String.valueOf(minTemperInt);
        this.maxTemper = String.valueOf(maxTemperInt).substring(0, this.maxTemper.lastIndexOf('.')+2) + unit;
        this.minTemper = String.valueOf(minTemperInt).substring(0, this.minTemper.lastIndexOf('.')+2) + unit;
        this.temperUnit = value;
        return this;
    }

    @Override
    public String toString() {
        return "date : " + date + "\nweather : "+weatherType + "\nTemper unit : " + temperUnit + "\nmaxTemper : " + maxTemper + "\nminTemper : " + minTemper ;
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

    public Integer getImgSymbol() {
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
