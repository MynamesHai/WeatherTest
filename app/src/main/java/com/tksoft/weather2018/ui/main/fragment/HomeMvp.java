package com.tksoft.weather2018.ui.main.fragment;

import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.ui.base.BaseMvpView;

public interface HomeMvp extends BaseMvpView {
    void setDataForViews(Address address);
}
