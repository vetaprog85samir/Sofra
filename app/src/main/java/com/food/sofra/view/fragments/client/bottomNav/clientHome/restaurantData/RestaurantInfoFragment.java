package com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.view.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RestaurantInfoFragment extends BaseFragment {

    public String state;
    public String minimumCharge;
    public String deliveryCost;
    public String city;
    public String region;
    @BindView(R.id.fragment_store_info_state)
    TextView fragmentStoreInfoState;
    @BindView(R.id.fragment_store_info_city)
    TextView fragmentStoreInfoCity;
    @BindView(R.id.fragment_store_info_region)
    TextView fragmentStoreInfoRegion;
    @BindView(R.id.fragment_store_info_minimumCharge)
    TextView fragmentStoreInfoMinimumCharge;
    @BindView(R.id.fragment_store_info_deliveryCost)
    TextView fragmentStoreInfoDeliveryCost;
    private Unbinder unbinder;


    public RestaurantInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store_info, container, false);
        unbinder = ButterKnife.bind(this, v);


        fragmentStoreInfoState.setText(state);
        fragmentStoreInfoCity.setText(city);
        fragmentStoreInfoCity.setText(region);
        fragmentStoreInfoCity.setText(minimumCharge);
        fragmentStoreInfoCity.setText(deliveryCost);

        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
