package com.food.sofra.view.fragments.client.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.client.auth.clientLogin.ClientLogin;
import com.food.sofra.data.model.client.auth.clientLogin.ClientUser;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.activities.HomeActivity;
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


public class ClientLoginFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_client_login_text_input_email)
    TextInputLayout fragmentClientLoginTextInputEmail;
    @BindView(R.id.fragment_client_login_text_input_password)
    TextInputLayout fragmentClientLoginTextInputPassword;

    public ClientLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_login, container, false);
        unbinder = ButterKnife.bind(this, v);

        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_client_login_tv_forget_password, R.id.fragment_client_login_layout_enter, R.id.fragment_client_login_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_client_login_tv_forget_password:
                ClientForgetPasswordStepOneFragment clientForgetPasswordStepOneFragment = new ClientForgetPasswordStepOneFragment();
                HelperMethod.replaceFragment(getChildFragmentManager(), R.id.fragment_client_login,clientForgetPasswordStepOneFragment);
                break;
            case R.id.fragment_client_login_layout_enter:
                String email = fragmentClientLoginTextInputEmail.getEditText().getText().toString();
                String password = fragmentClientLoginTextInputPassword.getEditText().getText().toString();
                getClientLogin(email,password);
                break;
            case R.id.fragment_client_login_tv_register:
                ClientRegisterFragment clientRegisterFragment = new ClientRegisterFragment();
                HelperMethod.replaceFragment(getChildFragmentManager(),R.id.fragment_client_register, clientRegisterFragment);
                break;
        }
    }


    private void getClientLogin(String email, String password) {

        getClient().clientLogin(email, password).enqueue(new Callback<ClientLogin>() {
            @Override
            public void onResponse(Call<ClientLogin> call, Response<ClientLogin> response) {
                try{
                    if (response.body().getStatus() == 1) {
                        SharedPreferencesManger.saveClientData(getActivity(),response.body().getClientLoginData());
                        SharedPreferencesManger.saveData(getActivity(),"api_token", response.body().getClientLoginData().getApiToken());
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                }
            }
            @Override
            public void onFailure(Call<ClientLogin> call, Throwable t) {
            }
        });
    }
}
