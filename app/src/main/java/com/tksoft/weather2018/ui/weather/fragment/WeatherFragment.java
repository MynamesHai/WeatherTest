package com.tksoft.weather2018.ui.weather.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.ui.base.BaseFragment;
import com.tksoft.weather2018.ui.weather.MainWeatherActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WeatherFragment extends BaseFragment {

    @BindView(R.id.btn_home_menu)
    ImageButton btnHomeMenu;
    @BindView(R.id.img_home_weather)
    ImageView imgHomeWeather;
    @BindView(R.id.txt_home_cityname)
    TextView txtHomeCityname;
    @BindView(R.id.txt_home_today)
    TextView txtHomeToday;
    @BindView(R.id.btn_home_add)
    ImageButton btnHomeAdd;
    @BindView(R.id.txt_home_temp)
    TextView txtHomeTemp;
    @BindView(R.id.txt_home_wind)
    TextView txtHomeWind;
    @BindView(R.id.txt_home_state)
    TextView txtHomeState;
    @BindView(R.id.txt_home_humidity)
    TextView txtHomeHumidity;
    @BindView(R.id.txt_home_detail_day)
    TextView txtHomeDetailDay;
    @BindView(R.id.txt_home_detail_hour)
    TextView txtHomeDetailHour;

    private MainWeatherActivity weatherActivity;

    public static WeatherFragment newInstance() {
        WeatherFragment mFragment = new WeatherFragment();
        return mFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void onCreateView() {
    }

    @Override
    public void setActionForViews() {

    }

    @OnClick({R.id.btn_home_menu, R.id.btn_home_add, R.id.txt_home_detail_day, R.id.txt_home_detail_hour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home_menu:
                weatherActivity = (MainWeatherActivity) getContext();
                weatherActivity.openDrawer();
                break;
            case R.id.btn_home_add:
                break;
            case R.id.txt_home_detail_day:
                break;
            case R.id.txt_home_detail_hour:
                break;
        }
    }
}
