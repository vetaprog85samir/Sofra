package com.food.sofra.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.sofra.R;


public class EmptyFragment extends BaseFragment{


    public EmptyFragment() {
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
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
