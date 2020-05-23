package com.food.sofra.view.fragments.restaurant.bottomNav.restaurantOrders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.adapter.RestaurantPreviousOrderAdapter;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.restaurant.orders.restaurantMyOrders.RestaurantMyOrders;
import com.food.sofra.data.model.restaurant.orders.restaurantMyOrders.RestaurantMyOrdersData;
import com.food.sofra.utils.OnEndLess;
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


public class RestaurantPreviousOrdersFragment extends BaseFragment {


    @BindView(R.id.fragment_restaurant_completed_orders_rv)
    RecyclerView fragmentRestaurantCompletedOrdersRv;
    Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int maxPage =0;
    private RestaurantPreviousOrderAdapter adapter;
    private List<RestaurantMyOrdersData> restaurantMyOrdersData = new ArrayList<>();

    public RestaurantPreviousOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_completed_orders, container, false);
        unbinder = ButterKnife.bind(this,v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentRestaurantCompletedOrdersRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getRestaurantCompletedOrders(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentRestaurantCompletedOrdersRv.addOnScrollListener(onEndLess);

        adapter = new RestaurantPreviousOrderAdapter(getActivity(), getActivity(),restaurantMyOrdersData);
        fragmentRestaurantCompletedOrdersRv.setAdapter(adapter);

        getRestaurantCompletedOrders(1);
    }

    private void getRestaurantCompletedOrders(int page) {
        getClient().restaurantMyOrders(SharedPreferencesManger.loadData(getActivity(),"api_token"),"completed", page).enqueue(new Callback<RestaurantMyOrders>() {
            @Override
            public void onResponse(Call<RestaurantMyOrders> call, Response<RestaurantMyOrders> response) {
                maxPage = response.body().getRestaurantMyOrdersPagination().getLastPage();
                restaurantMyOrdersData.addAll(response.body().getRestaurantMyOrdersPagination().getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RestaurantMyOrders> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
