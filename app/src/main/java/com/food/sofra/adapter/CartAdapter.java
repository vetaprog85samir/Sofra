package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.concurrent.Executors;
import com.food.sofra.R;
import com.food.sofra.data.local.room.Item;
import com.food.sofra.data.local.room.RoomDao;
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrdersData;
import com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.foodList.CartFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.food.sofra.data.local.room.RoomManager.getInstance;
import static com.food.sofra.utils.HelperMethod.onLoadImageFromUrl;

public class

CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    CartFragment cartFragment;
    private int quantity =0;
    private RoomDao roomDao;
    private List<Item> items = new ArrayList<>();

    public CartAdapter(Context context, Activity activity, List<Item> items,
                       CartFragment cartFragment) {
        this.context = context;
        this.activity = activity;
        this.items = items;
        this.cartFragment = cartFragment;
        roomDao = getInstance(activity).roomDao();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_cart_row_item,
                parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.fragmentCartRowItemName.setText(items.get(position).getItemName());
        holder.fragmentCartRowItemTvPriceOfOrder.setText(String.valueOf(items.get(position).getCost()));
        holder.fragmentCartRowItemTvNumberOfOrders.setText(String.valueOf(items.get(position).getQuantity()));
        onLoadImageFromUrl(holder.fragmentCartRowItemIvPhotoOfOrder,items.get(position).getImage(),
                activity);
    }

    private void setAction(ViewHolder holder, int position) {
        holder.fragmentCartRowItemBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = items.get(position).getQuantity() +1;
                holder.fragmentCartRowItemTvNumberOfOrders.setText(quantity);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        items.get(position).setQuantity(quantity);
                        roomDao.onUpdate(items.get(position));
                        cartFragment.updateUi(items);
                    }
                });
            }
        });

        holder.fragmentCartRowItemBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = items.get(position).getQuantity() -1;
                holder.fragmentCartRowItemTvNumberOfOrders.setText(quantity);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        items.get(position).setQuantity(quantity);
                        roomDao.onUpdate(items.get(position));
                        cartFragment.updateUi(items);
                    }
                });
            }
        });

        holder.fragmentCartRowItemBtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        roomDao.onDelete(items.get(position));
                        cartFragment.updateUi(items);

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                items.remove(position);
                                notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_cart_row_item_iv_photo_of_order)
        ImageView fragmentCartRowItemIvPhotoOfOrder;
        @BindView(R.id.fragment_cart_row_item_name)
        TextView fragmentCartRowItemName;
        @BindView(R.id.fragment_cart_row_item_tv_price_of_order)
        TextView fragmentCartRowItemTvPriceOfOrder;
        @BindView(R.id.fragment_cart_row_item_tv_number_of_orders)
        TextView fragmentCartRowItemTvNumberOfOrders;


        @BindView(R.id.fragment_cart_row_item_btn_plus)
        ImageButton fragmentCartRowItemBtnPlus;
        @BindView(R.id.fragment_cart_row_item_btn_minus)
        ImageButton fragmentCartRowItemBtnMinus;
        @BindView(R.id.fragment_cart_row_item_btn_remove)
        ImageButton fragmentCartRowItemBtnRemove;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
