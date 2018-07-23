package com.tksoft.weather2018.data.local.greendao;

import android.content.Context;

import com.tksoft.weather2018.data.eventbus.Event;
import com.tksoft.weather2018.data.eventbus.MessageEvent;
import com.tksoft.weather2018.data.local.ApplicationModules;
import com.tksoft.weather2018.data.local.preferences.PreferenceKeys;
import com.tksoft.weather2018.data.local.preferences.PreferencesHelper;
import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.data.model.address.AddressDao;
import com.tksoft.weather2018.data.model.address.DaoSession;
import com.tksoft.weather2018.data.model.address.Geometry;
import com.tksoft.weather2018.data.model.address.GeometryDao;
import com.tksoft.weather2018.data.model.address.Location;
import com.tksoft.weather2018.data.model.address.LocationDao;
import com.utility.DebugLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.Query;

import java.util.List;

public class DatabaseHelper {
    private Context mContext;
    private PreferencesHelper mPreferencesHelper;
    private DaoSession mDaoSession;

    public DatabaseHelper(Context context) {
        this.mContext = context;
        mPreferencesHelper = new PreferencesHelper(context);
        mDaoSession = ApplicationModules.getInstances().getDaoSectioṇ̣̣();
    }

    public void addAddress(Address address) {
        try {
            if (address == null) {
                return;
            }
            if (isExistAddress(address)){
                return;
            }
            LocationDao locationDao = mDaoSession.getLocationDao();
            GeometryDao geometryDao = mDaoSession.getGeometryDao();
            AddressDao addressDao = mDaoSession.getAddressDao();
            Location location = address.geometry.location;
            Long locationId = locationDao.insert(location);
            Geometry geometry = address.geometry;
            geometry.setLocationId(locationId);
            Long geometryId = geometryDao.insert(geometry);
            address.setGeometryID(geometryId);
            addressDao.insertOrReplace(address);
            EventBus.getDefault().post(new MessageEvent(Event.EVENT_INSERT_ADDRESS));
        } catch (Exception e) {
            DebugLog.loge(e);
        }
    }

    public boolean isExistAddress(Address address) {
        try {
            boolean isExist = false;
            List<Address> listAddress = mDaoSession.getAddressDao().queryBuilder().build().list();
            for (int i = 0; i < listAddress.size(); i++) {
                if (address.formatted_address.equalsIgnoreCase(listAddress.get(i).formatted_address)){
                    isExist = true;
                    break;
                }
            }
            return isExist;
        } catch (Exception e) {
            DebugLog.loge(e);
            return false;
        }
    }

    public void removeAddress(Address address) {
        if (address == null) {
            return;
        }
        mDaoSession.getAddressDao().delete(address);
        EventBus.getDefault().post(new MessageEvent(Event.EVENT_DELETE_ADDRESS));
    }

    public List<Address> getListAddressLocation() {
        return mDaoSession.getAddressDao().queryBuilder().list();
    }

    public Address getAddressById(long idAddress) {
        return mDaoSession.getAddressDao().load(idAddress);
    }

    public List<Address> getListAddressWithOutCurrentLocation() { 
        Query<Address> query = mDaoSession.getAddressDao().queryBuilder().where(AddressDao.Properties.IsCurrentAddress.eq(false)).build();
        return query.list();
    }

    public void setFirstInstallApp(boolean isFirstInstall) {
        mPreferencesHelper.setBooleanSPR(PreferenceKeys.FIRST_INSTALL_APPS, isFirstInstall, mContext);
        EventBus.getDefault().post(new MessageEvent(Event.EVENT_REQUEST_PERMISSION));
    }

    public boolean getFirstInstallApp() {
        return mPreferencesHelper.getBooleanSPR(PreferenceKeys.FIRST_INSTALL_APPS, mContext);
    }

    public void setEnableCurrentLocation(boolean isEnable) {
        mPreferencesHelper.setBooleanSPR(PreferenceKeys.KEY_ENABLE_CURRENT_LOCATION, isEnable, mContext);
    }

    public boolean isEnableCurrentLocation() {
        return mPreferencesHelper.getBooleanSPR(PreferenceKeys.KEY_ENABLE_CURRENT_LOCATION, mContext);
    }

    public void setDistanceWeather(String distance) {
        mPreferencesHelper.saveString(PreferenceKeys.KEY_DISTANCE_WEATHER, distance, mContext);
        //todo notify distance for screens.
    }

    public String getDistanceWeather() {
        return mPreferencesHelper.getStringSPR(PreferenceKeys.KEY_DISTANCE_WEATHER, mContext);
    }

    public void setTemperatureWeather(String typeTemperature) {
        mPreferencesHelper.saveString(PreferenceKeys.KEY_TEMPERATURE_WEATHER, typeTemperature, mContext);
        //todo notify change temperature for screens.
    }

    public String getTemperatureWeather() {
        return mPreferencesHelper.getStringSPR(PreferenceKeys.KEY_TEMPERATURE_WEATHER, mContext);
    }


}
