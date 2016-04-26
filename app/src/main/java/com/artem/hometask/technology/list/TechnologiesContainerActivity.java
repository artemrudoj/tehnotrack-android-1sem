package com.artem.hometask.technology.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.artem.hometask.R;
import com.artem.hometask.base.ToolbarActivity;

public class TechnologiesContainerActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setToolbar(R.string.second_activity);
        if(savedInstanceState == null) {
            Fragment listTechnologiesFragment = new TechnologiesListFragment();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.tech_container_fl, listTechnologiesFragment).commit();

        }

    }


}
