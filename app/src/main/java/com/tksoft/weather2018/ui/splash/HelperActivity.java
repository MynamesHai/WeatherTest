package com.tksoft.weather2018.ui.splash;

import android.widget.TextView;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.data.eventbus.Event;
import com.tksoft.weather2018.data.eventbus.MessageEvent;
import com.tksoft.weather2018.data.local.greendao.DatabaseHelper;
import com.tksoft.weather2018.ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class HelperActivity extends BaseActivity {

    @BindView(R.id.tv_skip_splash)
    TextView tvSkipSplash;

    private DatabaseHelper databaseHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onViewCreated() {
        databaseHelper = new DatabaseHelper(getContext());
    }

    @Override
    public void setActionForViews() {

    }

    @OnClick(R.id.tv_skip_splash)
    public void onViewClicked() {
        databaseHelper.setFirstInstallApp(true);
        finish();
    }
}
