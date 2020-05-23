package com.food.sofra.view.fragments.restaurant.bottomNav.restaurantMore.restaurantOffers;

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
import com.food.sofra.adapter.RestaurantShowOffersListAdapter;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.data.model.general.offers.generalOfferDetails.GeneralOfferDetails;
import com.food.sofra.data.model.general.offers.generalOfferDetails.GeneralOfferDetailsData;
import com.food.sofra.utils.DateTxt;
import com.food.sofra.utils.OnEndLess;
import com.food.sofra.view.fragments.BaseFragment;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.Calendar;
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
import static com.food.sofra.utils.HelperMethod.showCalender;


public class RestaurantShowOffersListFragment extends BaseFragment {


    @BindView(R.id.fragment_restaurant_show_offers_list_rv)
    RecyclerView fragmentRestaurantShowOffersListRv;
    Unbinder unbinder;
    private OnEndLess onEndLess;
    private int maxPage =0;
    private RestaurantShowOffersListAdapter adapter;
    private List<GeneralOfferDetailsData> generalOfferDetailsData = new ArrayList<>();
    private ArrayList<AlbumFile> image = new ArrayList<>();
    private String path;
    private EditText dialogAddOfferEtOfferName, dialogAddOfferEtOfferDes, dialogAddOfferEtPrice, dialogAddOfferEtOfferPrice;
    private TextView dialogAddOfferTvDateFrom, dialogAddOfferTvDateTo, dialogOfferText;

    public RestaurantShowOffersListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_show_offers_list, container, false);
        unbinder = ButterKnife.bind(this, v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentRestaurantShowOffersListRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getRestaurantOffersList(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentRestaurantShowOffersListRv.addOnScrollListener(onEndLess);

        adapter = new RestaurantShowOffersListAdapter(getActivity(), getActivity(), generalOfferDetailsData);
        fragmentRestaurantShowOffersListRv.setAdapter(adapter);

        getRestaurantOffersList(1);
    }

    private void getRestaurantOffersList(int page) {

        getClient().restaurantMyOffer(loadData(getActivity(),"api_token"),page).enqueue(new Callback<GeneralOfferDetails>() {
            @Override
            public void onResponse(Call<GeneralOfferDetails> call, Response<GeneralOfferDetails> response) {
                try {
                    if (response.body().getStatus()==1.) {
                        maxPage = response.body().getData().getLastPage();
                        generalOfferDetailsData.addAll(response.body().getData().getData());
                        adapter.notifyDataSetChanged();
                    }
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralOfferDetails> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_restaurant_show_offers_list_layout_add)
    public void onViewClicked() {
        openDialog();
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_offers);
        CircleImageView dialogAddOfferCiImage = dialog.findViewById(R.id.dialog_add_offer_ci_image);
        dialogOfferText = dialog.findViewById(R.id.dialog_offer_text);
        dialogOfferText.setText(getString(R.string.add_offers));
        dialogAddOfferCiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(getActivity(), 1, image, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        image.clear();
                        path = result.get(0).getPath();
                        onLoadImageFromUrl(dialogAddOfferCiImage, path, getActivity());

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
                showCalender(getActivity(), null, dialogAddOfferTvDateFrom, dateTxt);

            }
        });


        dialogAddOfferTvDateTo = dialog.findViewById(R.id.dialog_add_offer_tv_date_to);
        dialogAddOfferTvDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalender(getActivity(), null, dialogAddOfferTvDateTo, dateTxt);

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
                addOffers();

            }
        });

        dialog.show();

    }

    private void addOffers() {
        String name = dialogAddOfferEtOfferName.getText().toString();
        String des = dialogAddOfferEtOfferDes.getText().toString();
        String price = dialogAddOfferEtPrice.getText().toString();
        String offerPrice = dialogAddOfferEtOfferPrice.getText().toString();
        String fromDate = dialogAddOfferTvDateFrom.getText().toString();
        String toDate = dialogAddOfferTvDateTo.getText().toString();

        getClient().restaurantNewOffer(convertToRequestBody(name), convertToRequestBody(des),
                convertToRequestBody(price), convertToRequestBody(offerPrice), convertToRequestBody(fromDate),
                convertToRequestBody(toDate), convertToRequestBody(loadData(getActivity(), "Restaurant_ApiToken")),
                convertFileToMultipart(path, "photo")).enqueue(new Callback<GeneralOfferDetails>() {
            @Override
            public void onResponse(Call<GeneralOfferDetails> call, Response<GeneralOfferDetails> response) {
                try {
                    if (response.body().getStatus()==1) {
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<GeneralOfferDetails> call, Throwable t) {

            }
        });
    }
}
