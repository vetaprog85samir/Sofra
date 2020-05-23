package com.food.sofra.view.fragments.restaurant.bottomNav.restaurantHome;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.adapter.ClientShowCategoryFoodItemsAdapter;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItems;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItemsData;
import com.food.sofra.data.model.restaurant.foodItems.restaurantNewFoodItem.RestaurantNewFoodItem;
import com.food.sofra.utils.OnEndLess;
import com.food.sofra.view.fragments.BaseFragment;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.food.sofra.data.api.RetrofitClient.getClient;
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.loadData;
import static com.food.sofra.utils.HelperMethod.convertFileToMultipart;
import static com.food.sofra.utils.HelperMethod.convertToRequestBody;
import static com.food.sofra.utils.HelperMethod.onLoadImageFromUrl;
import static com.food.sofra.utils.HelperMethod.openGallery;


public class ShowCategoryFoodItemsFragment extends BaseFragment {


    @BindView(R.id.fragment_show_category_food_items_rv)
    RecyclerView fragmentShowCategoryFoodItemsRv;
    private Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private ClientShowCategoryFoodItemsAdapter adapter;
    private List<GeneralListOfRestaurantItemsData> generalListOfRestaurantItemsData = new ArrayList<>();
    private ArrayList<AlbumFile> image = new ArrayList<>();
    private String path;
    private EditText dialogAddItemEtItemName, dialogAddItemEtItemDes, dialogAddItemEtItemPrice, dialogAddItemEtItemOffer;
    private TextView itemDialogText;
    public int categoryId;

    public ShowCategoryFoodItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_category_food_items, container, false);
        unbinder = ButterKnife.bind(this, v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentShowCategoryFoodItemsRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getCategoryFoodItems(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentShowCategoryFoodItemsRv.addOnScrollListener(onEndLess);

        adapter = new ClientShowCategoryFoodItemsAdapter(getActivity(),
                getActivity(), generalListOfRestaurantItemsData);
        fragmentShowCategoryFoodItemsRv.setAdapter(adapter);

        getCategoryFoodItems(1);
    }

    private void getCategoryFoodItems(int page) {

        getClient().restaurantMYFoodItems(loadData(getActivity(), "api_token"), categoryId, page).enqueue(new Callback<GeneralListOfRestaurantItems>() {
            @Override
            public void onResponse(Call<GeneralListOfRestaurantItems> call, Response<GeneralListOfRestaurantItems> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        generalListOfRestaurantItemsData.addAll(response.body().getData().getData());
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<GeneralListOfRestaurantItems> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.floatingActionButton)
    public void onViewClicked() {
        openDialog();
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_item);
        CircleImageView dialogAddItemCiImage = dialog.findViewById(R.id.dialog_add_item_ci_image);
        itemDialogText = dialog.findViewById(R.id.item_dialog_text);
        itemDialogText.setText(R.string.add_item);
        dialogAddItemEtItemName = dialog.findViewById(R.id.dialog_add_item_et_item_name);
        dialogAddItemEtItemDes = dialog.findViewById(R.id.dialog_add_item_et_item_des);
        dialogAddItemEtItemPrice = dialog.findViewById(R.id.dialog_add_item_et_item_price);
        dialogAddItemEtItemOffer = dialog.findViewById(R.id.dialog_add_item_et_item_offer);
        dialogAddItemCiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(getActivity(), 1, image, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        image.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(dialogAddItemCiImage, path, getActivity());

                    }
                });
            }
        });
        Button dialogAddItemBtn = dialog.findViewById(R.id.floatingActionButton);

        dialogAddItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                addItem();
                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }
    private void addItem() {
        String name = dialogAddItemEtItemName.getText().toString();
        String des = dialogAddItemEtItemDes.getText().toString();
        String price = dialogAddItemEtItemPrice.getText().toString();
        String offer = dialogAddItemEtItemOffer.getText().toString();

        getClient().restaurantNewFoodItem(convertToRequestBody(loadData(getActivity(), "api_token")),
                convertToRequestBody(String.valueOf(categoryId)), convertToRequestBody(name),
                convertToRequestBody(des), convertToRequestBody(price),
                convertToRequestBody(offer), convertFileToMultipart(path, "photo")).enqueue(new Callback<RestaurantNewFoodItem>() {
            @Override
            public void onResponse(Call<RestaurantNewFoodItem> call, Response<RestaurantNewFoodItem> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<RestaurantNewFoodItem> call, Throwable t) {

            }
        });
    }
}
