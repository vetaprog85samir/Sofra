package com.food.sofra.view.fragments.client.bottomNav.clientMore.ClientOffers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.adapter.ClientShowOffersListAdapter;
import com.food.sofra.data.model.restaurant.offers.restaurantMyOffer.RestaurantMyOffer;
import com.food.sofra.data.model.restaurant.offers.restaurantMyOffer.RestaurantMyOfferData;
import com.food.sofra.utils.OnEndLess;
import com.food.sofra.view.activities.BaseActivity;
import com.food.sofra.view.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.loadData;


public class ClientShowOffersListFragment extends BaseFragment {


    @BindView(R.id.fragment_client_show_offers_list_rv)
    RecyclerView fragmentClientShowOffersListRv;
    private Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private List<RestaurantMyOfferData> restaurantMyOfferData = new ArrayList<>();
    private ClientShowOffersListAdapter adapter;
    private BaseActivity activity;

    public ClientShowOffersListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_show_offers_list, container, false);
        unbinder = ButterKnife.bind(this,v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentClientShowOffersListRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getClientOffersList(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentClientShowOffersListRv.addOnScrollListener(onEndLess);

        adapter = new ClientShowOffersListAdapter(getActivity(),
                getActivity(),restaurantMyOfferData);
        fragmentClientShowOffersListRv.setAdapter(adapter);

        getClientOffersList(1);
    }

    private void getClientOffersList(int page) {

        getClient().restaurantMyOffer(loadData(activity,"api_token"),page).enqueue(new Callback<RestaurantMyOffer>() {
            @Override
            public void onResponse(Call<RestaurantMyOffer> call, Response<RestaurantMyOffer> response) {
                restaurantMyOfferData.addAll(response.body().getRestaurantMyOfferPagination().getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RestaurantMyOffer> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
