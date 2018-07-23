package com.tksoft.weather2018.ui.search;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.tksoft.weather2018.BuildConfig;
import com.tksoft.weather2018.R;
import com.tksoft.weather2018.data.api.ApiHelper;
import com.tksoft.weather2018.data.local.greendao.DatabaseHelper;
import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.data.model.address.ResultSearch;
import com.tksoft.weather2018.data.network.BaseRequestQueue;
import com.tksoft.weather2018.data.network.RequestApi;
import com.tksoft.weather2018.data.network.RequestCallback;
import com.tksoft.weather2018.data.network.VolleyRequest;
import com.tksoft.weather2018.ui.base.BasePresenter;
import com.tksoft.weather2018.utils.Constants;
import com.tksoft.weather2018.utils.Utils;
import com.utility.DebugLog;
import com.utility.UtilsLib;

import java.lang.annotation.Target;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class SearchPresenter extends BasePresenter<SearchMvp> implements RequestCallback {
    private Context mContext;
    private ApiHelper mApiHelper;
    private DatabaseHelper mDatabaseHelper;

    public SearchPresenter(Context context){
        mContext = context;
        mApiHelper = new ApiHelper(mContext);
        mDatabaseHelper = new DatabaseHelper(mContext);
    }

    public void searchLocationAddress(String paramsSearch) {
        if (TextUtils.isEmpty(paramsSearch)){
            return;
        }
        if (!UtilsLib.isNetworkConnect(mContext)){
            UtilsLib.showToast(mContext, mContext.getString(R.string.network_not_found));
            return;
        }
        if (getMvpView() != null){
            getMvpView().showLoading();
        }
        mApiHelper.searchLocationApiCall(paramsSearch, this);
    }

    public void addAddressLocation(Address address){
        address.setId(System.currentTimeMillis());
        mDatabaseHelper.addAddress(address);
    }

    @Override
    public void onSuccess(RequestApi TAG, String response) {
        if (RequestApi.API_SEARCH_LOCATION.equals(TAG)){
            ResultSearch resultSearch = Utils.parserObject(response, ResultSearch.class);
            DebugLog.logd(resultSearch);
            if (getMvpView() != null){
                getMvpView().bindDataToViews(resultSearch.listResultsAddress);
                getMvpView().hideLoading();
            }
        }
    }

    @Override
    public void onFailure(RequestApi TAG, String msg) {
        if (RequestApi.API_SEARCH_LOCATION.equals(TAG)){
            if (getMvpView() != null){
                getMvpView().hideLoading();
            }
            UtilsLib.showToast(mContext, msg);
        }
    }
}
