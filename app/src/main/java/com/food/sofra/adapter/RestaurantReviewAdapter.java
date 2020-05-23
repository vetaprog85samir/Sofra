package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.client.clientAddReview.Review;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

RestaurantReviewAdapter extends RecyclerView.Adapter<RestaurantReviewAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<Review> review = new ArrayList<>();

    public RestaurantReviewAdapter(Context context, Activity activity, List<Review> review) {
        this.context = context;
        this.activity = activity;
        this.review = review;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_restaurant_reviews_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return review.size();
    }

    @OnClick(R.id.fragment_restaurant_reviews_row_item_layout_main)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_restaurant_reviews_row_item_tv_name)
        TextView fragmentRestaurantReviewsRowItemTvName;
        @BindView(R.id.fragment_restaurant_reviews_row_item_tv_title)
        TextView fragmentRestaurantReviewsRowItemTvTitle;
        @BindView(R.id.fragment_restaurant_reviews_row_item_iv_review)
        ImageView fragmentRestaurantReviewsRowItemIvReview;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
