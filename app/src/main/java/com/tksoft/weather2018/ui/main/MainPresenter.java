package com.tksoft.weather2018.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;

import com.tksoft.weather2018.data.eventbus.Event;
import com.tksoft.weather2018.data.eventbus.MessageEvent;
import com.tksoft.weather2018.data.local.greendao.DatabaseHelper;
import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.data.model.address.Geometry;
import com.tksoft.weather2018.services.LocationService;
import com.tksoft.weather2018.ui.base.BasePresenter;
import com.tksoft.weather2018.ui.controllers.LocationApiListener;
import com.tksoft.weather2018.ui.controllers.LocationManager;
import com.tksoft.weather2018.utils.Constants;
import com.tksoft.weather2018.utils.WeatherUtils;
import com.utility.DebugLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainMvp> {
    private Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private List<Address> listAddressLocation = new ArrayList<>();
    private LocationManager mLocationManager;

    public MainPresenter(Context mContext, LocationApiListener listener) {
        this.mContext = mContext;
        mDatabaseHelper = new DatabaseHelper(mContext);
        mLocationManager = new LocationManager(mContext, listener);
    }

    @Override
    public void attachView(MainMvp mvpView) {
        super.attachView(mvpView);
        EventBus.getDefault().register(this);
        mContext.registerReceiver(receiverLocation, new IntentFilter(Constants.DETECT_LOCATION));
    }

    @Override
    public void detachView() {
        super.detachView();
        EventBus.getDefault().unregister(this);
        stopLocationService();
        try {
            mContext.unregisterReceiver(receiverLocation);
        } catch (Exception e) {
            DebugLog.loge(e.toString());
        }
    }

    public void startLocationService() {
        getMvpView().showLoading();
        stopLocationService();
        mContext.startService(new Intent(mContext, LocationService.class));
    }

    private void stopLocationService() {
        mContext.stopService(new Intent(mContext, LocationService.class));
    }

    private BroadcastReceiver receiverLocation = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = (Location) intent.getExtras().get(Constants.LOCATION_DATA_EXTRA);
            String addressLocation = WeatherUtils.getAddressString(location.getLatitude(), location.getLongitude(), mContext);
            Address address = new Address();
            address.formatted_address = addressLocation;
            address.isCurrentAddress = true;
            Geometry geometry = new Geometry();
            com.tksoft.weather2018.data.model.address.Location locationAddress = new com.tksoft.weather2018.data.model.address.Location();
            locationAddress.lat = location.getLatitude();
            locationAddress.lng = location.getLongitude();
            geometry.location = locationAddress;
            address.geometry = geometry;
            address.setId((long) 0);
            mDatabaseHelper.addAddress(address);
            if (getMvpView() != null) {
                getMvpView().hideLoading();
            }
        }
    };

    public void initData() {
        if (getMvpView() == null) {
            return;
        }
        boolean isFirstInstallApps = mDatabaseHelper.getFirstInstallApp();
        if (isFirstInstallApps) {
            setupData();
        } else {
            mDatabaseHelper.setEnableCurrentLocation(true);
            getMvpView().showHelpActivity();
        }
    }

    private void setupData() {
        boolean isEnableCurrentLocation = mDatabaseHelper.isEnableCurrentLocation();
        if (isEnableCurrentLocation) {
            listAddressLocation.clear();
            listAddressLocation.addAll(mDatabaseHelper.getListAddressLocation());
            getMvpView().setPermissionLocation();
        } else {
            listAddressLocation.clear();
            listAddressLocation.addAll(mDatabaseHelper.getListAddressWithOutCurrentLocation());
        }
        getMvpView().bindDataToViews(listAddressLocation);
    }

    public void buildGPSGoogleApi() {
        mLocationManager.buildGPSGoogleApi();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (messageEvent.event == Event.EVENT_INSERT_ADDRESS) {
            setupData();
        }
        if (messageEvent.event == Event.EVENT_REQUEST_PERMISSION) {
            if (getMvpView() == null) {
                return;
            }
            getMvpView().setPermissionLocation();
        }
    }
}
