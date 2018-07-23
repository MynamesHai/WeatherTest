package com.tksoft.weather2018.services;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.LocationSource;
import com.tksoft.weather2018.utils.Constants;

public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationSource.OnLocationChangedListener, LocationListener {
    private Context context;
    private GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        buildGoogleApiClient();
        registerReceiver(receiverLocation, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void connectGoogleApiClient() {
        disconnectGoogleApiClient();
        if (mGoogleApiClient == null) {
            buildGoogleApiClient();
        }
        mGoogleApiClient.connect();
    }

    private void disconnectGoogleApiClient() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        connectGoogleApiClient();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiverLocation);
        disconnectGoogleApiClient();
        super.onDestroy();
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (checkAccessLocationPermission(context)) {
            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(10000); // interval 10 seconds
            mLocationRequest.setFastestInterval(1000); // the fastest rate in milliseconds at which your app can handle location updates
            mLocationRequest.setNumUpdates(1); // number of location updates
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {

        }
        //intentLocation();
    }

    private void intentLocation(){
        Intent intent = new Intent(Constants.DETECT_LOCATION);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
        sendBroadcast(intent);
    }

    public boolean checkAccessLocationPermission(Context context) {
        int hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        return !(hasAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED || hasAccessCoarseLocationPermission != PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        disconnectGoogleApiClient();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            mLastLocation = location;
            intentLocation();
        }
    }

    BroadcastReceiver receiverLocation = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isLocationEnable()) {
                connectGoogleApiClient();
            }
        }
    };

    public boolean isLocationEnable() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
