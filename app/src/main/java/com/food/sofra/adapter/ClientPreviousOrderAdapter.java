package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrdersData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

ClientPreviousOrderAdapter extends RecyclerView.Adapter<ClientPreviousOrderAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<ClientMyOrdersData> clientMyOrdersData = new ArrayList<>();

    public ClientPreviousOrderAdapter(Context context, Activity activity, List<ClientMyOrdersData> clientMyOrdersData) {
        this.context = context;
        this.activity = activity;
        this.clientMyOrdersData = clientMyOrdersData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_previous_orders_row_item,
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
        return clientMyOrdersData.size();
    }

    @OnClick({R.id.fragment_completed_orders_layout_main, R.id.fragment_completed_orders_layout_complete_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_completed_orders_layout_main:
                break;
            case R.id.fragment_completed_orders_layout_complete_order:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_completed_orders_iv_rest_logo)
        ImageView fragmentCompletedOrdersIvRestLogo;
        @BindView(R.id.fragment_completed_orders_tv_name)
        TextView fragmentCompletedOrdersTvName;
        @BindView(R.id.fragment_completed_orders_tv_number)
        TextView fragmentCompletedOrdersTvNumber;
        @BindView(R.id.fragment_completed_orders_tv_cost)
        TextView fragmentCompletedOrdersTvCost;
        @BindView(R.id.fragment_completed_orders_tv_address)
        TextView fragmentCompletedOrdersTvAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
