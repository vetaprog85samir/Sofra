package com.food.sofra.view.fragments.restaurant.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.food.sofra.R;
import com.food.sofra.adapter.CitySpinnerAdapter;
import com.food.sofra.adapter.RegionSpinnerAdapter;
import com.food.sofra.data.model.general.cities.generalListOfCities.CityData;
import com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId.RegionData;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.utils.GeneralRequest.getSpinnerData;
import static com.food.sofra.utils.HelperMethod.replaceFragment;


public class RestaurantRegisterFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_restaurant_register_ti_name)
    TextInputLayout fragmentRestaurantRegisterTiName;
    @BindView(R.id.fragment_restaurant_register_ti_email)
    TextInputLayout fragmentRestaurantRegisterTiEmail;
    @BindView(R.id.fragment_restaurant_register_delivery_time)
    TextInputLayout fragmentRestaurantRegisterDeliveryTime;
    @BindView(R.id.fragment_restaurant_register_sp_city)
    Spinner fragmentRestaurantRegisterSpCity;
    @BindView(R.id.fragment_restaurant_register_sp_region)
    Spinner fragmentRestaurantRegisterSpRegion;
    @BindView(R.id.fragment_restaurant_register_ti_password)
    TextInputLayout fragmentRestaurantRegisterTiPassword;
    @BindView(R.id.fragment_restaurant_register_ti_confirm_password)
    TextInputLayout fragmentRestaurantRegisterTiConfirmPassword;
    @BindView(R.id.fragment_restaurant_register_ti_min_charge)
    TextInputLayout fragmentRestaurantRegisterTiMinCharge;
    @BindView(R.id.fragment_restaurant_register_ti_delivery_fees)
    TextInputLayout fragmentRestaurantRegisterTiDeliveryFees;

    CitySpinnerAdapter citySpinnerAdapter;
    RegionSpinnerAdapter regionSpinnerAdapter;
    private List<CityData> cityData = new ArrayList<>();
    private List<RegionData> regionData = new ArrayList<>();

    public RestaurantRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_register, container, false);
        unbinder = ButterKnife.bind(this, v);

        citySpinnerAdapter = new CitySpinnerAdapter(getActivity(), cityData);
        regionSpinnerAdapter = new RegionSpinnerAdapter(getActivity(), regionData);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getSpinnerData(getActivity(), fragmentRestaurantRegisterSpCity, citySpinnerAdapter,
                        "Select City", getClient().generalListOfCities());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        getSpinnerData(getClient().generalListOfRegionsByCityId(citySpinnerAdapter.selectedId),
                fragmentRestaurantRegisterSpRegion, regionSpinnerAdapter, regionSpinnerAdapter.selectedId,
                "Select Region",listener);

        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_restaurant_register_btn_continue)
    public void onViewClicked() {
        String name = fragmentRestaurantRegisterTiName.getEditText().getText().toString();
        String email = fragmentRestaurantRegisterTiEmail.getEditText().getText().toString();
        String deliveryTime = fragmentRestaurantRegisterDeliveryTime.getEditText().getText().toString();
        String password = fragmentRestaurantRegisterTiPassword.getEditText().getText().toString();
        String confirmPassword = fragmentRestaurantRegisterTiConfirmPassword.getEditText().getText().toString();
        String minCharge = fragmentRestaurantRegisterTiMinCharge.getEditText().getText().toString();
        String deliveryFees = fragmentRestaurantRegisterTiDeliveryFees.getEditText().getText().toString();
        String regionId = String.valueOf(regionSpinnerAdapter.selectedId);

        RestaurantContactFragment restaurantContactFragment = new RestaurantContactFragment();
        restaurantContactFragment.name = name ;
        restaurantContactFragment.email = email;
        restaurantContactFragment.deliveryTime = deliveryTime ;
        restaurantContactFragment.password = password ;
        restaurantContactFragment.confirmPassword = confirmPassword ;
        restaurantContactFragment.minCharge = minCharge ;
        restaurantContactFragment.deliveryFees = deliveryFees ;
        restaurantContactFragment.regionId = regionId ;

        replaceFragment(getChildFragmentManager(), R.id.fragment_restaurant_register,
                restaurantContactFragment);
    }
}
