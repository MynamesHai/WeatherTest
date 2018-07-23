package com.tksoft.weather2018.ui.search;

import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.ui.base.BaseMvpView;

import java.util.List;

public interface SearchMvp extends BaseMvpView{
    void bindDataToViews(List<Address> listAddress);
}
