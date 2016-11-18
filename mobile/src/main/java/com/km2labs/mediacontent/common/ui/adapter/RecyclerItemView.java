package com.km2labs.mediacontent.common.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public interface RecyclerItemView {

    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent);

    void onBindView(RecyclerView.ViewHolder holder);
}
