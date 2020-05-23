package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.restaurant.orders.restaurantAcceptOrder.RestaurantAcceptOrder;
import com.food.sofra.data.model.restaurant.orders.restaurantMyOrders.RestaurantMyOrdersData;
import com.food.sofra.utils.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;

public class

RestaurantCurrentOrderAdapter extends RecyclerView.Adapter<RestaurantCurrentOrderAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<RestaurantMyOrdersData> restaurantMyOrdersData = new ArrayList<>();

    public RestaurantCurrentOrderAdapter(Context context, Activity activity, List<RestaurantMyOrdersData> restaurantMyOrdersData) {
        this.context = context;
        this.activity = activity;
        this.restaurantMyOrdersData = restaurantMyOrdersData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_restaurant_current_orders_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        HelperMethod.onLoadImageFromUrl(holder.fragmentRestaurantCurrentOrdersRowItemIvClientPhoto, restaurantMyOrdersData.get(position).getItems().get(position).getPhotoUrl(), activity);
        holder.fragmentRestaurantCurrentOrdersRowItemTvClientName.setText(restaurantMyOrdersData.get(position).getItems().get(position).getName());
        holder.fragmentRestaurantCurrentOrdersRowItemTvOrderNumber.setText(restaurantMyOrdersData.get(position).getId());
        holder.fragmentRestaurantCurrentOrdersRowItemTvTotalFees.setText(restaurantMyOrdersData.get(position).getTotal());
        holder.fragmentRestaurantCurrentOrdersRowItemTvAddress.setText(restaurantMyOrdersData.get(position).getAddress());
    }

    private void setAction(ViewHolder holder, int position) {
        holder.fragmentRestaurantCurrentOrdersRowItemLayoutConfirmDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClient().confirmOrder(SharedPreferencesManger.loadData(activity,"api_token"),
                        String.valueOf(restaurantMyOrdersData.get(position).getId())).enqueue(new Callback<RestaurantAcceptOrder>() {
                    @Override
                    public void onResponse(Call<RestaurantAcceptOrder> call, Response<RestaurantAcceptOrder> response) {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                        restaurantMyOrdersData.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<RestaurantAcceptOrder> call, Throwable t) {

                    }
                });
            }
        });

        holder.fragmentRestaurantCurrentOrdersRowItemLayoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClient().cancelOrder(SharedPreferencesManger.loadData(activity,"api_token"),
                        String.valueOf(restaurantMyOrdersData.get(position).getId())).enqueue(new Callback<RestaurantAcceptOrder>() {
                    @Override
                    public void onResponse(Call<RestaurantAcceptOrder> call, Response<RestaurantAcceptOrder> response) {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                        restaurantMyOrdersData.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<RestaurantAcceptOrder> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantMyOrdersData.size();
    }

    @OnClick({R.id.fragment_restaurant_current_orders_row_item_layout_main, R.id.fragment_restaurant_current_orders_row_item_layout_confirm_delivery, R.id.fragment_restaurant_current_orders_row_item_layout_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_restaurant_current_orders_row_item_layout_main:
                break;
            case R.id.fragment_restaurant_current_orders_row_item_layout_confirm_delivery:
                break;
            case R.id.fragment_restaurant_current_orders_row_item_layout_cancel:
                break;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_restaurant_current_orders_row_item_iv_client_photo)
        ImageView fragmentRestaurantCurrentOrdersRowItemIvClientPhoto;
        @BindView(R.id.fragment_restaurant_current_orders_row_item_tv_client_name)
        TextView fragmentRestaurantCurrentOrdersRowItemTvClientName;
        @BindView(R.id.fragment_restaurant_current_orders_row_item_tv_order_number)
        TextView fragmentRestaurantCurrentOrdersRowItemTvOrderNumber;
        @BindView(R.id.fragment_restaurant_current_orders_row_item_tv_total_fees)
        TextView fragmentRestaurantCurrentOrdersRowItemTvTotalFees;
        @BindView(R.id.fragment_restaurant_current_orders_row_item_tv_address)
        TextView fragmentRestaurantCurrentOrdersRowItemTvAddress;
        @BindView(R.id.fragment_restaurant_current_orders_row_item_layout_confirm_delivery)
        LinearLayout fragmentRestaurantCurrentOrdersRowItemLayoutConfirmDelivery;
        @BindView(R.id.fragment_restaurant_current_orders_row_item_layout_cancel)
        LinearLayout fragmentRestaurantCurrentOrdersRowItemLayoutCancel;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
