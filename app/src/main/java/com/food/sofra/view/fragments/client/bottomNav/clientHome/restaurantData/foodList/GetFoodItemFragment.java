package com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.foodList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.local.room.Item;
import com.food.sofra.data.local.room.RoomDao;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItemsData;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.fragments.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.food.sofra.data.local.room.RoomManager.getInstance;


public class GetFoodItemFragment extends BaseFragment {

    public GeneralListOfRestaurantItemsData itemsData;
    @BindView(R.id.fragment_show_food_list_row_item_iv)
    ImageView fragmentShowFoodListRowItemIv;
    @BindView(R.id.fragment_show_food_list_row_item_name)
    TextView fragmentShowFoodListRowItemName;
    @BindView(R.id.fragment_show_food_list_row_item_description)
    TextView fragmentShowFoodListRowItemDescription;
    @BindView(R.id.fragment_show_food_list_row_item_cost)
    TextView fragmentShowFoodListRowItemCost;
    @BindView(R.id.fragment_show_food_list_row_item_ti_order)
    TextInputLayout fragmentShowFoodListRowItemTiOrder;
    @BindView(R.id.fragment_show_food_list_row_item_tv_quantity)
    TextView fragmentShowFoodListRowItemTvQuantity;
    private Unbinder unbinder;
    private RoomDao roomDao;
    private int quantity;

    public GetFoodItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_get_food_item, container, false);
        unbinder = ButterKnife.bind(this, v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        HelperMethod.onLoadImageFromUrl(fragmentShowFoodListRowItemIv, itemsData.getPhotoUrl(),
                getActivity());
        fragmentShowFoodListRowItemName.setText(itemsData.getName());
        fragmentShowFoodListRowItemDescription.setText(itemsData.getDescription());
        fragmentShowFoodListRowItemCost.setText(itemsData.getPrice());
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_show_food_list_row_item_btn_add, R.id.fragment_show_food_list_row_item_btn_minus, R.id.fragment_show_food_list_row_item_btn_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_show_food_list_row_item_btn_add:
                quantity = quantity ++;
                fragmentShowFoodListRowItemTvQuantity.setText(String.valueOf(quantity));
                break;
            case R.id.fragment_show_food_list_row_item_btn_minus:
                if (quantity>1){
                    quantity = quantity --;
                    fragmentShowFoodListRowItemTvQuantity.setText(String.valueOf(quantity));
                }
                break;
            case R.id.fragment_show_food_list_row_item_btn_cart:
                roomDao = getInstance(getActivity()).roomDao();
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        Item item = new Item(itemsData.getId(),Integer.parseInt(itemsData.getRestaurantId()),itemsData.getName(),
                                Integer.parseInt(fragmentShowFoodListRowItemTvQuantity.getText().toString()),
                                itemsData.getPhotoUrl(),fragmentShowFoodListRowItemTiOrder.getEditText().getText().toString(),
                                Double.parseDouble(itemsData.getPrice()));
                        roomDao.add(item);
                    }
                });
                Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
