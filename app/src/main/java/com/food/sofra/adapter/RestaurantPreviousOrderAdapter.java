package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.restaurant.orders.restaurantMyOrders.RestaurantMyOrdersData;
import com.food.sofra.utils.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class

RestaurantPreviousOrderAdapter extends RecyclerView.Adapter<RestaurantPreviousOrderAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<RestaurantMyOrdersData> restaurantMyOrdersData = new ArrayList<>();

    public RestaurantPreviousOrderAdapter(Context context, Activity activity, List<RestaurantMyOrdersData> restaurantMyOrdersData) {
        this.context = context;
        this.activity = activity;
        this.restaurantMyOrdersData = restaurantMyOrdersData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_restaurant_completed_orders_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        HelperMethod.onLoadImageFromUrl(holder.fragmentRestaurantCompletedOrdersRowItemIvClientPhoto, restaurantMyOrdersData.get(position).getItems().get(position).getPhotoUrl(), activity);
        holder.fragmentRestaurantCompletedOrdersRowItemTvClientName.setText(restaurantMyOrdersData.get(position).getItems().get(position).getName());
        holder.fragmentRestaurantCompletedOrdersRowItemTvOrderNumber.setText(restaurantMyOrdersData.get(position).getId());
        holder.fragmentRestaurantCompletedOrdersRowItemTvCost.setText(restaurantMyOrdersData.get(position).getTotal());
        holder.fragmentRestaurantCompletedOrdersRowItemTvAddress.setText(restaurantMyOrdersData.get(position).getAddress());
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return restaurantMyOrdersData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_restaurant_completed_orders_row_item_iv_client_photo)
        ImageView fragmentRestaurantCompletedOrdersRowItemIvClientPhoto;
        @BindView(R.id.fragment_restaurant_completed_orders_row_item_tv_client_name)
        TextView fragmentRestaurantCompletedOrdersRowItemTvClientName;
        @BindView(R.id.fragment_restaurant_completed_orders_row_item_tv_order_number)
        TextView fragmentRestaurantCompletedOrdersRowItemTvOrderNumber;
        @BindView(R.id.fragment_restaurant_completed_orders_row_item_tv_cost)
        TextView fragmentRestaurantCompletedOrdersRowItemTvCost;
        @BindView(R.id.fragment_restaurant_completed_orders_row_item_tv_address)
        TextView fragmentRestaurantCompletedOrdersRowItemTvAddress;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
