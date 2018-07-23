package com.tksoft.weather2018.ui.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.data.model.address.Address;
import com.tksoft.weather2018.ui.base.BaseActivity;
import com.tksoft.weather2018.ui.search.adapter.AdapterSearchLocation;
import com.utility.DebugLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchLocationActivity extends BaseActivity implements SearchMvp, AdapterSearchLocation.ItemClickListener {
    @BindView(R.id.toolbar_details)
    Toolbar toolbarDetails;
    @BindView(R.id.et_action_search)
    AutoCompleteTextView etActionSearch;
    @BindView(R.id.rv_search_location)
    RecyclerView rvSearchLocation;

    private AdapterSearchLocation mAdapter;
    private List<Address> listAddress = new ArrayList<>();
    private SearchPresenter mPresenter;
    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_location;
    }

    @Override
    public void onViewCreated() {
        mContext = getContext();
        mPresenter = new SearchPresenter(mContext);
        mPresenter.attachView(this);
        initToolbar();
        initRecyclerViews();
    }

    private void initRecyclerViews() {
        mAdapter = new AdapterSearchLocation(mContext, listAddress, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvSearchLocation.setLayoutManager(layoutManager);
        rvSearchLocation.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        assert toolbarDetails != null;
        setSupportActionBar(toolbarDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    public void setActionForViews() {
        etActionSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPresenter.searchLocationAddress(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    @Override
    public void onItemClick(Address address) {
        mPresenter.addAddressLocation(address);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void bindDataToViews(List<Address> listAddress) {
        this.listAddress.clear();
        this.listAddress.addAll(listAddress);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DebugLog.logd("onBackPressed");
        setResult(Activity.RESULT_OK);
        super.onBackPressed();
    }
}
