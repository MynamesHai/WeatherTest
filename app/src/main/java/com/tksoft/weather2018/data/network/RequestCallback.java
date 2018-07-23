package com.tksoft.weather2018.data.network;


public interface RequestCallback {
    void onSuccess(RequestApi TAG, String response);

    void onFailure(RequestApi TAG, String msg);

}