package com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.adapter.ClientReviewAdapter;
import com.food.sofra.data.model.client.clientAddReview.ClientAddReview;
import com.food.sofra.data.model.client.clientAddReview.Review;
import com.food.sofra.data.model.general.restaurants.generalRestaurantReviews.GeneralRestaurantReviews;
import com.food.sofra.data.model.general.restaurants.generalRestaurantReviews.GeneralRestaurantReviewsData;
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
import static com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger.loadData;


public class ReviewsFragment extends BaseFragment {


    @BindView(R.id.fragment_reviews_rv)
    RecyclerView fragmentReviewsRv;

    public Integer id;
    Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private List<Review> review = new ArrayList<>();
    private ClientReviewAdapter adapter;
    private BaseActivity activity;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reviews, container, false);
        unbinder = ButterKnife.bind(this,v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentReviewsRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getClientReviews(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentReviewsRv.addOnScrollListener(onEndLess);

        adapter = new ClientReviewAdapter(getActivity(),getActivity(), review);
        fragmentReviewsRv.setAdapter(adapter);

        getClientReviews(1);
    }

    private void getClientReviews(int page) {

        getClient().generalRestaurantReviews(loadData(activity,"api_token"),id,page).enqueue(new Callback<GeneralRestaurantReviews>() {
            @Override
            public void onResponse(Call<GeneralRestaurantReviews> call, Response<GeneralRestaurantReviews> response) {
                try {
                    if (response.body().getStatus()==1) {
                        maxPage = response.body().getData().getLastPage();
                        review.addAll(response.body().getData().getData());
                        adapter.notifyDataSetChanged();
                    }
                }catch (Exception e){}


            }

            @Override
            public void onFailure(Call<GeneralRestaurantReviews> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
