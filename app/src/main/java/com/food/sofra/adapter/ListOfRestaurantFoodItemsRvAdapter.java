package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItemsData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

ListOfRestaurantFoodItemsRvAdapter extends RecyclerView.Adapter<ListOfRestaurantFoodItemsRvAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<GeneralListOfRestaurantItemsData> generalListOfRestaurantItemsData = new ArrayList<>();
    public int selectedId = 0;

    public ListOfRestaurantFoodItemsRvAdapter(Context context, Activity activity, List<GeneralListOfRestaurantItemsData> generalListOfRestaurantItemsData) {
        this.context = context;
        this.activity = activity;
        this.generalListOfRestaurantItemsData = generalListOfRestaurantItemsData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_show_list_food_row_item,
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

        return generalListOfRestaurantItemsData.size();
    }

    @OnClick(R.id.fragment_show_food_list_row_item_layout_main)
    public void onViewClicked() {
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;


        @BindView(R.id.fragment_show_food_list_row_item_iv)
        ImageView fragmentShowFoodListRowItemIv;
        @BindView(R.id.fragment_show_food_list_row_item_tv_name)
        TextView fragmentShowFoodListRowItemTvName;
        @BindView(R.id.fragment_show_food_list_row_item_tv_description)
        TextView fragmentShowFoodListRowItemTvDescription;
        @BindView(R.id.fragment_show_food_list_row_item_tv_price)
        TextView fragmentShowFoodListRowItemTvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }


    @Override
    public int getItemViewType(int position) {

        selectedId = generalListOfRestaurantItemsData.get(position).getId();

        return super.getItemViewType(position);
    }
}
