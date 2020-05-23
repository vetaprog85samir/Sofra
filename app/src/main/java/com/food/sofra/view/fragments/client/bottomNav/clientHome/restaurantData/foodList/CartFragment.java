package com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.foodList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.adapter.CartAdapter;
import com.food.sofra.data.local.room.Item;
import com.food.sofra.data.local.room.RoomDao;
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrders;
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrdersData;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.utils.OnEndLess;
import com.food.sofra.view.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

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
import static com.food.sofra.data.local.room.RoomManager.getInstance;


public class CartFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_cart_rv)
    RecyclerView fragmentCartRv;
    @BindView(R.id.fragment_cart_tv)
    TextView fragmentCartTv;
    private List<Item> items;
    RoomDao roomDao;
    private double total;
    private ConfirmOrderFragment confirmOrderFragment;
    //private OnEndLess onEndLess;
    private LinearLayoutManager linearLayoutManager;
    //private int maxPage = 0;
    private CartAdapter adapter;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {

        roomDao = getInstance(getActivity()).roomDao();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                items = roomDao.getAll();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        linearLayoutManager = new LinearLayoutManager(getActivity());
                        fragmentCartRv.setLayoutManager(linearLayoutManager);
                        adapter = new CartAdapter(getActivity(), getActivity(), items, CartFragment.this);
                        total = 0.0;
                        for (int i = 0; i < items.size(); i++) {
                            total = total + items.get(i).getQuantity() * items.get(i).getCost();
                        }
                        fragmentCartTv.setText(String.valueOf(total));
                    }
                });
            }
        });

        //linearLayoutManager = new LinearLayoutManager(getActivity());
        //fragmentCartRv.setLayoutManager(linearLayoutManager);
//
        //onEndLess = new OnEndLess(linearLayoutManager, 1) {
        //    @Override
        //    public void onLoadMore(int current_page) {
//
        //        if (current_page <= maxPage) {
        //            if (maxPage != 0 && current_page != 1) {
        //                onEndLess.previous_page = current_page;
        //                getMyCartOrders();
        //            } else {
        //                onEndLess.current_page = onEndLess.previous_page;
        //            }
        //        } else {
        //            onEndLess.current_page = onEndLess.previous_page;
        //        }
        //    }
        //};
        //fragmentCartRv.addOnScrollListener(onEndLess);
//
        //adapter = new CartAdapter(getActivity(),getActivity(), clientMyOrdersData);
        //fragmentCartRv.setAdapter(adapter);
//
        //getMyCartOrders();
    }

    public void updateUi(List<Item> data) {
        for (int i = 0; i < data.size(); i++) {
            total = 0.0;
            total = total + items.get(i).getQuantity() * items.get(i).getCost();
        }
        fragmentCartTv.setText(String.valueOf(total));


    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_cart_btn_confirm_order, R.id.fragment_cart_btn_add_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_cart_btn_confirm_order:
                confirmOrderFragment = new ConfirmOrderFragment();
                confirmOrderFragment.itemData = items;
                HelperMethod.replaceFragment(getChildFragmentManager(), R.id.fragment_cart, confirmOrderFragment);
                break;
            case R.id.fragment_cart_btn_add_more:
                break;
        }
    }

    //private void getMyCartOrders() {
    //    String open = "OPEN";
    //    getClient().clientMyOrders("HRbqKFSaq5ZpsOKITYoztpFZNylmzL9elnlAThxZSZ52QWqVBIj8Rdq7RhoB"
    //            ,open).enqueue(new Callback<ClientMyOrders>() {
    //        @Override
    //        public void onResponse(Call<ClientMyOrders> call, Response<ClientMyOrders> response) {
    //            clientMyOrdersData.addAll(response.body().getClientMyOrdersPagination().getData());
    //            adapter.notifyDataSetChanged();
    //        }
//
    //        @Override
    //        public void onFailure(Call<ClientMyOrders> call, Throwable t) {
//
    //        }
    //    });
    //}


}
