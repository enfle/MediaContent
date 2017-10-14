package com.km2labs.mediacontent.common.movie.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.km2labs.mediacontent.R;
import com.km2labs.mediacontent.common.movie.bean.Review;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class ReviewListItem implements RecyclerItemView {

    private Review mReview;

    public ReviewListItem(Review review) {
        mReview = review;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_detail_review_list_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder) {
        ((ReviewViewHolder) holder).bindVew(mReview);
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_content)
        TextView mContentTextView;

        @BindView(R.id.author)
        TextView mAuthorTextView;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindVew(Review review) {
            mContentTextView.setText(review.getContent());
            mAuthorTextView.setText(review.getAuthor());
        }
    }
}
