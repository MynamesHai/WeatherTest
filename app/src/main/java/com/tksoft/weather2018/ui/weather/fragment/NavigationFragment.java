package com.tksoft.weather2018.ui.weather.fragment;


import android.view.View;
import android.widget.RelativeLayout;

import com.tksoft.weather2018.ui.base.BaseFragment;
import com.tksoft.weather2018.R;

import butterknife.BindView;
import butterknife.OnClick;


public class NavigationFragment extends BaseFragment {
    @BindView(R.id.menu_location_item1)
    RelativeLayout menuLocationItem1;
    @BindView(R.id.menu_location_item2)
    RelativeLayout menuLocationItem2;
    @BindView(R.id.menu_location_item3)
    RelativeLayout menuLocationItem3;
    @BindView(R.id.menu_location_item4)
    RelativeLayout menuLocationItem4;
    @BindView(R.id.menu_location_item5)
    RelativeLayout menuLocationItem5;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigation_menu;
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void setActionForViews() {

    }
    @OnClick({R.id.menu_location_item1, R.id.menu_location_item2, R.id.menu_location_item3,
            R.id.menu_location_item4, R.id.menu_location_item5})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.menu_location_item1:

                break;
            case R.id.menu_location_item2:

                break;
            case R.id.menu_location_item3:

                break;
            case R.id.menu_location_item4:

                break;
            case R.id.menu_location_item5:

                break;
        }
    }
}
