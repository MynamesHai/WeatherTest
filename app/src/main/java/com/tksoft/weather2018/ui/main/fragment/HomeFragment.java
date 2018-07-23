package com.tksoft.weather2018.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.tksoft.weather2018.R;
import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.ui.base.BaseFragment;
import com.tksoft.weather2018.ui.search.SearchLocationActivity;
import com.tksoft.weather2018.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeMvp{
    @BindView(R.id.btn_navigation_menu_home)
    FrameLayout btnNavigationMenuHome;
    @BindView(R.id.iv_status_weather_home)
    ImageView ivStatusWeatherHome;
    @BindView(R.id.tv_address_home)
    TextView tvAddressHome;
    @BindView(R.id.btn_add_location_home)
    FrameLayout btnAddLocationHome;

    private Context mContext;
    private HomePresenter mPresenter;

    public static HomeFragment newInstance(long idAddress) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.Bundle.KEY_ID_ADDRESS, idAddress);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_weather;
    }

    @Override
    public void onCreateView() {
        long idAddress = getArguments().getLong(Constants.Bundle.KEY_ID_ADDRESS, 0);
        mContext = getContext();
        mPresenter = new HomePresenter(mContext);
        mPresenter.attachView(this);
        mPresenter.getDataByIdAddress(idAddress);
    }

    @OnClick(R.id.btn_add_location_home)
    public void onAddLocation() {
        Intent intent = new Intent(mContext, SearchLocationActivity.class);
        startActivity(intent);
    }

    @Override
    public void setActionForViews() {

    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void setDataForViews(Address address) {
        tvAddressHome.setText(address.formatted_address);
    }
}
