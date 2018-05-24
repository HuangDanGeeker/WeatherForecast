package wang.com.weatherforecast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1 on 2018/5/21.
 */

public class LocationMap {
    private Map<String, CityDetail> location = new HashMap<>();

    public LocationMap(){
        location.put("北京", new CityDetail(2038349, "Beijing Shi",116.422091,39.914714));
        location.put("南京", new CityDetail(1799963, "Nanjing", 118.794373,32.064897));
        location.put("长沙", new CityDetail(1815577, "ChangSha", 112.985139,28.246855));
        location.put("广州", new CityDetail(1809858, "Guangzhou", 113.267981,23.127094));
    }

    public CityDetail getCityDetail(String cityName){

        return location.get(cityName);
    }
    public String getCityCode(String cityName){

        return String.valueOf(location.get(cityName).getCode());
    }



    private class CityDetail {
        private int id;
        private String code;
        private double lat;
        private double lng;

        public CityDetail(int id, String code, double lat, double lng){
            this.code = code;
            this.id = id;
            this.lat = lat;
            this.lng = lng;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }



    }
}
