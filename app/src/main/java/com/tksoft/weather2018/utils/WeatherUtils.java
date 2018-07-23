package com.tksoft.weather2018.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.tksoft.weather2018.R;

import java.util.List;
import java.util.Locale;

public class WeatherUtils {
    public static String getAddressString(double lat, double lng, Context context) {
        String mAddress = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        int countAddress = 0;
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses != null) {
                android.location.Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                strReturnedAddress.append(returnedAddress.getAddressLine(0));
                mAddress = strReturnedAddress.toString();
            }
        } catch (Exception e) {
            mAddress = context.getString(R.string.lbl_current_location);
        }
        return mAddress;
    }

}
