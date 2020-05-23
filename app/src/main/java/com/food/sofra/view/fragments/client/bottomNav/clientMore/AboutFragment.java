package com.food.sofra.view.fragments.client.bottomNav.clientMore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;
import com.food.sofra.view.fragments.BaseFragment;


public class AboutFragment extends BaseFragment{


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        return inflater.inflate(R.layout.fragment_client_about, container, false);
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
