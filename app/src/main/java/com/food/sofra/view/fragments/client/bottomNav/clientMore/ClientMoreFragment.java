package com.food.sofra.view.fragments.client.bottomNav.clientMore;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.food.sofra.R;
import com.food.sofra.data.local.sharedPreferences.SharedPreferencesManger;
import com.food.sofra.view.fragments.BaseFragment;
import com.food.sofra.view.fragments.client.bottomNav.clientMore.ClientOffers.ClientShowOffersListFragment;

import butterknife.OnClick;

import static com.food.sofra.utils.HelperMethod.replaceFragment;


public class ClientMoreFragment extends BaseFragment {


    public ClientMoreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_more, container, false);
        setUpActivity();
        return v;
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_more_client_et_offers, R.id.fragment_more_client_et_contact_us, R.id.fragment_more_client_et_about_app, R.id.fragment_more_client_et_change_password, R.id.fragment_more_client_et_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_more_client_et_offers:
                ClientShowOffersListFragment clientShowOffersListFragment = new ClientShowOffersListFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_client_more,clientShowOffersListFragment);
                break;
            case R.id.fragment_more_client_et_contact_us:
                ContactFragment contactFragment = new ContactFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_client_more, contactFragment);
                break;
            case R.id.fragment_more_client_et_about_app:
                AboutFragment aboutFragment = new AboutFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_client_more, aboutFragment);
                break;
            case R.id.fragment_more_client_et_change_password:
                ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
                replaceFragment(getChildFragmentManager(),R.id.fragment_client_more, changePasswordFragment);
                break;
            case R.id.fragment_more_client_et_logout:
                logoutDialog();
                break;
        }
    }

    private void logoutDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_client_logout);

        ImageButton confirm = dialog.findViewById(R.id.dialog_client_logout_ib_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesManger.clean(getActivity());
                getActivity().finish();
            }
        });

        ImageButton cancel = dialog.findViewById(R.id.dialog_client_logout_ib_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
