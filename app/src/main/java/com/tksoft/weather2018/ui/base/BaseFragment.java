/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.tksoft.weather2018.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tksoft.weather2018.utils.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by janisharali on 27/01/17.
 */

public abstract class BaseFragment extends Fragment implements BaseMvpView, BaseListener {
    public static final int REQUEST_CODE_SETTINGS = 101;

    public BaseActivity mActivity;

    private Callback callback;

    public abstract int getLayoutId();

    public abstract void onCreateView();

    public abstract void setActionForViews();

    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onCreateView();
        setActionForViews();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            mActivity.addListener(this);
        }
    }

    @Override
    public void requestPermission(String permission) {
        Dexter.withActivity(getActivity())
                .withPermission(permission)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        permissionGranted(response.getPermissionName());
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
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
        DialogUtils.showSettingsDialog(mActivity);
    }

    @Override
    public void permissionGranted(String permissionName) {
        //TODO implement feature.
    }

    public void pushFragment(boolean isCallBack, int containerViewId, Fragment newFragment){
        android.support.v4.app.FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, newFragment);
        if (isCallBack){
            fragmentTransaction.addToBackStack(null);
        }else {
            clearBackStack();
        }
        fragmentTransaction.commit();
    }

    public void addFragment(boolean isCallBack, int containerViewId, Fragment newFragment){
        android.support.v4.app.FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, newFragment);
        if (isCallBack){
            fragmentTransaction.addToBackStack(null);
        }else {
            clearBackStack();
        }
        fragmentTransaction.commit();
    }

    private void clearBackStack() {
        android.support.v4.app.FragmentManager manager = mActivity.getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            android.support.v4.app.FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void showLoading(String message) {
        if (mActivity != null) {
            mActivity.showLoading(message);
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void showAlertDialog(String message) {
        if (mActivity != null) {
            mActivity.showAlertDialog(message);
        }
    }

    @Override
    public void hideAlertDialog() {
        if (mActivity != null) {
            mActivity.hideAlertDialog();
        }
    }

    @Override
    public boolean toggleMenuNavigation() {
        return false;
    }

    @Override
    public boolean onBackFragment() {
        return false;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface Callback {

    }

}
