package com.food.sofra.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sofra.R;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItemsData;
import com.food.sofra.data.model.restaurant.foodItems.restaurantDeleteFoodItem.RestaurantDeleteFoodItem;
import com.food.sofra.data.model.restaurant.foodItems.restaurantNewFoodItem.RestaurantNewFoodItem;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.fragments.restaurant.bottomNav.restaurantHome.ShowCategoryFoodItemsFragment;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
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

public class

ClientShowCategoryFoodItemsAdapter extends RecyclerView.Adapter<ClientShowCategoryFoodItemsAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<GeneralListOfRestaurantItemsData> generalListOfRestaurantItemsData = new ArrayList<>();

    private EditText dialogAddItemEtItemName;
    private EditText dialogAddItemEtItemDes;
    private EditText dialogAddItemEtItemPrice;
    private EditText dialogAddItemEtItemOffer;
    private TextView itemDialogText;

    private ArrayList<AlbumFile> image = new ArrayList<>();
    private String path;

    public ClientShowCategoryFoodItemsAdapter(Context context, Activity activity, List<GeneralListOfRestaurantItemsData> generalListOfRestaurantItemsData) {
        this.context = context;
        this.activity = activity;
        this.generalListOfRestaurantItemsData = generalListOfRestaurantItemsData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_show_category_food_items_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        onLoadImageFromUrl(holder.fragmentShowCategoryFoodItemsRowItemIv, generalListOfRestaurantItemsData.get(position).getPhotoUrl(),activity);
        holder.fragmentShowCategoryFoodItemsRowItemTvFoodItemName.setText(generalListOfRestaurantItemsData.get(position).getName());
        holder.fragmentShowCategoryFoodItemsRowItemTvFoodItemDescription.setText(generalListOfRestaurantItemsData.get(position).getDescription());
        holder.fragmentShowCategoryFoodItemsRowItemTvFoodItemPrice.setText(generalListOfRestaurantItemsData.get(position).getPrice());
    }

    private void setAction(ViewHolder holder, int position) {
        holder.fragmentShowCategoryFoodItemsRowItemIbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
                notifyDataSetChanged();
            }

        });

        holder.fragmentShowCategoryFoodItemsRowItemIbEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(position);
                notifyDataSetChanged();

            }


        });
    }

    private void deleteItem(int i) {
        getClient().restaurantDeleteFoodItem(generalListOfRestaurantItemsData.get(i).getId(), loadData(activity,"api_token")).enqueue(new Callback<RestaurantDeleteFoodItem>() {
            @Override
            public void onResponse(Call<RestaurantDeleteFoodItem> call, Response<RestaurantDeleteFoodItem> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<RestaurantDeleteFoodItem> call, Throwable t) {

            }
        });
    }

    private void openDialog(int i) {

        // custom dialog
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_add_item);
        CircleImageView dialogAddItemCiImage = dialog.findViewById(R.id.dialog_add_item_ci_image);
        onLoadImageFromUrl(dialogAddItemCiImage, generalListOfRestaurantItemsData.get(i).getPhotoUrl(), activity);
        itemDialogText = dialog.findViewById(R.id.item_dialog_text);
        itemDialogText.setText(R.string.edit_item);
        dialogAddItemEtItemName = dialog.findViewById(R.id.dialog_add_item_et_item_name);
        dialogAddItemEtItemDes = dialog.findViewById(R.id.dialog_add_item_et_item_des);
        dialogAddItemEtItemPrice = dialog.findViewById(R.id.dialog_add_item_et_item_price);
        dialogAddItemEtItemOffer = dialog.findViewById(R.id.dialog_add_item_et_item_offer);
        dialogAddItemCiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(activity, 1, image, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        image.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(dialogAddItemCiImage, path, activity);

                    }
                });
            }
        });
        Button dialogAddItemBtAdd = dialog.findViewById(R.id.dialog_add_item_bt_add);

        dialogAddItemBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                editItem(i);

            }

        });


        dialog.show();
    }

    private void editItem(int i) {
        String name = dialogAddItemEtItemName.getText().toString();
        String description = dialogAddItemEtItemDes.getText().toString();
        String price = dialogAddItemEtItemPrice.getText().toString();
        String offer = dialogAddItemEtItemOffer.getText().toString();

        getClient().restaurantUpdateItem(convertToRequestBody(loadData(activity, "api_token")), convertToRequestBody(String.valueOf(generalListOfRestaurantItemsData.get(i).getId())),
                convertToRequestBody(name), convertToRequestBody(description), convertToRequestBody(price), convertToRequestBody(offer), convertFileToMultipart(path, "photo")).enqueue(new Callback<RestaurantNewFoodItem>() {
            @Override
            public void onResponse(Call<RestaurantNewFoodItem> call, Response<RestaurantNewFoodItem> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {}
            }

            @Override
            public void onFailure(Call<RestaurantNewFoodItem> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return generalListOfRestaurantItemsData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_show_category_food_items_row_item_iv)
        ImageView fragmentShowCategoryFoodItemsRowItemIv;
        @BindView(R.id.fragment_show_category_food_items_row_item_tv_food_item_name)
        TextView fragmentShowCategoryFoodItemsRowItemTvFoodItemName;
        @BindView(R.id.fragment_show_category_food_items_row_item_tv_food_item_description)
        TextView fragmentShowCategoryFoodItemsRowItemTvFoodItemDescription;
        @BindView(R.id.fragment_show_category_food_items_row_item_tv_food_item_price)
        TextView fragmentShowCategoryFoodItemsRowItemTvFoodItemPrice;
        @BindView(R.id.fragment_show_category_food_items_row_item_ib_delete)
        ImageButton fragmentShowCategoryFoodItemsRowItemIbDelete;
        @BindView(R.id.fragment_show_category_food_items_row_item_ib_edit)
        ImageButton fragmentShowCategoryFoodItemsRowItemIbEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
