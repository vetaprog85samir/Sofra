package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurant.GeneralListOfRestaurantData;
import com.food.sofra.view.activities.BaseActivity;
import com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.RestaurantDataContainerFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.food.sofra.utils.HelperMethod.onLoadImageFromUrl;
import static com.food.sofra.utils.HelperMethod.replaceFragment;

public class

ListOfRestaurantsRvAdapter extends RecyclerView.Adapter<ListOfRestaurantsRvAdapter.ViewHolder> {

    private Context context;
    private BaseActivity activity;
    private List<GeneralListOfRestaurantData> generalListOfRestaurantData = new ArrayList<>();

    public ListOfRestaurantsRvAdapter(Context context, Activity activity, List<GeneralListOfRestaurantData> generalListOfRestaurantData) {
        this.context = context;
        this.activity = (BaseActivity) activity;
        this.generalListOfRestaurantData = generalListOfRestaurantData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_list_restaurants_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.fragmentListRestaurantsRowItemTvRestaurantName.setText(generalListOfRestaurantData.get(position).getName());
        holder.fragmentListRestaurantsRowItemTvRestaurantState.setText(generalListOfRestaurantData.get(position).getAvailability());
        holder.fragmentListRestaurantsRowItemRbRate.setRating(generalListOfRestaurantData.get(position).getRate());
        holder.fragmentListRestaurantsRowItemTvMimOrder.setText(generalListOfRestaurantData.get(position).getMinimumCharger());
        holder.fragmentListRestaurantsRowItemTvDelCost.setText(generalListOfRestaurantData.get(position).getDeliveryCost());
        onLoadImageFromUrl(holder.fragmentListRestaurantsRowItemCiImage,generalListOfRestaurantData.get(position).getPhotoUrl(),activity);
    }

    private void setAction(ViewHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantDataContainerFragment restaurantDataContainerFragment = new RestaurantDataContainerFragment();
                restaurantDataContainerFragment.generalListOfRestaurantData = generalListOfRestaurantData.get(position);
                replaceFragment(activity.getSupportFragmentManager(),R.id.fragment_restaurant_register,restaurantDataContainerFragment);
            }
        });

    }

    @Override
    public int getItemCount() {

        return generalListOfRestaurantData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;


        @BindView(R.id.fragment_list_restaurants_row_item_tv_restaurant_name)
        TextView fragmentListRestaurantsRowItemTvRestaurantName;
        @BindView(R.id.fragment_list_restaurants_row_item_tv_restaurant_state)
        TextView fragmentListRestaurantsRowItemTvRestaurantState;
        @BindView(R.id.fragment_list_restaurants_row_item_rb_rate)
        RatingBar fragmentListRestaurantsRowItemRbRate;
        @BindView(R.id.fragment_list_restaurants_row_item_tv_mim_order)
        TextView fragmentListRestaurantsRowItemTvMimOrder;
        @BindView(R.id.fragment_list_restaurants_row_item_tv_del_cost)
        TextView fragmentListRestaurantsRowItemTvDelCost;
        @BindView(R.id.fragment_list_restaurants_row_item_ci_image)
        CircleImageView fragmentListRestaurantsRowItemCiImage;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }


}
