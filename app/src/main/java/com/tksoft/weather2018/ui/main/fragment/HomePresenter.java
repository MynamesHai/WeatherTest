package com.tksoft.weather2018.ui.main.fragment;

import android.content.Context;

import com.tksoft.weather2018.data.local.greendao.DatabaseHelper;
import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.data.model.weather.WeatherEntity;
import com.tksoft.weather2018.ui.base.BasePresenter;

public class HomePresenter extends BasePresenter<HomeMvp> {
    private Context mContext;
    private DatabaseHelper mDataHelper;

    public HomePresenter(Context mContext) {
        this.mContext = mContext;
        mDataHelper = new DatabaseHelper(mContext);
    }

    @Override
    public void attachView(HomeMvp mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getDataByIdAddress(long idAddress) {
        Address address = mDataHelper.getAddressById(idAddress);
        getMvpView().setDataForViews(address);
    }
}
