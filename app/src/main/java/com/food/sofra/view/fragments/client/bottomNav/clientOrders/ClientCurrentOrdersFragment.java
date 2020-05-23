package com.food.sofra.view.fragments.client.bottomNav.clientOrders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.adapter.ClientCurrentOrderAdapter;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrders;
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrdersData;
import com.food.sofra.data.model.client.orders.clientNewOrder.ClientNewOrder;
import com.food.sofra.data.model.client.orders.clientNewOrder.ClientNewOrderData;
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


public class ClientCurrentOrdersFragment extends BaseFragment {


    @BindView(R.id.fragment_current_orders_rv)
    RecyclerView fragmentCurrentOrdersRv;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private ClientCurrentOrderAdapter adapter;
    private List<ClientMyOrdersData> clientMyOrdersData = new ArrayList<>();
    Unbinder unbinder;
    private BaseActivity activity;

    public ClientCurrentOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_orders, container, false);
        unbinder = ButterKnife.bind(this, v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentCurrentOrdersRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getClientCurrentOrders(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentCurrentOrdersRv.addOnScrollListener(onEndLess);

        adapter = new ClientCurrentOrderAdapter(getActivity(),getActivity(),clientMyOrdersData);
        fragmentCurrentOrdersRv.setAdapter(adapter);

        getClientCurrentOrders(1);
    }

    private void getClientCurrentOrders(int page) {

        getClient().clientMyOrders(SharedPreferencesManger.loadData(activity,"api_token"),
                "current",page).enqueue(new Callback<ClientMyOrders>() {
            @Override
            public void onResponse(Call<ClientMyOrders> call, Response<ClientMyOrders> response) {
                if (response.body().getStatus()==1) {
                    maxPage = response.body().getClientMyOrdersPagination().getLastPage();
                    clientMyOrdersData.addAll(response.body().getClientMyOrdersPagination().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ClientMyOrders> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
