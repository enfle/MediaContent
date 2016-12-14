/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.km2labs.mediacontent.loaders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.core.adapter.ItemizedRecyclerAdapter;
import com.km2labs.mediacontent.core.adapter.RecyclerAdapter;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.framework.mvp.INetworkPresenter;
import com.km2labs.framework.mvp.ILoadingView;
import com.km2labs.mediacontent.common.utils.ItemClickSupport;

import java.util.List;

import butterknife.BindView;


public abstract class RecyclerViewFragment<V extends ILoadingView, P extends INetworkPresenter<V>> extends BaseNetworkFragment<V, P> {

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 3;


    protected enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        VERTICAL_LINEAR_LAYOUT_MANAGER,
        HORIZONTAL_LINEAR_LAYOUT_MANAGER;
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    @BindView(R.id.recycle_view)
    protected RecyclerView mRecyclerView;

    protected RecyclerAdapter mAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;

    /*Abstract methods*/
    abstract protected LayoutManagerType getLayoutManagerType();

    protected abstract void onLoadData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.VERTICAL_LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(getLayoutManagerType());
        mAdapter = getAdapter();
        mRecyclerView.setAdapter(mAdapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(this::OnItemClicked);
    }


    protected RecyclerAdapter getAdapter() {
        return new ItemizedRecyclerAdapter();
    }

    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {

    }

    private void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }
        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case VERTICAL_LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.VERTICAL_LINEAR_LAYOUT_MANAGER;
                break;
            case HORIZONTAL_LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                mCurrentLayoutManagerType = LayoutManagerType.HORIZONTAL_LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.VERTICAL_LINEAR_LAYOUT_MANAGER;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.recycler_view;
    }

    @Override
    final protected void loadData() {
        onLoadData();
    }

    public void addItems(List<RecyclerItemView> recyclerItemViews) {
        mAdapter.addItems(recyclerItemViews);
    }

}
