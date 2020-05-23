package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.restaurant.offers.restaurantMyOffer.RestaurantMyOfferData;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.fragments.client.bottomNav.clientMore.ClientOffers.ClientOfferDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class

ClientShowOffersListAdapter extends RecyclerView.Adapter<ClientShowOffersListAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    private List<RestaurantMyOfferData> restaurantMyOfferData = new ArrayList<>();
    private ClientOfferDetailsFragment clientOfferDetailsFragment;


    public ClientShowOffersListAdapter(Context context, Activity activity, List<RestaurantMyOfferData> restaurantMyOfferData) {
        this.context = context;
        this.activity = activity;
        this.restaurantMyOfferData = restaurantMyOfferData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_client_show_offers_list_row_item,
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
        holder.fragmentClientShowOffersListRowItemTv.setText(restaurantMyOfferData.get(position).getName());
        HelperMethod.onLoadImageFromUrl(holder.fragmentClientShowOffersListRowItemIv,restaurantMyOfferData.get(position).getPhotoUrl(),activity);
    }

    private void setAction(ViewHolder holder, int position) {

        //holder.fragmentClientShowOffersListRowItemLayoutDetails.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        HelperMethod.replace(clientOfferDetailsFragment,activity.getFragmentManager(),R.id.fragment_client_show_offers_list_row_item_layout_main)
        //    }
        //});
    }

    @Override
    public int getItemCount() {
        return restaurantMyOfferData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_client_show_offers_list_row_item_tv)
        TextView fragmentClientShowOffersListRowItemTv;
        @BindView(R.id.fragment_client_show_offers_list_row_item_iv)
        ImageView fragmentClientShowOffersListRowItemIv;
        @BindView(R.id.fragment_client_show_offers_list_row_item_layout_details)
        LinearLayout fragmentClientShowOffersListRowItemLayoutDetails;
        @BindView(R.id.fragment_client_show_offers_list_row_item_layout_main)
        RelativeLayout fragmentClientShowOffersListRowItemLayoutMain;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
