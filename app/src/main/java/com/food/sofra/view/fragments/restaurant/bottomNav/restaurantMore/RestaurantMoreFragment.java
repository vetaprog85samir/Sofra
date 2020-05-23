package com.food.sofra.view.fragments.restaurant.bottomNav.restaurantMore;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.utils.HelperMethod;
import com.food.sofra.view.fragments.BaseFragment;
import com.food.sofra.view.fragments.client.bottomNav.clientHome.restaurantData.ReviewsFragment;
import com.food.sofra.view.fragments.client.bottomNav.clientMore.AboutFragment;
import com.food.sofra.view.fragments.client.bottomNav.clientMore.ChangePasswordFragment;
import com.food.sofra.view.fragments.client.bottomNav.clientMore.ContactFragment;
import com.food.sofra.view.fragments.restaurant.bottomNav.restaurantMore.restaurantOffers.RestaurantShowOffersListFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.food.sofra.utils.HelperMethod.replaceFragment;


public class RestaurantMoreFragment extends BaseFragment {


    private Unbinder unbinder;

    public RestaurantMoreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_more_restaurant, container, false);
        unbinder = ButterKnife.bind(this, v);
        setUpActivity();
        return v;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_more_restaurant_et_offers, R.id.fragment_more_restaurant_et_contact_us, R.id.fragment_more_restaurant_et_about_app, R.id.fragment_more_restaurant_et_comment, R.id.fragment_more_restaurant_et_change_password, R.id.fragment_more_restaurant_et_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_more_restaurant_et_offers:
                RestaurantShowOffersListFragment restaurantShowOffersListFragment = new RestaurantShowOffersListFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_more_restaurant,restaurantShowOffersListFragment);
                break;
            case R.id.fragment_more_restaurant_et_contact_us:
                ContactFragment contactFragment = new ContactFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_more_restaurant,contactFragment);
                break;
            case R.id.fragment_more_restaurant_et_about_app:
                AboutFragment aboutFragment = new AboutFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_more_restaurant,aboutFragment);
                break;
            case R.id.fragment_more_restaurant_et_comment:
                ReviewsFragment reviewsFragment = new ReviewsFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_more_restaurant,reviewsFragment);
                break;
            case R.id.fragment_more_restaurant_et_change_password:
                ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_more_restaurant,changePasswordFragment);
                break;
            case R.id.fragment_more_restaurant_et_logout:
                openDialog();
                break;
        }
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_logout_action);

        TextView text = dialog.findViewById(R.id.logout_dialog_text);
        text.setText(getString(R.string.are_you_sure_to_logout));
        ImageButton accept = dialog.findViewById(R.id.logout_dialog_ok);
        accept.setImageResource(R.drawable.ic_true);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesManger.clean(getActivity());
                getActivity().finish();
            }
        });
        ImageButton cancel = dialog.findViewById(R.id.logout_dialog_cancel);
        cancel.setImageResource(R.drawable.ic_wrong);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
