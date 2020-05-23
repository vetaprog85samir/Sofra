package com.food.sofra.view.fragments.restaurant.bottomNav.restaurantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.food.sofra.R;
import com.food.sofra.adapter.CitySpinnerAdapter;
import com.food.sofra.adapter.RegionSpinnerAdapter;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.general.cities.generalListOfCities.CityData;
import com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId.RegionData;
import com.food.sofra.data.model.restaurant.auth.restaurantLogin.RestaurantLoginData;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.restaurantData;
import static com.food.sofra.utils.GeneralRequest.getSpinnerData;
import static com.food.sofra.utils.HelperMethod.onLoadImageFromUrl;
import static com.food.sofra.utils.HelperMethod.openGallery;


public class RestaurantProfileInfoFragment extends BaseFragment {


    @BindView(R.id.fragment_restaurant_profile_info_logo)
    ImageView fragmentRestaurantProfileInfoLogo;
    @BindView(R.id.fragment_restaurant_profile_info_et_name)
    TextInputEditText fragmentRestaurantProfileInfoEtName;
    @BindView(R.id.fragment_restaurant_profile_info_rt_email)
    TextInputEditText fragmentRestaurantProfileInfoRtEmail;
    @BindView(R.id.fragment_restaurant_profile_info_sp_city)
    Spinner fragmentRestaurantProfileInfoSpCity;
    @BindView(R.id.fragment_restaurant_profile_info_sp_region)
    Spinner fragmentRestaurantProfileInfoSpRegion;
    @BindView(R.id.fragment_restaurant_profile_info_et_cost)
    TextInputEditText fragmentRestaurantProfileInfoEtCost;
    private Unbinder unbinder;
    private RestaurantLoginData restaurantLoginData;
    private CitySpinnerAdapter citySpinnerAdapter;
    private RegionSpinnerAdapter regionSpinnerAdapter;
    private ArrayList<AlbumFile> image = new ArrayList<>();
    private String path;
    private List<CityData> cityData = new ArrayList<>();
    private List<RegionData> regionData = new ArrayList<>();
    private RestaurantProfileProceedInfoFragment restaurantProfileProceedInfoFragment;

    public RestaurantProfileInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_profile_info, container, false);
        unbinder = ButterKnife.bind(this, v);
        init();
        setUpActivity();
        return v;
    }

    private void init() {
        citySpinnerAdapter = new CitySpinnerAdapter(getActivity(), cityData);
        regionSpinnerAdapter = new RegionSpinnerAdapter(getActivity(), regionData);

        AdapterView.OnItemSelectedListener onCitySelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    getSpinnerData(getClient().generalListOfCities(), fragmentRestaurantProfileInfoSpRegion,
                            citySpinnerAdapter, restaurantLoginData.getUser().getId(), "Select");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        getSpinnerData(getClient().generalListOfCities(), fragmentRestaurantProfileInfoSpCity, citySpinnerAdapter,
                Integer.parseInt(restaurantLoginData.getUser().getRegionId()), "Select", onCitySelectedListener);


        restaurantLoginData = SharedPreferencesManger.loadRestaurantData(getActivity(),restaurantData);

        onLoadImageFromUrl(fragmentRestaurantProfileInfoLogo,restaurantLoginData.getUser().getPhotoUrl(),getActivity());
        fragmentRestaurantProfileInfoEtName.setText(restaurantLoginData.getUser().getName());
        fragmentRestaurantProfileInfoRtEmail.setText(restaurantLoginData.getUser().getEmail());
        fragmentRestaurantProfileInfoEtCost.setText(restaurantLoginData.getUser().getDeliveryCost());
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_restaurant_profile_info_logo, R.id.fragment_restaurant_profile_info_btn_proceed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_restaurant_profile_info_logo:
                openGallery(getActivity(), 1, image, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        image.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(fragmentRestaurantProfileInfoLogo, path, getActivity());
                    }
                });
                break;
            case R.id.fragment_restaurant_profile_info_btn_proceed:

                String name =  fragmentRestaurantProfileInfoEtName.getText().toString();
                String email = fragmentRestaurantProfileInfoRtEmail.getText().toString();
                String cost = fragmentRestaurantProfileInfoEtCost.getText().toString();
                String cityId = String.valueOf(citySpinnerAdapter.selectedId);
                String regionId = String.valueOf(regionSpinnerAdapter.selectedId);

                restaurantProfileProceedInfoFragment.name =name;
                restaurantProfileProceedInfoFragment.email = email;
                restaurantProfileProceedInfoFragment.cost = cost;
                restaurantProfileProceedInfoFragment.cityId = cityId;
                restaurantProfileProceedInfoFragment.regionId = regionId;
                restaurantProfileProceedInfoFragment.path = path;

                HelperMethod.replaceFragment(getChildFragmentManager(),R.id.fragment_restaurant_profile_info,
                        restaurantProfileProceedInfoFragment);
                break;
        }
    }

}
