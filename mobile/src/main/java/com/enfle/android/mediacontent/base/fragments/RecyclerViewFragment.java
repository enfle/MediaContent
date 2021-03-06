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

package com.enfle.android.mediacontent.base.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enfle.android.mediacontent.R;
import com.enfle.android.mediacontent.base.adapter.ItemizedRecyclerAdapter;
import com.enfle.android.mediacontent.base.adapter.RecyclerAdapter;
import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.utils.ItemClickSupport;

import java.util.List;

import butterknife.BindView;

public abstract class RecyclerViewFragment extends BaseFragment {

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private static final int SPAN_COUNT = 2;

    protected LayoutManagerType mCurrentLayoutManagerType;

    @BindView(R.id.recycle_view)
    protected RecyclerView mRecyclerView;

    protected RecyclerAdapter mAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;

    /*Abstract methods*/
    abstract protected LayoutManagerType getLayoutManagerType();

    /**
     * If Default implementation need to be overridden then override this method and provide custom implementation
     *
     * @return - #{@RecyclerAdapter}
     */
    protected RecyclerAdapter getAdapter() {
        return new ItemizedRecyclerAdapter();
    }

    /**
     * Will be called when any item of recycler view has been clicked
     *
     * @param recyclerView @{RecyclerView}
     * @param position     - Item position
     * @param view-        view which has been clicked
     */
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(getLayoutManagerType());
        mAdapter = getAdapter();
        mRecyclerView.setAdapter(mAdapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(this::OnItemClicked);
    }

    private void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), getSpanCount());
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case HORIZONTAL_LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    protected int getSpanCount() {
        return SPAN_COUNT;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void addItems(List<RecyclerItemView> recyclerItemViews) {
        mAdapter.addItems(recyclerItemViews);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
