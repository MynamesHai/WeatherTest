package com.tksoft.weather2018.data.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;



public class VolleyRequest<T> extends Request<T> {
    private final RequestCallback requestCallback;
    private RequestApi taskType;
    private Map<String, String> headers;

    public VolleyRequest(int method, String url, RequestCallback requestCallback, RequestApi taskType) {
        super(method, url, null);
        this.headers = new HashMap<>();
        this.requestCallback = requestCallback;
        this.taskType = taskType;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        headers.put("Content-BarCodeFormat", "application/json; charset=utf-8");
        return headers;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    protected void deliverResponse(T response) {
        requestCallback.onSuccess(taskType, response.toString());
    }

    @Override
    public void deliverError(VolleyError error) {
        error.printStackTrace();
        requestCallback.onFailure(taskType, error.toString());
    }

    @Override
    public boolean isCanceled() {
        return super.isCanceled();
    }

    @Override
    public Response.ErrorListener getErrorListener() {
        return super.getErrorListener();
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            requestCallback.onFailure(taskType, "" + new ParseError(e));
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            requestCallback.onFailure(taskType, "" + new ParseError(e));
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            e.printStackTrace();
            requestCallback.onFailure(taskType, "" + new ParseError(e));
            return Response.error(new ParseError(e));
        }
    }

}
