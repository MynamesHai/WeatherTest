package com.tksoft.weather2018.ui.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tksoft.weather2018.ui.weather.fragment.WeatherFragment;

public class WeatherPagerAdapter extends FragmentStatePagerAdapter {
    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
