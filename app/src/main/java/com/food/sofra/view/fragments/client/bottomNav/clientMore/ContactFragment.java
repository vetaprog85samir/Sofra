package com.food.sofra.view.fragments.client.bottomNav.clientMore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.model.general.generalContactUs.GeneralContactUs;
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


public class ContactFragment extends BaseFragment {


    @BindView(R.id.fragment_contact_us_ti_name)
    TextInputLayout fragmentContactUsTiName;
    @BindView(R.id.fragment_contact_us_email)
    TextInputLayout fragmentContactUsEmail;
    @BindView(R.id.fragment_contact_us_mobile)
    TextInputLayout fragmentContactUsMobile;
    @BindView(R.id.fragment_contact_us_msg)
    TextInputLayout fragmentContactUsMsg;
    @BindView(R.id.fragment_contact_us_rb_complain)
    RadioButton fragmentContactUsRbComplain;
    @BindView(R.id.fragment_contact_us_rb_suggest)
    RadioButton fragmentContactUsRbSuggest;
    @BindView(R.id.fragment_contact_us_rb_Query)
    RadioButton fragmentContactUsRbQuery;
    private Unbinder unbinder;
    private String type;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_contact, container, false);
        unbinder = ButterKnife.bind(this, v);
        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_contact_us_send)
    public void onViewClicked() {
        String name = fragmentContactUsTiName.getEditText().getText().toString();
        String email = fragmentContactUsEmail.getEditText().getText().toString();
        String mobile = fragmentContactUsMobile.getEditText().getText().toString();
        String msg = fragmentContactUsMsg.getEditText().getText().toString();
        if(fragmentContactUsRbComplain.isChecked()){ type = fragmentContactUsRbComplain.getText().toString();}
        else if(fragmentContactUsRbSuggest.isChecked()){type = fragmentContactUsRbSuggest.getText().toString();}
        else if(fragmentContactUsRbQuery.isChecked()){type = fragmentContactUsRbQuery.getText().toString();}
        getContact(name,email,mobile,type,msg);
    }

    private void getContact(String name, String email, String mobile, String type, String msg) {
        getClient().generalContactUs(name,email,mobile,type,msg).enqueue(new Callback<GeneralContactUs>() {
            @Override
            public void onResponse(Call<GeneralContactUs> call, Response<GeneralContactUs> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralContactUs> call, Throwable t) {

            }
        });
    }
}
