package com.food.sofra.view.fragments.restaurant.bottomNav.restaurantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.restaurant.auth.restaurantLogin.RestaurantLoginData;
import com.food.sofra.data.model.restaurant.auth.restaurantRegister.RestaurantRegister;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.utils.HelperMethod.convertFileToMultipart;
import static com.food.sofra.utils.HelperMethod.convertToRequestBody;


public class RestaurantProfileProceedInfoFragment extends BaseFragment {

    public String name;
    public String email;
    public String cost;
    public String cityId;
    public String regionId;
    public String path;
    @BindView(R.id.fragment_restaurant_profile_proceed_info_et_minimum_charge)
    TextInputEditText fragmentRestaurantProfileProceedInfoEtMinimumCharge;
    @BindView(R.id.fragment_restaurant_profile_proceed_info_et_delivery_time)
    TextInputEditText fragmentRestaurantProfileProceedInfoEtDeliveryTime;
    @BindView(R.id.fragment_restaurant_profile_proceed_info_state)
    Switch fragmentRestaurantProfileProceedInfoState;
    @BindView(R.id.fragment_restaurant_profile_proceed_info_phone)
    TextInputEditText fragmentRestaurantProfileProceedInfoPhone;
    @BindView(R.id.fragment_restaurant_profile_proceed_info_whatsapp)
    TextInputEditText fragmentRestaurantProfileProceedInfoWhatsapp;
    private Unbinder unbinder;
    private RestaurantLoginData restaurantLoginData;
    private String state;


    public RestaurantProfileProceedInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_profile_proceed_info, container, false);
        unbinder = ButterKnife.bind(this, v);
        init();
        setUpActivity();
        return v;
    }

    private void init() {
        fragmentRestaurantProfileProceedInfoEtMinimumCharge.setText(restaurantLoginData.getUser().getMinimumCharger());
        fragmentRestaurantProfileProceedInfoEtDeliveryTime.setText(restaurantLoginData.getUser().getDeliveryTime());
        fragmentRestaurantProfileProceedInfoPhone.setText(restaurantLoginData.getUser().getPhone());
        fragmentRestaurantProfileProceedInfoWhatsapp.setText(restaurantLoginData.getUser().getWhatsapp());
        if (restaurantLoginData.getUser().getAvailability().equals("open")) {
            fragmentRestaurantProfileProceedInfoState.setChecked(true);
        }
        else {
            fragmentRestaurantProfileProceedInfoState.setChecked(false);
        }
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_restaurant_profile_proceed_info_btn_edit)
    public void onViewClicked() {
        getRestaurantInfoEdit();
    }

    private void getRestaurantInfoEdit() {
        String minimumCharger = fragmentRestaurantProfileProceedInfoEtMinimumCharge.getText().toString();
        String deliveryTime = fragmentRestaurantProfileProceedInfoEtDeliveryTime.getText().toString();
        String phone = fragmentRestaurantProfileProceedInfoPhone.getText().toString();
        String whatsapp = fragmentRestaurantProfileProceedInfoWhatsapp.getText().toString();
        if (fragmentRestaurantProfileProceedInfoState.isChecked()) {
            state = "open";
        }else {
            state = "close";
        }

        getClient().EditRestaurantProfile(convertToRequestBody(restaurantLoginData.getApiToken()), convertToRequestBody(name),
                convertToRequestBody(email), convertToRequestBody(phone), convertToRequestBody(whatsapp), convertToRequestBody(regionId),
                convertToRequestBody(cost), convertToRequestBody(minimumCharger), convertToRequestBody(state),
                convertFileToMultipart(path, "photo"), convertToRequestBody(deliveryTime)).enqueue(new Callback<RestaurantRegister>() {
            @Override
            public void onResponse(Call<RestaurantRegister> call, Response<RestaurantRegister> response) {
                try {
                    if (response.body().getStatus()==1) {
                        SharedPreferencesManger.saveRestaurantData(getActivity(), response.body().getData());
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){}

            }

            @Override
            public void onFailure(Call<RestaurantRegister> call, Throwable t) {

            }
        });
    }
}
