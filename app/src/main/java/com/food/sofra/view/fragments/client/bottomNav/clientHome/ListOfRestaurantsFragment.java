package com.food.sofra.view.fragments.client.bottomNav.clientHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.food.sofra.R;
import com.food.sofra.adapter.CitySpinnerAdapter;
import com.food.sofra.adapter.ListOfRestaurantsRvAdapter;
import com.food.sofra.data.model.general.cities.generalListOfCities.CityData;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurant.GeneralListOfRestaurant;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurant.GeneralListOfRestaurantData;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantWithFilter.GeneralListOfRestaurantWithFilter;
import com.food.sofra.utils.GeneralRequest;
import com.food.sofra.utils.OnEndLess;
import com.food.sofra.view.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;


public class ListOfRestaurantsFragment extends BaseFragment {

    Unbinder unbind;
    @BindView(R.id.fragment_list_of_restaurants_sp_city)
    Spinner fragmentListOfRestaurantsSpCity;
    @BindView(R.id.fragment_list_of_restaurants_et_search)
    EditText fragmentListOfRestaurantsEtSearch;
    @BindView(R.id.fragment_list_of_restaurants_rv)
    RecyclerView fragmentListOfRestaurantsRv;
    //----------------------------------------------------------------------------------------------
    private CitySpinnerAdapter citySpinnerAdapter;
    private List<CityData> cityData = new ArrayList<>();
    //----------------------------------------------------------------------------------------------
    private LinearLayoutManager linearLayoutManager;
    private List<GeneralListOfRestaurantData> generalListOfRestaurantData = new ArrayList<>();
    private ListOfRestaurantsRvAdapter listOfRestaurantsRvAdapter;
    //----------------------------------------------------------------------------------------------
    private Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean filter = false;

    //----------------------------------------------------------------------------------------------
    public ListOfRestaurantsFragment() {
        // Required empty public constructor
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_of_restaurants, container,
                false);

        unbind = ButterKnife.bind(this, v);

        citySpinnerAdapter = new CitySpinnerAdapter(getActivity(),cityData);
        GeneralRequest.getSpinnerData(getActivity(),fragmentListOfRestaurantsSpCity
                ,citySpinnerAdapter,
                "Select City",getClient().generalListOfCities());

        init();

        setUpActivity();
        return v;
    }

    //----------------------------------------------------------------------------------------------
    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentListOfRestaurantsRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = onEndLess.current_page;
                        //getCities();
                        if (filter) {
                            onFilter(current_page);
                        }else {
                            getRestaurants(current_page);
                        }
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentListOfRestaurantsRv.addOnScrollListener(onEndLess);


        listOfRestaurantsRvAdapter = new ListOfRestaurantsRvAdapter(getActivity(),getActivity(),
                generalListOfRestaurantData);
        fragmentListOfRestaurantsRv.setAdapter(listOfRestaurantsRvAdapter);

        getRestaurants(1);
    }
    //----------------------------------------------------------------------------------------------
    private void getRestaurants(int page) {

        getClient().generalListOfRestaurant(page).enqueue(new Callback<GeneralListOfRestaurant>() {
            @Override
            public void onResponse(Call<GeneralListOfRestaurant> call,
                                   Response<GeneralListOfRestaurant> response) {
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    generalListOfRestaurantData.addAll(response.body().getData().getData());
                    listOfRestaurantsRvAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<GeneralListOfRestaurant> call, Throwable t) {
            }
        });
    }
    //----------------------------------------------------------------------------------------------
    private void onFilter(int page) {
        filter =true;
        String key = fragmentListOfRestaurantsEtSearch.getText().toString();
        int city = citySpinnerAdapter.selectedId;
        getClient().generalListOfRestaurantWithFilter(key, city, page).enqueue(new Callback<GeneralListOfRestaurantWithFilter>() {
            @Override
            public void onResponse(Call<GeneralListOfRestaurantWithFilter> call, Response<GeneralListOfRestaurantWithFilter> response) {
                try {
                    if (response.body().getStatus()==1) {
                        if (page == 1) {
                            onEndLess.previousTotal=0;
                            onEndLess.current_page = 1;
                            onEndLess.previous_page=1;
                            generalListOfRestaurantData = new ArrayList<>();
                            listOfRestaurantsRvAdapter = new ListOfRestaurantsRvAdapter(getActivity(),getActivity(),
                                    generalListOfRestaurantData);
                            fragmentListOfRestaurantsRv.setAdapter(listOfRestaurantsRvAdapter);
                            getRestaurants(page);
                        }
                        getRestaurants(page);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralListOfRestaurantWithFilter> call, Throwable t) {

            }
        });

    }
    //----------------------------------------------------------------------------------------------

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_list_of_restaurants_btn_search)
    public void onViewClicked() {
        onFilter(1);
    }
}
