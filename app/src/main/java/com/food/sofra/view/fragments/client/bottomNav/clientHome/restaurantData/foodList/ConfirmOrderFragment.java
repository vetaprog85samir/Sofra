package com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.foodList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.local.room.Item;
import com.food.sofra.data.local.room.RoomDao;
import com.food.sofra.data.model.client.orders.clientNewOrder.ClientNewOrder;
import com.food.sofra.data.model.restaurant.auth.restaurantLogin.RestaurantLoginData;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.data.local.room.RoomManager.getInstance;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.clientData;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.loadRestaurantData;


public class ConfirmOrderFragment extends BaseFragment {


    public double cost = 0.0, total = 0.0;
    public List<Item> itemData;
    public List<Integer> item = new ArrayList<>();
    public List<String> note = new ArrayList<>();
    public List<Integer> quantity = new ArrayList<>();
    public String name;
    public int deliveryFees;
    Unbinder unbinder;
    @BindView(R.id.fragment_confirm_order_notes)
    TextInputLayout fragmentConfirmOrderNotes;
    @BindView(R.id.fragment_confirm_order_tv_address)
    TextView fragmentConfirmOrderTvAddress;
    @BindView(R.id.fragment_confirm_client_order_rb_cash)
    RadioButton fragmentConfirmClientOrderRbCash;
    @BindView(R.id.fragment_confirm_client_order_rb_online)
    RadioButton fragmentConfirmClientOrderRbOnline;
    @BindView(R.id.fragment_confirm_client_order_rb_group)
    RadioGroup fragmentConfirmClientOrderRbGroup;
    @BindView(R.id.fragment_confirm_order_tv_cost)
    TextView fragmentConfirmOrderTvCost;
    @BindView(R.id.fragment_confirm_order_delivery_fees)
    TextView fragmentConfirmOrderDeliveryFees;
    @BindView(R.id.fragment_confirm_order_tv_total)
    TextView fragmentConfirmOrderTvTotal;
    private int payMethodId;
    private RoomDao roomDao;

    public ConfirmOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_client_order, container, false);
        unbinder = ButterKnife.bind(this, v);
        init();
        //getClientMyOrders();
        setUpActivity();
        return v;
    }

    private void init() {
        roomDao = getInstance(getActivity()).roomDao();
        fragmentConfirmClientOrderRbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fragment_confirm_client_order_rb_cash:
                        payMethodId = 1;
                        break;

                    case R.id.fragment_confirm_client_order_rb_online:
                        payMethodId = 2;
                        break;
                }
            }
        });

        for (int i = 0; i < itemData.size(); i++) {
            cost = cost + itemData.get(i).getQuantity() * itemData.get(i).getCost();
            note.add(itemData.get(i).getNote());
            quantity.add(itemData.get(i).getQuantity());
            item.add(itemData.get(i).getItemId());
        }

        fragmentConfirmOrderTvCost.setText(String.valueOf(cost));
        fragmentConfirmOrderDeliveryFees.setText(String.valueOf(deliveryFees));
        total = cost + deliveryFees;
        fragmentConfirmOrderTvTotal.setText(String.valueOf(total));
    }

    @OnClick(R.id.fragment_confirm_order_btn_confirm)
    public void onViewClicked() {
        getClientOrders();
        deleteAll();
    }

    private void getClientOrders() {
        String notes = fragmentConfirmOrderNotes.getEditText().getText().toString();
        String address = fragmentConfirmOrderTvAddress.getText().toString();
        RestaurantLoginData restaurantLoginData = loadRestaurantData(getActivity(), clientData);

        getClient().clientNewOrder(itemData.get(0).getRestaurantId(),notes,address,payMethodId,
                restaurantLoginData.getUser().getPhone(),restaurantLoginData.getUser().getName(),
                restaurantLoginData.getApiToken(),
                item,quantity,note).enqueue(new Callback<ClientNewOrder>() {
            @Override
            public void onResponse(Call<ClientNewOrder> call, Response<ClientNewOrder> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}

            }

            @Override
            public void onFailure(Call<ClientNewOrder> call, Throwable t) {
            }
        });
    }

    private void deleteAll() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                roomDao.deleteAll();
            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }

}
