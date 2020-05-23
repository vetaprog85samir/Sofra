package com.food.sofra.view.fragments;


import com.food.sofra.view.activities.BaseActivity;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;

    public void setUpActivity() {
        baseActivity = (BaseActivity) getActivity();

        baseActivity.baseFragment = this;
    }

    public void onBack() {
        baseActivity.superBackPressed();
    }

}
