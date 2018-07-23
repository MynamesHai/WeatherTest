package com.tksoft.weather2018;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tksoft.weather2018.data.local.ApplicationModules;

public class BaseApplication extends Application {
    public static BaseApplication instance;

    public static BaseApplication getInstance(){
        if (null == instance){
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ApplicationModules.getInstances().initData(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ApplicationModules.getInstances().closeDatabase();
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }
}
