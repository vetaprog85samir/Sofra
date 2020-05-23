package com.food.sofra.view.fragments.client.bottomNav.clientMore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.model.client.auth.clientChangePassword.ClientChangePassword;
import com.food.sofra.data.model.restaurant.auth.restaurantChangePassword.RestaurantChangePassword;
import com.food.sofra.view.fragments.BaseFragment;

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


public class ChangePasswordFragment extends BaseFragment {


    @BindView(R.id.fragment_change_password_et_old_pass)
    EditText fragmentChangePasswordEtOldPass;
    @BindView(R.id.fragment_change_password_et_new_pass)
    EditText fragmentChangePasswordEtNewPass;
    @BindView(R.id.fragment_change_password_et_confirm_pass)
    EditText fragmentChangePasswordEtConfirmPass;
    private Unbinder unbinder;


    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_change_password, container, false);
        unbinder = ButterKnife.bind(this, v);
        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_change_password_bt_change)
    public void onViewClicked() {
        String oldPassword = fragmentChangePasswordEtOldPass.getText().toString();
        String newPassword = fragmentChangePasswordEtNewPass.getText().toString();
        String confirmPassword = fragmentChangePasswordEtConfirmPass.getText().toString();
        String apiTokenRest = loadData(getActivity(), "Restaurant_ApiToken");
        String apiTokenClient = loadData(getActivity(), "Client_ApiToken");

        if (loadData(getActivity(), userType).equals(userTypeRestaurant)) {
            getRestaurantPassChange(apiTokenRest, oldPassword, newPassword, confirmPassword);
        } else if (loadData(getActivity(), userType).equals(userTypeRestaurant)) {
            getClientPassChange(apiTokenClient, oldPassword, newPassword, confirmPassword);
        }
    }

    private void getRestaurantPassChange(String apiTokenRest, String oldPassword, String newPassword, String confirmPassword) {
        getClient().restaurantChangePassword(apiTokenRest,oldPassword,newPassword,confirmPassword).enqueue(new Callback<RestaurantChangePassword>() {
            @Override
            public void onResponse(Call<RestaurantChangePassword> call, Response<RestaurantChangePassword> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<RestaurantChangePassword> call, Throwable t) {

            }
        });
    }

    private void getClientPassChange(String apiTokenClient, String oldPassword, String newPassword, String confirmPassword) {
        getClient().clientChangePassword(apiTokenClient,oldPassword,newPassword,confirmPassword).enqueue(new Callback<ClientChangePassword>() {
            @Override
            public void onResponse(Call<ClientChangePassword> call, Response<ClientChangePassword> response) {
                try {
                    if (response.body().getStatus() ==1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}

            }

            @Override
            public void onFailure(Call<ClientChangePassword> call, Throwable t) {

            }
        });
    }
}
