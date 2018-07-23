package com.tksoft.weather2018.ui.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.data.model.address.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSearchLocation extends RecyclerView.Adapter<AdapterSearchLocation.SearchLocationHolder> {

    private Context mContext;
    private List<Address> listAddressLocation;
    private ItemClickListener mListener;

    public AdapterSearchLocation(Context mContext, List<Address> listAddressLocation, ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.listAddressLocation = listAddressLocation;
        this.mListener = itemClickListener;
    }

    @Override
    public SearchLocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_location, parent, false);
        return new SearchLocationHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchLocationHolder holder, int position) {
        Address address = listAddressLocation.get(position);
        holder.tvItemAddressSearch.setText(address.formatted_address);
        holder.tvItemAddressSearch.setOnClickListener(view -> mListener.onItemClick(address));
    }


    public class SearchLocationHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_address_search)
        TextView tvItemAddressSearch;
        public SearchLocationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return listAddressLocation.size();
    }

    public interface ItemClickListener {
        void onItemClick(Address address);
    }

}
