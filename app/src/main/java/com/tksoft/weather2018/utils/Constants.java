package com.tksoft.weather2018.utils;

public interface Constants {
    String BASE_WEATHER_URL =  "https://api.darksky.net/forecast/";
    String BASE_SEARCH_LOCATION = "https://maps.googleapis.com/maps/api/geocode/json?";
    String BASE_GET_ADDRESS_LOCATION = "";
    String DETECT_LOCATION = "DETECT_LOCATION";
    String LOCATION_DATA_EXTRA = "LOCATION_DATA_EXTRA";
    int REQUEST_SEARCH_LOCATION = 1002;

    public interface Bundle {
        String KEY_ID_ADDRESS = "KEY_ID_ADDRESS";
    }
}
