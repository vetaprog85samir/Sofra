package com.food.sofra.view.fragments.restaurant.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.model.restaurant.auth.restaurantRegister.RestaurantRegister;
import com.food.sofra.view.activities.HomeActivity;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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
import static com.food.sofra.utils.HelperMethod.onLoadImageFromUrl;
import static com.food.sofra.utils.HelperMethod.openGallery;


public class RestaurantContactFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_restaurant_contact_iv_restaurant_photo)
    ImageView fragmentRestaurantContactIvRestaurantPhoto;
    @BindView(R.id.fragment_restaurant_contact_input_phone)
    TextInputLayout fragmentRestaurantContactInputPhone;
    @BindView(R.id.fragment_restaurant_contact_whatsapp)
    TextInputLayout fragmentRestaurantContactWhatsapp;

    public String name;
    public String email;
    public String deliveryTime;
    public String password;
    public String confirmPassword;
    public String minCharge;
    public String deliveryFees;
    public String regionId;

    private String path;
    private ArrayList<AlbumFile> images = new ArrayList<>();

    public RestaurantContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_contact, container, false);
        unbinder = ButterKnife.bind(this, v);
        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_restaurant_contact_iv_restore_photo, R.id.fragment_restaurant_contact_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_restaurant_contact_iv_restore_photo:
                openGallery(getActivity(), 1, images, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        images.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(fragmentRestaurantContactIvRestaurantPhoto, path, getActivity());
                    }
                });
                break;
            case R.id.fragment_restaurant_contact_btn_register:
                getRestaurantRegister();

                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

                break;
        }
    }

    private void getRestaurantRegister() {
        String phone = fragmentRestaurantContactInputPhone.getEditText().getText().toString();
        String whatsapp = fragmentRestaurantContactWhatsapp.getEditText().getText().toString();

        getClient().restaurantRegister(convertToRequestBody(name), convertToRequestBody(email), convertToRequestBody(password),
                convertToRequestBody(confirmPassword), convertToRequestBody(phone),
                convertToRequestBody(whatsapp), convertToRequestBody(regionId), convertToRequestBody(deliveryFees),
                convertToRequestBody(minCharge), convertFileToMultipart(path, "photo"),
                convertToRequestBody(deliveryTime)).enqueue(new Callback<RestaurantRegister>() {
            @Override
            public void onResponse(Call<RestaurantRegister> call, Response<RestaurantRegister> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<RestaurantRegister> call, Throwable t) {
            }
        });
    }
}
