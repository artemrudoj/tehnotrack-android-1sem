package com.artem.hometask.splashscreen;

import android.content.Intent;
import android.os.Bundle;

import com.artem.hometask.R;
import com.artem.hometask.base.BaseActivity;
import com.artem.hometask.technology.list.TechnologiesContainerActivity;

public class SplashScreenActivity extends BaseActivity implements SplashScreenView {

    SplashScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        presenter = new SplashScreenPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void goToListActivity() {
        Intent intent = new Intent(this, TechnologiesContainerActivity.class);
        startActivity(intent);
        finish();
    }
}
