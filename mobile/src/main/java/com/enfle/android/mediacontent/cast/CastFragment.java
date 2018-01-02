package com.enfle.android.mediacontent.cast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enfle.android.mediacontent.base.adapter.RecyclerItemView;
import com.enfle.android.mediacontent.base.fragments.LayoutManagerType;
import com.enfle.android.mediacontent.base.fragments.RecyclerViewFragment;
import com.enfle.android.mediacontent.beans.Cast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subhamtyagi on 18/12/17.
 */

public class CastFragment extends RecyclerViewFragment {

    public static final String ARG_MOVIE_CAST = "Arg:Fragment:Movie:Cast";

    @Override
    protected LayoutManagerType getLayoutManagerType() {
        return LayoutManagerType.GRID_LAYOUT_MANAGER;
    }

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Cast> castList = Parcels.unwrap(getArguments().getParcelable(ARG_MOVIE_CAST));
        setCast(castList);
    }

    void setCast(List<Cast> castList) {
        List<RecyclerItemView> itemViews = new ArrayList<>();

        for (Cast cast : castList) {
            itemViews.add(new CastRecyclerView(cast));
        }
        addItems(itemViews);
    }

    @Override
    protected void OnItemClicked(RecyclerView recyclerView, int position, View view) {
        super.OnItemClicked(recyclerView, position, view);
    }
}
