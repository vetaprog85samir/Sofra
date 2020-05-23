package com.food.sofra.view.fragments.client.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.model.client.auth.newPassword.NewPassword;
import com.food.sofra.data.model.restaurant.auth.restaurantNewPassword.RestaurantNewPassword;
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


public class ClientForgetPasswordStepTwoFragment extends BaseFragment {

    Unbinder unbinder;
    public static String email;
    @BindView(R.id.fragment_client_forget_password_step_two_ti_code)
    TextInputLayout fragmentClientForgetPasswordStepTwoTiCode;
    @BindView(R.id.fragment_client_forget_password_step_two_ti_password)
    TextInputLayout fragmentClientForgetPasswordStepTwoTiPassword;
    @BindView(R.id.fragment_client_forget_password_step_two_ti_password_confirmation)
    TextInputLayout fragmentClientForgetPasswordStepTwoTiPasswordConfirmation;

    private String code;
    private String password;
    private String passwordConfirmation;

    public ClientForgetPasswordStepTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_forget_password_step_two, container, false);
        unbinder = ButterKnife.bind(this, v);
        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_client_forget_password_step_two_btn_send)
    public void onViewClicked() {
        code = fragmentClientForgetPasswordStepTwoTiCode.getEditText().getText().toString();
        password = fragmentClientForgetPasswordStepTwoTiPassword.getEditText().getText().toString();
        passwordConfirmation = fragmentClientForgetPasswordStepTwoTiPasswordConfirmation.getEditText().getText().toString();

        try {
            if (loadData(getActivity(),userType).equals(userTypeRestaurant)) {
                getNewRestaurantPassword();
            }else getNewClientPassword();
        }catch (Exception e){}
    }

    private void getNewRestaurantPassword() {
        getClient().restaurantNewPassword(code,password,passwordConfirmation).enqueue(new Callback<RestaurantNewPassword>() {
            @Override
            public void onResponse(Call<RestaurantNewPassword> call, Response<RestaurantNewPassword> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }
            @Override
            public void onFailure(Call<RestaurantNewPassword> call, Throwable t) {
            }
        });
    }

    private void getNewClientPassword() {
        getClient().clientNewPassword(code,password,passwordConfirmation).enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }
            @Override
            public void onFailure(Call<NewPassword> call, Throwable t) {
            }
        });
    }
}
