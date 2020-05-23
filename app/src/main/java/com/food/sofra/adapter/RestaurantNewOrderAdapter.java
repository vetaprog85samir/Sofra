package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;

public class

RestaurantNewOrderAdapter extends RecyclerView.Adapter<RestaurantNewOrderAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<RestaurantMyOrdersData> restaurantMyOrdersData = new ArrayList<>();

    public RestaurantNewOrderAdapter(Context context, Activity activity, List<RestaurantMyOrdersData> restaurantMyOrdersData) {
        this.context = context;
        this.activity = activity;
        this.restaurantMyOrdersData = restaurantMyOrdersData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_restaurant_pending_orders_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        HelperMethod.onLoadImageFromUrl(holder.fragmentRestaurantPendingOrdersRowItemIvClientPhoto, restaurantMyOrdersData.get(position).getItems().get(position).getPhotoUrl(), activity);
        holder.fragmentRestaurantPendingOrdersRowItemTvClientName.setText(restaurantMyOrdersData.get(position).getItems().get(position).getName());
        holder.fragmentRestaurantPendingOrdersRowItemTvOrderNumber.setText(restaurantMyOrdersData.get(position).getId());
        holder.fragmentRestaurantPendingOrdersRowItemTvTotalFees.setText(restaurantMyOrdersData.get(position).getTotal());
        holder.fragmentRestaurantPendingOrdersRowItemTvAddress.setText(restaurantMyOrdersData.get(position).getAddress());
    }

    private void setAction(ViewHolder holder, int position) {
        holder.fragmentRestaurantPendingOrdersRowItemLayoutAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClient().acceptOrder(SharedPreferencesManger.loadData(activity, "api_token"),
                        String.valueOf(restaurantMyOrdersData.get(position).getId())).enqueue(new Callback<RestaurantAcceptOrder>() {
                    @Override
                    public void onResponse(Call<RestaurantAcceptOrder> call, Response<RestaurantAcceptOrder> response) {
                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        restaurantMyOrdersData.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<RestaurantAcceptOrder> call, Throwable t) {

                    }
                });
            }
        });

        holder.fragmentRestaurantPendingOrdersRowItemLayoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClient().cancelOrder(SharedPreferencesManger.loadData(activity, "api_token"),
                        String.valueOf(restaurantMyOrdersData.get(position).getId())).enqueue(new Callback<RestaurantAcceptOrder>() {
                    @Override
                    public void onResponse(Call<RestaurantAcceptOrder> call, Response<RestaurantAcceptOrder> response) {
                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        restaurantMyOrdersData.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<RestaurantAcceptOrder> call, Throwable t) {

                    }
                });
            }
        });

        holder.fragmentRestaurantPendingOrdersRowItemLayoutCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + restaurantMyOrdersData.get(position).getRestaurant().getPhone()));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantMyOrdersData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_restaurant_pending_orders_row_item_iv_client_photo)
        ImageView fragmentRestaurantPendingOrdersRowItemIvClientPhoto;
        @BindView(R.id.fragment_restaurant_pending_orders_row_item_tv_client_name)
        TextView fragmentRestaurantPendingOrdersRowItemTvClientName;
        @BindView(R.id.fragment_restaurant_pending_orders_row_item_tv_order_number)
        TextView fragmentRestaurantPendingOrdersRowItemTvOrderNumber;
        @BindView(R.id.fragment_restaurant_pending_orders_row_item_tv_total_fees)
        TextView fragmentRestaurantPendingOrdersRowItemTvTotalFees;
        @BindView(R.id.fragment_restaurant_pending_orders_row_item_tv_address)
        TextView fragmentRestaurantPendingOrdersRowItemTvAddress;
        @BindView(R.id.fragment_restaurant_pending_orders_row_item_layout_accept)
        LinearLayout fragmentRestaurantPendingOrdersRowItemLayoutAccept;
        @BindView(R.id.fragment_restaurant_pending_orders_row_item_layout_cancel)
        LinearLayout fragmentRestaurantPendingOrdersRowItemLayoutCancel;
        @BindView(R.id.fragment_restaurant_pending_orders_row_item_layout_call)
        LinearLayout fragmentRestaurantPendingOrdersRowItemLayoutCall;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
