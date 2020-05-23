package com.food.sofra.view.fragments.client.bottomNav;

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
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.client.auth.clientRegister.ClientRegister;
import com.food.sofra.data.model.client.auth.clientRegister.ClientRegisterData;
import com.food.sofra.data.model.general.cities.generalListOfCities.CityData;
import com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId.RegionData;
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
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.clientData;
import static com.food.sofra.utils.GeneralRequest.getSpinnerData;
import static com.food.sofra.utils.HelperMethod.convertFileToMultipart;
import static com.food.sofra.utils.HelperMethod.convertToRequestBody;
import static com.food.sofra.utils.HelperMethod.onLoadImageFromUrl;
import static com.food.sofra.utils.HelperMethod.openGallery;


public class ClientProfileFragment extends BaseFragment {


    Unbinder unbinder;

    @BindView(R.id.fragment_client_profile_iv)
    ImageView fragmentClientProfileIv;
    @BindView(R.id.fragment_client_profile_et_name)
    TextInputLayout fragmentClientProfileEtName;
    @BindView(R.id.fragment_client_profile_et_email)
    TextInputLayout fragmentClientProfileEtEmail;
    @BindView(R.id.fragment_client_profile_et_call)
    TextInputLayout fragmentClientProfileEtCall;
    @BindView(R.id.fragment_client_profile_sp_city)
    Spinner fragmentClientProfileSpCity;
    @BindView(R.id.fragment_client_profile_sp_region)
    Spinner fragmentClientProfileSpRegion;

    private RegionSpinnerAdapter regionSpinnerAdapter;
    private CitySpinnerAdapter citySpinnerAdapter;
    private List<CityData> cityData = new ArrayList<>();
    private List<RegionData> regionData = new ArrayList<>();
    private ArrayList<AlbumFile> image = new ArrayList<>();
    private String path;

    ClientRegisterData clientRegisterData;

    public ClientProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_profile, container, false);
        unbinder = ButterKnife.bind(this, v);
        getClientProfile();
        setUpActivity();
        return v;
    }

    private void getClientProfile() {
        clientRegisterData = SharedPreferencesManger.loadClientRegisterData(getActivity(), clientData);

        fragmentClientProfileEtName.getEditText().setText(clientRegisterData.getUser().getName());
        fragmentClientProfileEtEmail.getEditText().setText(clientRegisterData.getUser().getEmail());
        fragmentClientProfileEtCall.getEditText().setText(clientRegisterData.getUser().getPhone());
        onLoadImageFromUrl(fragmentClientProfileIv, clientRegisterData.getUser().getPhotoUrl(), getActivity());


        citySpinnerAdapter = new CitySpinnerAdapter(getActivity(), cityData);
        regionSpinnerAdapter = new RegionSpinnerAdapter(getActivity(), regionData);
        AdapterView.OnItemSelectedListener onCitySelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    getSpinnerData(getClient().generalListOfCities(), fragmentClientProfileSpRegion,
                            citySpinnerAdapter, clientRegisterData.getUser().getId(), "Select");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        getSpinnerData(getClient().generalListOfCities(), fragmentClientProfileSpCity, citySpinnerAdapter,
                clientRegisterData.getUser().getRegionId(), "Select", onCitySelectedListener);


    }

    @Override
    public void onBack() {
        super.onBack();
    }


    @OnClick({R.id.fragment_client_profile_iv, R.id.fragment_client_profile_layout_btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_client_profile_iv:
                openGallery(getActivity(), 1, image, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        image.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(fragmentClientProfileIv, path, getActivity());
                    }
                });
                break;
            case R.id.fragment_client_profile_layout_btn_change:
                changeProfile();
                break;
        }
    }

    private void changeProfile() {
        String name = fragmentClientProfileEtName.getEditText().getText().toString();
        String email = fragmentClientProfileEtEmail.getEditText().getText().toString();
        String call = fragmentClientProfileEtCall.getEditText().getText().toString();
        String regionId = String.valueOf(regionSpinnerAdapter.selectedId);

        getClient().editClientProfile(convertToRequestBody(clientRegisterData.getApiToken()), convertToRequestBody(name), convertToRequestBody(email),
                convertToRequestBody(call), convertToRequestBody(regionId),
                convertFileToMultipart(path, "profile_image")).enqueue(new Callback<ClientRegister>() {
            @Override
            public void onResponse(Call<ClientRegister> call, Response<ClientRegister> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        SharedPreferencesManger.saveClientRegisterData(getActivity(), response.body().getClientRegisterData());
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }

                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ClientRegister> call, Throwable t) {

            }
        });
    }
}
