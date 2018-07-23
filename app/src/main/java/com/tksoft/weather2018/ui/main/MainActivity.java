package com.tksoft.weather2018.ui.main;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.common.api.Status;
import com.tksoft.weather2018.R;
import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.ui.base.BaseActivity;
import com.tksoft.weather2018.ui.controllers.LocationApiListener;
import com.tksoft.weather2018.ui.main.adapter.MainPagerAdapter;
import com.tksoft.weather2018.ui.search.SearchLocationActivity;
import com.tksoft.weather2018.ui.splash.HelperActivity;
import com.tksoft.weather2018.utils.Constants;
import com.utility.DebugLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvp, LocationApiListener {
    private static final int REQUEST_CHECK_SETTINGS = 1001;
    @BindView(R.id.view_pager_main)
    ViewPager viewPagerMain;
    @BindView(R.id.progress_bar_main)
    ProgressBar progressBarMain;

    private MainPagerAdapter mAdapter;
    private List<Address> listAddressLocation = new ArrayList<>();
    private MainPresenter mPresenter;
    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewCreated() {
        mContext = getContext();
        mPresenter = new MainPresenter(mContext, this);
        mPresenter.attachView(this);
        initViewPager();
        mPresenter.initData();
    }

    @Override
    public void setActionForViews() {
        //todo setAction ViewPager.
    }

    private void initViewPager() {
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), listAddressLocation, mContext);
        viewPagerMain.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showHelpActivity() {
        Intent intent = new Intent(this, HelperActivity.class);
        startActivity(intent);
    }

    @Override
    public void bindDataToViews(List<Address> listAddressLocation) {
        this.listAddressLocation.clear();
        this.listAddressLocation.addAll(listAddressLocation);
        DebugLog.logd(listAddressLocation.size());
        initViewPager();
    }

    @Override
    public void setPermissionLocation() {
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        if (progressBarMain != null) {
            progressBarMain.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        if (progressBarMain != null) {
            progressBarMain.setVisibility(View.GONE);
        }
    }

    @Override
    public void permissionGranted(String permissionName) {
        super.permissionGranted(permissionName);
        if (Manifest.permission.ACCESS_FINE_LOCATION.equalsIgnoreCase(permissionName)) {
            mPresenter.buildGPSGoogleApi();
        }
    }

    @Override
    public void permissionDenied() {
        if (listAddressLocation != null && listAddressLocation.isEmpty()) {
            Intent intent = new Intent(this, SearchLocationActivity.class);
            startActivityForResult(intent, Constants.REQUEST_SEARCH_LOCATION);
            return;
        }
        super.permissionDenied();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    mPresenter.startLocationService();
                    break;
                case Activity.RESULT_CANCELED:
                    if (listAddressLocation != null && listAddressLocation.isEmpty()) {
                        Intent intent = new Intent(this, SearchLocationActivity.class);
                        startActivityForResult(intent, Constants.REQUEST_SEARCH_LOCATION);
                        return;
                    }
                    break;
                default:
                    break;
            }
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_SEARCH_LOCATION) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void doConnectGPS() {
        mPresenter.startLocationService();
    }

    @Override
    public void doNotConnectGPS(Status status) {
        try {
            status.startResolutionForResult(this, REQUEST_CHECK_SETTINGS);
        } catch (IntentSender.SendIntentException e) {
            DebugLog.loge(e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

}
