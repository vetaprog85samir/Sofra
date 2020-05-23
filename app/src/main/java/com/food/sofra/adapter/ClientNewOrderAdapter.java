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
import com.food.sofra.data.model.client.orders.clientNewOrder.ClientNewOrderData;
import com.food.sofra.data.model.restaurant.orders.restaurantAcceptOrder.RestaurantAcceptOrder;
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
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.loadData;

public class

ClientNewOrderAdapter extends RecyclerView.Adapter<ClientNewOrderAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<ClientNewOrderData> clientNewOrderData = new ArrayList<>();

    public ClientNewOrderAdapter(Context context, Activity activity, List<ClientNewOrderData> clientNewOrderData) {
        this.context = context;
        this.activity = activity;
        this.clientNewOrderData = clientNewOrderData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_new_orders_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.fragmentPendingOrdersLayoutTvOrderName.setText(clientNewOrderData.get(position).getItems().get(position).getName());
        holder.fragmentPendingOrdersLayoutTvOrderNumber.setText(clientNewOrderData.get(position).getId());
        holder.fragmentPendingOrdersLayoutTvAddress.setText(clientNewOrderData.get(position).getAddress());
        holder.fragmentPendingOrdersLayoutTvOrderFees.setText(clientNewOrderData.get(position).getTotal());
        HelperMethod.onLoadImageFromUrl(holder.fragmentPendingOrdersIvRestLogo, clientNewOrderData.get(position).getItems().get(position).getPhotoUrl(), activity);
    }

    private void setAction(ViewHolder holder, int position) {
        holder.fragmentPendingOrdersLayoutLayoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClient().cancelOrder(loadData(activity,"api_token"),String.valueOf(clientNewOrderData.get(position).getId())).enqueue(new Callback<RestaurantAcceptOrder>() {
                    @Override
                    public void onResponse(Call<RestaurantAcceptOrder> call, Response<RestaurantAcceptOrder> response) {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                        clientNewOrderData.remove(position);
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
        return clientNewOrderData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_pending_orders_iv_rest_logo)
        ImageView fragmentPendingOrdersIvRestLogo;
        @BindView(R.id.fragment_pending_orders_layout_tv_order_name)
        TextView fragmentPendingOrdersLayoutTvOrderName;
        @BindView(R.id.fragment_pending_orders_layout_tv_order_number)
        TextView fragmentPendingOrdersLayoutTvOrderNumber;
        @BindView(R.id.fragment_pending_orders_layout_tv_order_fees)
        TextView fragmentPendingOrdersLayoutTvOrderFees;
        @BindView(R.id.fragment_pending_orders_layout_tv_address)
        TextView fragmentPendingOrdersLayoutTvAddress;
        @BindView(R.id.fragment_pending_orders_layout_layout_cancel)
        LinearLayout fragmentPendingOrdersLayoutLayoutCancel;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
