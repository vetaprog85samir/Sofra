package com.food.sofra.view.fragments.restaurant.bottomNav.restaurantHome;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.adapter.ClientShowCategoriesAdapter;
import com.food.sofra.data.model.general.restaurants.generalListOfCategories.GeneralListOfCategories;
import com.food.sofra.data.model.general.restaurants.generalListOfCategories.GeneralListOfCategoriesData;
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


public class ShowCategoriesFragment extends BaseFragment {


    @BindView(R.id.fragment_show_categories_rv)
    RecyclerView fragmentShowCategoriesRv;
    Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private List<GeneralListOfCategoriesData> generalListOfCategoriesData = new ArrayList<>();
    private ClientShowCategoriesAdapter adapter;
    private EditText dialogAddCategoryEtCategoryName;
    private CircleImageView dialogAddCategoryCiImage;
    private ArrayList<AlbumFile> image = new ArrayList<>();
    private String path;
    public String email;
    public String password;

    public ShowCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_categories, container, false);
        unbinder = ButterKnife.bind(this, v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentShowCategoriesRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getRestaurantCategories(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentShowCategoriesRv.addOnScrollListener(onEndLess);

        adapter = new ClientShowCategoriesAdapter(getActivity()
                , getActivity(), generalListOfCategoriesData);
        fragmentShowCategoriesRv.setAdapter(adapter);
        getRestaurantCategories(1);
    }

    private void getRestaurantCategories(int page) {

        getClient().restaurantCategoriesList(loadData(getActivity(), "api_token"), page).enqueue(new Callback<GeneralListOfCategories>() {
            @Override
            public void onResponse(Call<GeneralListOfCategories> call, Response<GeneralListOfCategories> response) {
                maxPage = response.body().getData().getLastPage();
                generalListOfCategoriesData.addAll(response.body().getData().getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GeneralListOfCategories> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.btn_add_category)
    public void onViewClicked() {
        openDialog();
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_category);
        dialogAddCategoryEtCategoryName = dialog.findViewById(R.id.dialog_add_category_et_category_name);
        dialogAddCategoryCiImage = dialog.findViewById(R.id.dialog_add_category_ci_image);
        dialogAddCategoryCiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(getActivity(), 1, image, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        image.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(dialogAddCategoryCiImage,path,getActivity());
                    }
                });
            }
        });

        Button dialogAddCategoryBtAdd = dialog.findViewById(R.id.dialog_add_category_bt_add);
        dialogAddCategoryBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                addCategory();
                adapter.notifyDataSetChanged();

            }

        });
        dialog.show();
    }

    private void addCategory() {
        String name = dialogAddCategoryEtCategoryName.getText().toString();
        getClient().restaurantNewCategory(convertToRequestBody(name),convertFileToMultipart(path,"photo"),convertToRequestBody(loadData(getActivity(),"api_token"))).enqueue(new Callback<GeneralListOfCategories>() {
            @Override
            public void onResponse(Call<GeneralListOfCategories> call, Response<GeneralListOfCategories> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralListOfCategories> call, Throwable t) {

            }
        });
    }
}
