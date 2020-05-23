package com.food.sofra.view.fragments.client.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.adapter.CitySpinnerAdapter;
import com.food.sofra.adapter.RegionSpinnerAdapter;
import com.food.sofra.data.model.client.auth.clientRegister.ClientRegister;
import com.food.sofra.data.model.general.cities.generalListOfCities.CityData;
import com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId.RegionData;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.activities.HomeActivity;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.utils.GeneralRequest.getSpinnerData;
import static com.food.sofra.utils.HelperMethod.convertFileToMultipart;
import static com.food.sofra.utils.HelperMethod.convertToRequestBody;
import static com.food.sofra.utils.HelperMethod.onLoadImageFromUrl;
import static com.food.sofra.utils.HelperMethod.openGallery;


public class ClientRegisterFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_client_register_iv_client_photo)
    ImageView fragmentClientRegisterIvClientPhoto;
    @BindView(R.id.fragment_client_register_ti_name)
    TextInputLayout fragmentClientRegisterTiName;
    @BindView(R.id.fragment_client_register_ti_email)
    TextInputLayout fragmentClientRegisterTiEmail;
    @BindView(R.id.fragment_client_register_ti_mobile_number)
    TextInputLayout fragmentClientRegisterTiMobileNumber;
    @BindView(R.id.fragment_client_register_sp_city)
    Spinner fragmentClientRegisterSpCity;
    @BindView(R.id.fragment_client_register_sp_region)
    Spinner fragmentClientRegisterSpRegion;
    @BindView(R.id.fragment_client_register_ti_password)
    TextInputLayout fragmentClientRegisterTiPassword;
    @BindView(R.id.fragment_client_register_ti_confirm_password)
    TextInputLayout fragmentClientRegisterTiConfirmPassword;

    CitySpinnerAdapter citySpinnerAdapter;
    RegionSpinnerAdapter regionSpinnerAdapter;
    private List<CityData> cityData = new ArrayList<>();
    private List<RegionData> regionData = new ArrayList<>();

    private ArrayList<AlbumFile> images = new ArrayList<>();
    private String path;

    public ClientRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_register, container, false);
        unbinder = ButterKnife.bind(this, v);

        citySpinnerAdapter = new CitySpinnerAdapter(getActivity(), cityData);
        regionSpinnerAdapter = new RegionSpinnerAdapter(getActivity(), regionData);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getSpinnerData(getActivity(),fragmentClientRegisterSpCity,citySpinnerAdapter,"Select City",
                        getClient().generalListOfCities());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        getSpinnerData(getClient().generalListOfRegionsByCityId(citySpinnerAdapter.selectedId),
                fragmentClientRegisterSpRegion, regionSpinnerAdapter,citySpinnerAdapter.selectedId,
                "Select Region", listener);

        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }


    @OnClick({R.id.fragment_client_register_iv_client_photo, R.id.fragment_client_register_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_client_register_iv_client_photo:
                openGallery(getActivity(), 1, images, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        images.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(fragmentClientRegisterIvClientPhoto, path, getActivity());
                    }
                });
                break;
            case R.id.fragment_client_register_btn_register:
                getClientRegister();
                break;
        }
    }

    private void getClientRegister() {
        String name = fragmentClientRegisterTiName.getEditText().getText().toString();
        String email = fragmentClientRegisterTiEmail.getEditText().getText().toString();
        String password = fragmentClientRegisterTiPassword.getEditText().getText().toString();
        String passwordConfirmation = fragmentClientRegisterTiConfirmPassword.getEditText().getText().toString();
        String phone = fragmentClientRegisterTiMobileNumber.getEditText().getText().toString();
        String regionId = String.valueOf(regionSpinnerAdapter.selectedId);

        getClient().clientRegister(convertToRequestBody(name), convertToRequestBody(email),
                convertToRequestBody(password), convertToRequestBody(passwordConfirmation),
                convertToRequestBody(phone),convertToRequestBody(regionId),
                convertFileToMultipart(path, "profile_image")).enqueue(new Callback<ClientRegister>() {
            @Override
            public void onResponse(Call<ClientRegister> call, Response<ClientRegister> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }
            @Override
            public void onFailure(Call<ClientRegister> call, Throwable t) {

            }
        });
    }
}
