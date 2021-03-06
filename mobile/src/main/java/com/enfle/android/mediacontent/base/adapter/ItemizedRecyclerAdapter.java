package com.enfle.android.mediacontent.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class ItemizedRecyclerAdapter extends RecyclerAdapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerItemView itemView = mViewTypeVsViewItemLookup.get(viewType);
        return itemView.onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerItemView itemView = mItems.get(position);
        itemView.onBindView(holder);
    }
}
