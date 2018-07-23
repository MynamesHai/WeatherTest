package com.tksoft.weather2018.ui.main;

import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.ui.base.BaseMvpView;

import java.util.List;

public interface MainMvp extends BaseMvpView {
    void showHelpActivity();

    void bindDataToViews(List<Address> listAddressLocation);

    void setPermissionLocation();
}
