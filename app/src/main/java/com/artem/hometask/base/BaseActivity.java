package com.artem.hometask.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by artem on 17.03.16.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }
}
