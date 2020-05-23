package com.food.sofra.view.activities;

import android.os.Bundle;

import com.food.sofra.R;
import com.food.sofra.view.fragments.client.bottomNav.clientHome.ListOfRestaurantsFragment;

import androidx.annotation.Nullable;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_auth_fragment_container, new ListOfRestaurantsFragment()).commit();

    }
}
