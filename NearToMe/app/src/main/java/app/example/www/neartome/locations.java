package app.example.www.neartome;

import java.util.HashMap;

public class locations {
    double latitude;
    double longitude;
    HashMap friendsList;

    public locations(){

    }

    public locations(double lat,double lon){
        latitude=lat;
        longitude=lon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
