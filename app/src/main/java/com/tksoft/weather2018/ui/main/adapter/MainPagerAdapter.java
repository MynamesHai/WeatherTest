package com.tksoft.weather2018.ui.main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.ui.main.fragment.HomeFragment;

import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private List<Address> listAddressLocation;
    private Context mContext;

    public MainPagerAdapter(FragmentManager fm, List<Address> listAddressLocation, Context context) {
        super(fm);
        this.listAddressLocation = listAddressLocation;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeFragment.newInstance(listAddressLocation.get(position).id);
    }

    @Override
    public int getCount() {
        return listAddressLocation.size();
    }
}
