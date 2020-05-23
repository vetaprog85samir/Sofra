package com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.adapter.ViewPagerWithFragmentAdapter;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurant.GeneralListOfRestaurantData;
import com.food.sofra.view.fragments.BaseFragment;
import com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.foodList.ShowFoodListFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RestaurantDataContainerFragment extends BaseFragment{

    public GeneralListOfRestaurantData generalListOfRestaurantData;
    Unbinder unbinder;

    public RestaurantDataContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_data_container, container, false);
        unbinder = ButterKnife.bind(this, v);

        ViewPager viewPager = v.findViewById(R.id.fragment_restaurant_data_container_vp_pager);
        TabLayout tabLayout = v.findViewById(R.id.fragment_restaurant_data_container_tl_tab);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        ViewPagerWithFragmentAdapter viewPagerWithFragmentAdapter = new ViewPagerWithFragmentAdapter(getChildFragmentManager());

        ShowFoodListFragment showFoodListFragment = new ShowFoodListFragment();
        showFoodListFragment.id = generalListOfRestaurantData.getId();
        viewPagerWithFragmentAdapter.addPager(showFoodListFragment, "Menu");

        ReviewsFragment reviewsFragment = new ReviewsFragment();
        reviewsFragment.id = generalListOfRestaurantData.getId();
        viewPagerWithFragmentAdapter.addPager(reviewsFragment, "Rating");

        RestaurantInfoFragment restaurantInfoFragment = new RestaurantInfoFragment();
        restaurantInfoFragment.state = generalListOfRestaurantData.getAvailability();
        restaurantInfoFragment.deliveryCost = generalListOfRestaurantData.getDeliveryCost();
        restaurantInfoFragment.minimumCharge = generalListOfRestaurantData.getMinimumCharger();
        viewPagerWithFragmentAdapter.addPager(restaurantInfoFragment, "Info");

        viewPager.setAdapter(viewPagerWithFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
