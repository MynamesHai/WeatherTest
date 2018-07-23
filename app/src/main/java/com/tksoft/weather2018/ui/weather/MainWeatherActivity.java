package com.tksoft.weather2018.ui.weather;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.ui.base.BaseActivity;
import com.tksoft.weather2018.ui.weather.adapter.WeatherPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainWeatherActivity extends BaseActivity {
    @BindView(R.id.vp_weather)
    ViewPager vpWeather;
    @BindView(R.id.frame_fragment_weather)
    FrameLayout frameFragmentWeather;
//    @BindView(R.id.nav_menu_weather)
//    NavigationView navMenuWeather;
    @BindView(R.id.drawer_layout_weather)
    DrawerLayout drawerLayoutWeather;
    private WeatherPagerAdapter pagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_weather;
    }

    @Override
    public void onViewCreated() {
        pagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager());
        vpWeather.setAdapter(pagerAdapter);
    }

    @Override
    public void setActionForViews() {

    }


    public void openDrawer() {
        drawerLayoutWeather.openDrawer(GravityCompat.START);
    }

    public void closeDrawer() {
       // drawerLayoutWeather.closeDrawer(navMenuWeather);
    }

}
