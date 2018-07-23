package com.tksoft.weather2018.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tksoft.weather2018.utils.DialogUtils;
import com.utility.DebugLog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView {

    private static final int REQUEST_CODE_SETTINGS = 101;


    public abstract int getLayoutId();

    public abstract void onViewCreated();

    public abstract void setActionForViews();

    private Unbinder unbinder;
    private BaseListener mCallBack;

    public void addListener(BaseListener mCallBack) {
        this.mCallBack = mCallBack;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        onViewCreated();
        setActionForViews();
        setupWindowAnimations();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void requestPermission(String permission) {
        Dexter.withActivity(this)
                .withPermission(permission)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        DebugLog.logd("onPermissionGranted");
                        permissionGranted(response.getPermissionName());
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        DebugLog.logd("onPermissionDenied :: 1::" + response.isPermanentlyDenied());
                        if (!response.isPermanentlyDenied()) {
                            permissionDenied();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void permissionDenied() {
        DebugLog.logd("onPermissionDenied :: 2");
        //DialogUtils.showSettingsDialog(this);
    }

    @Override
    public void permissionGranted(String permissionName) {
        //TODO implement feature.
    }

    public void pushFragment(boolean isCallBack, int containerViewId, Fragment newFragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, newFragment);
        if (isCallBack) {
            fragmentTransaction.addToBackStack(null);
        } else {
            clearBackStack();
        }
        fragmentTransaction.commit();
    }

    public void addFragment(boolean isCallBack, int containerViewId, Fragment newFragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, newFragment);
        if (isCallBack) {
            fragmentTransaction.addToBackStack(null);
        } else {
            clearBackStack();
        }
        fragmentTransaction.commit();
    }

    protected void clearBackStack() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            android.support.v4.app.FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showLoading(String message) {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showAlertDialog(String message) {
        hideAlertDialog();
        if (message == null || message.trim().isEmpty()) {
            return;
        }

    }

    @Override
    public void hideAlertDialog() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        hideLoading();
        hideAlertDialog();
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
    }
}
