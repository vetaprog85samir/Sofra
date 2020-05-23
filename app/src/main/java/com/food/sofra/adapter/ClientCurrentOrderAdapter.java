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
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrdersData;
import com.food.sofra.data.model.client.orders.clientNewOrder.ClientNewOrderData;
import com.food.sofra.data.model.restaurant.orders.restaurantAcceptOrder.RestaurantAcceptOrder;
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

ClientCurrentOrderAdapter extends RecyclerView.Adapter<ClientCurrentOrderAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<ClientMyOrdersData> clientMyOrdersData = new ArrayList<>();

    public ClientCurrentOrderAdapter(Context context, Activity activity, List<ClientMyOrdersData> clientMyOrdersData) {
        this.context = context;
        this.activity = activity;
        this.clientMyOrdersData = clientMyOrdersData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_current_orders_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.fragmentCurrentOrdersTvName.setText(clientMyOrdersData.get(position).getItems().get(position).getName());
        holder.fragmentCurrentOrdersTvNumber.setText(clientMyOrdersData.get(position).getId());
        holder.fragmentCurrentOrdersTvAddress.setText(clientMyOrdersData.get(position).getAddress());
        holder.fragmentCurrentOrdersTvCost.setText(clientMyOrdersData.get(position).getTotal());
        HelperMethod.onLoadImageFromUrl(holder.fragmentCurrentOrdersIvRestLogo, clientMyOrdersData.get(position).getItems().get(position).getPhotoUrl(), activity);
    }

    private void setAction(ViewHolder holder, int position) {
        holder.fragmentCurrentOrdersLayoutConfirmDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClient().confirmOrder(SharedPreferencesManger.loadData(activity,"api_token"),
                        String.valueOf(clientMyOrdersData.get(position).getId())).enqueue(new Callback<RestaurantAcceptOrder>() {
                    @Override
                    public void onResponse(Call<RestaurantAcceptOrder> call, Response<RestaurantAcceptOrder> response) {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                        clientMyOrdersData.remove(position);
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
        return clientMyOrdersData.size();
    }

    @OnClick({R.id.fragment_current_orders_layout_main, R.id.fragment_current_orders_layout_confirm_delivery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_current_orders_layout_main:
                break;
            case R.id.fragment_current_orders_layout_confirm_delivery:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.fragment_current_orders_iv_rest_logo)
        ImageView fragmentCurrentOrdersIvRestLogo;
        @BindView(R.id.fragment_current_orders_tv_name)
        TextView fragmentCurrentOrdersTvName;
        @BindView(R.id.fragment_current_orders_tv_number)
        TextView fragmentCurrentOrdersTvNumber;
        @BindView(R.id.fragment_current_orders_tv_cost)
        TextView fragmentCurrentOrdersTvCost;
        @BindView(R.id.fragment_current_orders_tv_address)
        TextView fragmentCurrentOrdersTvAddress;
        @BindView(R.id.fragment_current_orders_layout_confirm_delivery)
        LinearLayout fragmentCurrentOrdersLayoutConfirmDelivery;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
