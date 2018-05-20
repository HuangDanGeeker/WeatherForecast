package wang.com.weatherforecast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1 on 2018/5/21.
 */

public class LocationMap {
    private Map<String, LatLng> location = new HashMap<>();

    public LocationMap(){
        location.put("北京", new LatLng(116.422091,39.914714));
        location.put("南京", new LatLng(118.794373,32.064897));
        location.put("长沙", new LatLng(112.985139,28.246855));
        location.put("广州", new LatLng(113.267981,23.127094));
    }

    public LatLng getLalo(String cityName){

        return location.get(cityName);
    }

    private class LatLng{
        private double lat;
        private double lng;

        public LatLng(double lat, double lng){
            this.lat = lat;
            this.lng = lng;
        }
    }
}
