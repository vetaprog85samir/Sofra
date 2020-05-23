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
import com.food.sofra.data.model.general.offers.generalOfferDetails.GeneralOfferDetails;
import com.food.sofra.data.model.general.offers.generalOfferDetails.GeneralOfferDetailsData;
import com.food.sofra.data.model.restaurant.offers.restaurantUpdateOffer.RestaurantUpdateOffer;
import com.food.sofra.utils.DateTxt;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.Calendar;
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
import static com.food.sofra.utils.HelperMethod.showCalender;

public class

RestaurantShowOffersListAdapter extends RecyclerView.Adapter<RestaurantShowOffersListAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<GeneralOfferDetailsData> generalOfferDetailsData = new ArrayList<>();

    private CircleImageView dialogAddOfferCiImage;
    private EditText dialogAddOfferEtOfferName;
    private EditText dialogAddOfferEtOfferDes;
    private EditText dialogAddOfferEtPrice;
    private EditText dialogAddOfferEtOfferPrice;
    private TextView dialogAddOfferTvDateFrom;
    private TextView dialogAddOfferTvDateTo;
    private TextView dialogOfferText;
    private ArrayList<AlbumFile> image = new ArrayList<>();
    private String path;

    public RestaurantShowOffersListAdapter(Context context, Activity activity, List<GeneralOfferDetailsData> generalOfferDetailsData) {
        this.context = context;
        this.activity = activity;
        this.generalOfferDetailsData = generalOfferDetailsData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_restaurant_show_offer_list_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.itemsRestaurantTvItemName.setText(generalOfferDetailsData.get(position).getName());
        onLoadImageFromUrl(holder.itemsRestaurantIvImage, generalOfferDetailsData.get(position).getPhotoUrl(), activity);
        holder.itemsRestaurantTvItemDes.setText(generalOfferDetailsData.get(position).getDescription());
        holder.itemsRestaurantTvItemCost.setVisibility(View.GONE);
    }

    private void setAction(ViewHolder holder, int position) {
        holder.itemsRestaurantIbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
                notifyDataSetChanged();
            }
        });

        holder.itemsRestaurantIbEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(position);
                notifyDataSetChanged();

            }
        });
    }

    private void deleteItem(int position) {
        getClient().restaurantDeleteOffer(String.valueOf(generalOfferDetailsData.get(position).getId()), loadData(activity,"api_token")).enqueue(new Callback<GeneralOfferDetails>() {
            @Override
            public void onResponse(Call<GeneralOfferDetails> call, Response<GeneralOfferDetails> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralOfferDetails> call, Throwable t) {

            }
        });
    }

    private void openDialog(int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_add_offers);
        CircleImageView dialogAddOfferCiImage = dialog.findViewById(R.id.dialog_add_offer_ci_image);
        dialogOfferText = dialog.findViewById(R.id.dialog_offer_text);
        dialogOfferText.setText(R.string.edit_offers);
        dialogAddOfferCiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(activity, 1, image, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        image.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(dialogAddOfferCiImage, path, activity);

                    }
                });
            }
        });

        dialogAddOfferTvDateFrom = dialog.findViewById(R.id.dialog_add_offer_tv_date_from);
        Calendar calendar = Calendar.getInstance();
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        DateTxt dateTxt = new DateTxt(day, month, year, day + "-" + month + "-" + year);
        dialogAddOfferTvDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalender(activity, null, dialogAddOfferTvDateFrom, dateTxt);

            }
        });


        dialogAddOfferTvDateTo = dialog.findViewById(R.id.dialog_add_offer_tv_date_to);
        dialogAddOfferTvDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalender(activity, null, dialogAddOfferTvDateTo, dateTxt);

            }
        });

        dialogAddOfferEtOfferName = dialog.findViewById(R.id.dialog_add_offer_et_offer_name);
        dialogAddOfferEtOfferDes = dialog.findViewById(R.id.dialog_add_offer_et_offer_des);
        dialogAddOfferEtPrice = dialog.findViewById(R.id.dialog_add_offer_et_price);
        dialogAddOfferEtOfferPrice = dialog.findViewById(R.id.dialog_add_offer_et_offer_price);
        Button dialogAddOfferBtAdd = dialog.findViewById(R.id.dialog_add_offer_bt_add);
        dialogAddOfferBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                editOffers(position);

            }
        });

        dialog.show();
    }

    private void editOffers(int position) {

        String name = dialogAddOfferEtOfferName.getText().toString();
        String des = dialogAddOfferEtOfferDes.getText().toString();
        String price = dialogAddOfferEtPrice.getText().toString();
        String offerPrice = dialogAddOfferEtOfferPrice.getText().toString();
        String fromDate = dialogAddOfferTvDateFrom.getText().toString();
        String toDate = dialogAddOfferTvDateTo.getText().toString();

        getClient().restaurantUpdateOffer(convertToRequestBody(loadData(activity, "api_token")), convertToRequestBody(String.valueOf(generalOfferDetailsData.get(position).getId())), convertToRequestBody(name), convertToRequestBody(des),
                convertToRequestBody(price), convertToRequestBody(offerPrice), convertToRequestBody(fromDate),
                convertToRequestBody(toDate),
                convertFileToMultipart(path, "photo")).enqueue(new Callback<RestaurantUpdateOffer>() {
            @Override
            public void onResponse(Call<RestaurantUpdateOffer> call, Response<RestaurantUpdateOffer> response) {
                try {
                    if (response.body().getStatus()==1) {
                        notifyDataSetChanged();
                        Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<RestaurantUpdateOffer> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return generalOfferDetailsData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.items_restaurant_ib_delete)
        ImageButton itemsRestaurantIbDelete;
        @BindView(R.id.items_restaurant_ib_edit)
        ImageButton itemsRestaurantIbEdit;
        @BindView(R.id.items_restaurant_iv_image)
        ImageView itemsRestaurantIvImage;
        @BindView(R.id.items_restaurant_tv_item_name)
        TextView itemsRestaurantTvItemName;
        @BindView(R.id.items_restaurant_tv_item_des)
        TextView itemsRestaurantTvItemDes;
        @BindView(R.id.items_restaurant_tv_item_cost)
        TextView itemsRestaurantTvItemCost;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
