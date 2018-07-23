package com.tksoft.weather2018.data.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tksoft.weather2018.BaseApplication;


public class BaseRequestQueue {
    private static final String TAG = BaseRequestQueue.class.getSimpleName();
    /**
     * time out set to request.
     */
    private static final Integer TIME_OUT = 3000;
    /**
     * number retry if time out is exceeded.
     */
    private static final Integer NUMBER_RETRY = 0;
    /**
     * depends on Restful WebServices.
     */
    private static final float EXPONENTIAL_BACKOFF = 1.0f;
    private static BaseRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    /**
     * ApplicationContext is key, it keeps you from leaking the Activity or
     * BroadcastReceiver if someone passes one in.
     */
    private Context mContext;

    private BaseRequestQueue() {
        mContext = BaseApplication.getAppContext();
        mRequestQueue = getRequestQueue();
    }

    public static BaseRequestQueue getInstance() {
        if (mInstance == null) {
            mInstance = new BaseRequestQueue();
        }
        return mInstance;
    }
    /**
     * get singleton request queue.
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    /**
     * set Tag to request and add to request queue. and set retry policy.
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        addToRequestQueue(req, null);
    }

    public void cancelPendingRequest(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void removeCache(String url) {
        mRequestQueue.getCache().remove(url);

    }

    public void clearCache() {
        mRequestQueue.getCache().clear();
    }
}
