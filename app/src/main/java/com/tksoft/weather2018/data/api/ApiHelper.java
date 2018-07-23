package com.tksoft.weather2018.data.api;

import android.content.Context;

import com.android.volley.Request;
import com.tksoft.weather2018.BuildConfig;
import com.tksoft.weather2018.R;
import com.tksoft.weather2018.data.model.weather.WeatherEntity;
import com.tksoft.weather2018.data.network.BaseRequestQueue;
import com.tksoft.weather2018.data.network.RequestApi;
import com.tksoft.weather2018.data.network.RequestCallback;
import com.tksoft.weather2018.data.network.VolleyRequest;
import com.tksoft.weather2018.utils.Constants;

public class ApiHelper {
    private Context mContext;

    public ApiHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void getWeatherApiCall(double lat, double lng, RequestCallback requestCallback) {
        StringBuilder builder = new StringBuilder(Constants.BASE_WEATHER_URL);
        builder.append(mContext.getString(R.string.weather_api_key));
        builder.append(lat);
        builder.append(lng);
        VolleyRequest volleyRequest = new VolleyRequest(Request.Method.GET, builder.toString(),
                requestCallback, RequestApi.API_WEATHER_DATA);
        BaseRequestQueue.getInstance().addToRequestQueue(volleyRequest);
    }

    public void searchLocationApiCall(String paramsSearch, RequestCallback requestCallback) {
        StringBuilder builder = new StringBuilder(Constants.BASE_SEARCH_LOCATION);
        builder.append("address=" + paramsSearch);
        builder.append("&key=" + BuildConfig.API_SEARCH_KEY);
        String url = builder.toString();
        VolleyRequest volleyRequest = new VolleyRequest(Request.Method.GET, url,
                requestCallback, RequestApi.API_SEARCH_LOCATION);
        BaseRequestQueue.getInstance().clearCache();
        BaseRequestQueue.getInstance().addToRequestQueue(volleyRequest);
    }

}
