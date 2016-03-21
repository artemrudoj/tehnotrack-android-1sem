package com.artem.hometask.base;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.artem.hometask.R;

public class ToolbarActivity extends BaseActivity {

    TextView titleToolbar;



    public void setToolbar(int titleResourceId) {
        setToolbar(getString(titleResourceId));
    }

    public void setToolbar(String title) {
        Toolbar mActionBarToolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle("");
        titleToolbar = (TextView) mActionBarToolbar.findViewById(R.id.title_tv);
        titleToolbar.setText(title);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    public void setToolbar() {
        setToolbar("");
    }

    public void changeTitle(String title) {
        if (titleToolbar != null)
        titleToolbar.setText(title);
    }


    public void changeTitle(int title) {
        changeTitle(getString(title));
    }
}
