package com.tksoft.weather2018.ui.base;


public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
