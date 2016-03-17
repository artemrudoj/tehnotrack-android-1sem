package com.artem.hometask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.artem.hometask.base.ToolbarActivity;

public class SecondActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setToolbar(R.string.second_activity);
        setUpRecyclerView();
    }

    void setUpRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        mRecyclerView.setAdapter(new SimpleRecyclerViewAdapter(getLayoutInflater()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);
    }
}
