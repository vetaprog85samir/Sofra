package com.food.sofra.view.fragments.client.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.model.client.auth.resetPassword.ResetPassword;
import com.food.sofra.data.model.restaurant.auth.restaurantResetPassword.RestaurantResetPassword;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.loadData;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.userType;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.userTypeRestaurant;
import static com.food.sofra.utils.HelperMethod.replaceFragment;


public class ClientForgetPasswordStepOneFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_client_forget_password_step_one_ti_email)
    TextInputLayout fragmentClientForgetPasswordStepOneTiEmail;
    private static String email;

    public ClientForgetPasswordStepOneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_forget_password_step_one, container, false);
        unbinder = ButterKnife.bind(this, v);
        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_client_forget_password_step_one_btn_send)
    public void onViewClicked() {
        email = fragmentClientForgetPasswordStepOneTiEmail.getEditText().getText().toString();

        try {
            if (loadData(getActivity(), userType).equals(userTypeRestaurant)) {
                getRestaurantPasswordCode(email);
                ClientForgetPasswordStepTwoFragment clientForgetPasswordStepTwoFragment = new ClientForgetPasswordStepTwoFragment();
                clientForgetPasswordStepTwoFragment.email = email;
                replaceFragment(getChildFragmentManager(), R.id.fragment_client_forget_password_step_one,
                        clientForgetPasswordStepTwoFragment);
            } else {
                getClientPasswordCode(email);
                ClientForgetPasswordStepTwoFragment clientForgetPasswordStepTwoFragment = new ClientForgetPasswordStepTwoFragment();
                clientForgetPasswordStepTwoFragment.email = email;
                replaceFragment(getChildFragmentManager(), R.id.fragment_client_forget_password_step_one,
                        clientForgetPasswordStepTwoFragment);

            }
        } catch (Exception e) {

        }
    }


    private void getRestaurantPasswordCode( String email) {
        getClient().restaurantResetPassword(email).enqueue(new Callback<RestaurantResetPassword>() {
            @Override
            public void onResponse(Call<RestaurantResetPassword> call, Response<RestaurantResetPassword> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getRestaurantResetPasswordData().getCode() +
                                response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){}
            }
            @Override
            public void onFailure(Call<RestaurantResetPassword> call, Throwable t) {
            }
        });
    }

    private void getClientPasswordCode(String email) {
        getClient().clientResetPassword(email).enqueue(new Callback<ResetPassword>() {
            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getResetPasswordData().getCode() +
                                response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {

            }
        });
    }
}
