package com.km2labs.mediacontent.loaders.movie.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.common.movie.detail.MovieDetailContract;
import com.km2labs.mediacontent.loaders.DefaultPresenterFactory;
import com.km2labs.mediacontent.loaders.BaseNetworkFragment;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieDetailFragment extends BaseNetworkFragment<MovieDetailContract.View, MovieDetailContract.Presenter>
        implements MovieDetailContract.View {

    public static final String ARG_MOVIE_ID = "Args:Fragment:Movie:Detail:Id";

    @BindView(R.id.backdrop)
    ImageView mBackdrop;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected PresenterFactory<MovieDetailContract.Presenter> getPresenterFactory() {
        return new DefaultPresenterFactory<>(DefaultPresenterFactory.TYPE_MOVIE_DETAIL);
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.movie_detail_fragment;
    }

    @Override
    protected void loadData() {
        onLoadingComplete(true);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        int movieId = bundle.getInt(ARG_MOVIE_ID);

        if (movieId < 1) {
            return;
        }
        mPresenter.getMovieDetail(movieId);
    }

    @Override
    public void onMovieDetailReceived(MovieDetailDto movieDetailDto) {
        MovieDetailFragmentPagerAdapter adapter = new MovieDetailFragmentPagerAdapter(getContext(), getFragmentManager());
        adapter.setMovieDetailDto(movieDetailDto);
        mViewPager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewPager, true);
        adapter.notifyDataSetChanged();

        String imagePath = movieDetailDto.getBackdropPath();
        Picasso.with(mBackdrop.getContext())
                .load("http://image.tmdb.org/t/p/w500/" + imagePath)
                .fit()
                .into(mBackdrop);
    }

    @Override
    public void onError() {

    }


}
