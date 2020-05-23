package com.food.sofra.view.fragments.restaurant.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.model.restaurant.auth.restaurantLogin.RestaurantLogin;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.activities.HomeActivity;
import com.food.sofra.view.fragments.BaseFragment;
import com.food.sofra.view.fragments.client.auth.ClientForgetPasswordStepOneFragment;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.saveData;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.saveRestaurantData;


public class RestaurantLoginFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_restaurant_login_text_input_email)
    TextInputLayout fragmentRestaurantLoginTextInputEmail;
    @BindView(R.id.fragment_restaurant_login_text_input_password)
    TextInputLayout fragmentRestaurantLoginTextInputPassword;

    public RestaurantLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_login, container, false);
        unbinder = ButterKnife.bind(this, v);
        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_restaurant_login_btn_enter, R.id.fragment_restaurant_login_tv_forget_password, R.id.fragment_restaurant_login_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_restaurant_login_btn_enter:
                String email = fragmentRestaurantLoginTextInputEmail.getEditText().getText().toString();
                String password = fragmentRestaurantLoginTextInputPassword.getEditText().getText().toString();
                getRestaurantLogin(email,password);
                break;
            case R.id.fragment_restaurant_login_tv_forget_password:
                ClientForgetPasswordStepOneFragment clientForgetPasswordStepOneFragment = new ClientForgetPasswordStepOneFragment();
                HelperMethod.replaceFragment(getChildFragmentManager(), R.id.fragment_restaurant_login,clientForgetPasswordStepOneFragment);
                break;
            case R.id.fragment_restaurant_login_tv_register:
                RestaurantRegisterFragment restaurantRegisterFragment = new RestaurantRegisterFragment();
                HelperMethod.replaceFragment(getChildFragmentManager(),R.id.fragment_client_register, restaurantRegisterFragment);
                break;
        }
    }

    private void getRestaurantLogin(String email, String password) {
        getClient().restaurantLogin(email,password).enqueue(new Callback<RestaurantLogin>() {
            @Override
            public void onResponse(Call<RestaurantLogin> call, Response<RestaurantLogin> response) {
                try {
                    if(response.body().getStatus()==1){
                        saveRestaurantData(getActivity(), response.body().getRestaurantLoginData());
                        saveData(getActivity(),"restaurant_id", response.body().getRestaurantLoginData().getUser().getId());
                        saveData(getActivity(), "api_token", response.body().getRestaurantLoginData().getApiToken());
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                }
            }
            @Override
            public void onFailure(Call<RestaurantLogin> call, Throwable t) {
            }
        });
    }
}
