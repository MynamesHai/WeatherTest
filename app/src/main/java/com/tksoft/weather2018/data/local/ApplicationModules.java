package com.tksoft.weather2018.data.local;

import android.content.Context;

import com.tksoft.weather2018.data.model.address.DaoMaster;
import com.tksoft.weather2018.data.model.address.DaoSession;
import com.utility.DebugLog;

import org.greenrobot.greendao.database.Database;


public class ApplicationModules {
    public static ApplicationModules mInstances;
    public static final String DATABASE_NAME = "WEATHER_DATABASE";

    private DaoSession mDaoSession;
    private Database mDatabase;

    public static ApplicationModules getInstances() {
        if (null == mInstances) {
            mInstances = new ApplicationModules();
        }
        return mInstances;
    }

    public void initData(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME);
        mDatabase = helper.getWritableDb();
        mDaoSession = new DaoMaster(mDatabase).newSession();
        //mDaoSession.getWeatherEntityDao().hasKey()
    }

    public DaoSession getDaoSectioṇ̣̣() {
        return mDaoSession;
    }

    public void closeDatabase() {
        try {
            mDaoSession.clear();
            mDatabase.close();
            mDatabase = null;
        } catch (Exception e) {
            DebugLog.loge(e.toString());
        }

    }

}
