package com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.foodList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.adapter.ClientShowCategoriesAdapter;
import com.food.sofra.adapter.ListOfRestaurantFoodItemsRvAdapter;
import com.food.sofra.data.model.general.restaurants.generalListOfCategories.GeneralListOfCategories;
import com.food.sofra.data.model.general.restaurants.generalListOfCategories.GeneralListOfCategoriesData;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItems;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItemsData;
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


public class ShowFoodListFragment extends BaseFragment {


    Unbinder unbinder;

    public Integer id;
    private OnEndLess onEndLess;
    private int maxPage = 0;
    protected int clientCategoryId = -1;

    @BindView(R.id.fragment_show_food_list_rv_category)
    RecyclerView fragmentShowFoodListRvCategory;
    @BindView(R.id.fragment_show_food_list_rv_items)
    RecyclerView fragmentShowFoodListRvItems;

    private LinearLayoutManager itemsLinearLayoutManager;
    private LinearLayoutManager categoriesLinearLayoutManager;

    private List<GeneralListOfRestaurantItemsData> generalListOfRestaurantItemsData =
            new ArrayList<>();
    private List<GeneralListOfCategoriesData> generalListOfCategoriesData =
            new ArrayList<>();

    private ListOfRestaurantFoodItemsRvAdapter itemsAdapter;
    private ClientShowCategoriesAdapter categoriesAdapter;
    //----------------------------------------------------------------------------------------------
    public ShowFoodListFragment() {
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
        View v = inflater.inflate(R.layout.fragment_show_food_list, container, false);

        unbinder = ButterKnife.bind(this, v);

        getCategoryData();
        getItemData();

        setUpActivity();
        return v;
    }

    private void getCategoryData() {
        getClient().generalListOfCategories(id, clientCategoryId).enqueue(new Callback<GeneralListOfCategories>() {
            @Override
            public void onResponse(Call<GeneralListOfCategories> call, Response<GeneralListOfCategories> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        generalListOfCategoriesData.addAll(response.body().getData());
                        categoriesLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
                        fragmentShowFoodListRvCategory.setLayoutManager(categoriesLinearLayoutManager);
                        categoriesAdapter = new ClientShowCategoriesAdapter(getActivity(),getActivity(),generalListOfCategoriesData);
                        fragmentShowFoodListRvCategory.setAdapter(categoriesAdapter);
                        categoriesAdapter.notifyDataSetChanged();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralListOfCategories> call, Throwable t) {

            }
        });
    }

    private void getItemData() {
        itemsLinearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentShowFoodListRvItems.setLayoutManager(itemsLinearLayoutManager);

        onEndLess = new OnEndLess(itemsLinearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = onEndLess.current_page;
                        getItems(current_page);
                    }
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentShowFoodListRvItems.addOnScrollListener(onEndLess);

        itemsAdapter = new ListOfRestaurantFoodItemsRvAdapter(getActivity(),getActivity(),
                generalListOfRestaurantItemsData);
        fragmentShowFoodListRvItems.setAdapter(itemsAdapter);

        getItems(1);
    }

    private void getItems(int page) {
        getClient().generalListOfRestaurantItems(id, clientCategoryId, page).enqueue(new Callback<GeneralListOfRestaurantItems>() {
            @Override
            public void onResponse(Call<GeneralListOfRestaurantItems> call, Response<GeneralListOfRestaurantItems> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        onEndLess.previous_page = 1;
                        onEndLess.current_page = 1;
                        onEndLess.previousTotal = 0;

                        generalListOfCategoriesData = new ArrayList<>();
                        itemsAdapter = new ListOfRestaurantFoodItemsRvAdapter(getActivity(),getActivity(),
                                generalListOfRestaurantItemsData);
                        fragmentShowFoodListRvItems.setAdapter(itemsAdapter);
                    }
                    maxPage = response.body().getData().getLastPage();
                    generalListOfRestaurantItemsData.addAll(response.body().getData().getData());
                    itemsAdapter.notifyDataSetChanged();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralListOfRestaurantItems> call, Throwable t) {

            }
        });
    }

    //----------------------------------------------------------------------------------------------
    //private void init() {
    //    itemsLinearLayoutManager = new LinearLayoutManager(getActivity());
    //    fragmentShowFoodListRv.setLayoutManager(itemsLinearLayoutManager);
//
    //    onEndLess = new OnEndLess(itemsLinearLayoutManager, 1) {
    //        @Override
    //        public void onLoadMore(int current_page) {
//
    //            if (current_page <= maxPage) {
    //                if (maxPage != 0 && current_page != 1) {
    //                    onEndLess.previous_page = current_page;
    //                    getRestaurantItems();
    //                } else {
    //                    onEndLess.current_page = onEndLess.previous_page;
    //                }
    //            } else {
    //                onEndLess.current_page = onEndLess.previous_page;
    //            }
    //        }
    //    };
    //    fragmentShowFoodListRv.addOnScrollListener(onEndLess);
//
    //    itemsAdapter = new ListOfRestaurantFoodItemsRvAdapter(getActivity(), getActivity(),
    //            itemsData);
    //    fragmentShowFoodListRv.setAdapter(itemsAdapter);
//
    //    getRestaurantItems();
    //}
//
    ////----------------------------------------------------------------------------------------------
    //private void getRestaurantItems() {
    //    getClient().generalListOfRestaurantItems(restaurantsRvAdapter.selectedId,
    //            clientShowCategoriesAdapter.selectedId)
    //            .enqueue(new Callback<GeneralListOfRestaurantItems>() {
    //                @Override
    //                public void onResponse(Call<GeneralListOfRestaurantItems> call,
    //                                       Response<GeneralListOfRestaurantItems> response) {
    //                    itemsData.addAll(response.body().getData().getData());
    //                    itemsAdapter.notifyDataSetChanged();
    //                }
//
    //                @Override
    //                public void onFailure(Call<GeneralListOfRestaurantItems> call, Throwable t) {
//
    //                }
    //            });
    //}

    //----------------------------------------------------------------------------------------------
    @Override
    public void onBack() {
        super.onBack();
    }
    //----------------------------------------------------------------------------------------------

}
