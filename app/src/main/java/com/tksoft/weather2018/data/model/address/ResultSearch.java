package com.tksoft.weather2018.data.model.address;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import java.util.ArrayList;
import java.util.List;

public class ResultSearch {
    @SerializedName("results")
    public List<Address> listResultsAddress = new ArrayList<>();
}
