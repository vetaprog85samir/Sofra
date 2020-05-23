package com.food.sofra.view.fragments.client.bottomNav.clientHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.adapter.ClientNotificationAdapter;
import com.food.sofra.data.model.client.notifications.listOfNotifications.ListOfNotifications;
import com.food.sofra.data.model.client.notifications.listOfNotifications.ListOfNotificationsData;
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


public class NotificationFragment extends BaseFragment {

    public BaseActivity activity;
    @BindView(R.id.notification_fragment_rv)
    RecyclerView notificationFragmentRv;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private int maxPage = 0;
    private List<ListOfNotificationsData> listOfNotificationsData = new ArrayList<>();
    private ClientNotificationAdapter adapter;
    Unbinder unbinder;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, v);

        init();

        setUpActivity();
        return v;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        notificationFragmentRv.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getClientNotifications();
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        notificationFragmentRv.addOnScrollListener(onEndLess);

        adapter = new ClientNotificationAdapter(getActivity(), getActivity(), listOfNotificationsData);
        notificationFragmentRv.setAdapter(adapter);

        getClientNotifications();
    }

    private void getClientNotifications() {
        getClient().listOfNotifications(loadData(activity,"api_token")).enqueue(new Callback<ListOfNotifications>() {
            @Override
            public void onResponse(Call<ListOfNotifications> call, Response<ListOfNotifications> response) {
                maxPage = response.body().getListOfNotificationsPagination().getLastPage();
                listOfNotificationsData.addAll(response.body().getListOfNotificationsPagination().getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListOfNotifications> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
