package com.km2labs.mediacontent.loaders.movie.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.RetrofitHelper;
import com.km2labs.mediacontent.common.cache.InMemoryCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.common.movie.detail.OverviewFragmentContract;
import com.km2labs.mediacontent.common.movie.detail.OverviewFragmentPresenter;
import com.km2labs.mediacontent.loaders.BaseLoadingFragment;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;

import butterknife.BindView;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

public class MovieOverviewFragment extends BaseLoadingFragment<OverviewFragmentContract.View, OverviewFragmentContract.Presenter>
        implements OverviewFragmentContract.View {

    public static final String ARG_MOVIE_ID = "Args:Fragment:Movie:Detail";

    private Integer mMovieId;

    @BindView(R.id.overview_text_view)
    TextView mOverviewText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            showEmptyScreen();
            return;
        }
        mMovieId = bundle.getInt(ARG_MOVIE_ID);
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.movie_detail_overview_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimilarMovieFragment similarMovieFragment = new SimilarMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SimilarMovieFragment.ARG_MOVIE_ID, mMovieId);
        similarMovieFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.similar_movie_container, similarMovieFragment).commit();

        RecommendedMovieFragment recommendedMovieFragment = new RecommendedMovieFragment();
        Bundle recBundle = new Bundle();
        recBundle.putInt(RecommendedMovieFragment.ARG_MOVIE_ID, mMovieId);
        recommendedMovieFragment.setArguments(recBundle);
        getFragmentManager().beginTransaction().add(R.id.recommended_movie_container, recommendedMovieFragment).commit();
    }

    @Override
    protected void loadData() {
        onLoadingComplete(true);
        mPresenter.getMovieDetail(mMovieId);
    }

    @Override
    protected int getLoaderId() {
        return 11;
    }

    @Override
    protected PresenterFactory<OverviewFragmentContract.Presenter> getPresenterFactory() {
        return () -> new OverviewFragmentPresenter(RetrofitHelper.getRetrofitHelper()
                .createService(MovieService.class), new InMemoryCache());
    }

    @Override
    public void onError() {

    }

    @Override
    public void onMovieDetailLoaded(MovieDetailDto movieDetailDto) {
        mOverviewText.setText(movieDetailDto.getOverview());
    }
}
