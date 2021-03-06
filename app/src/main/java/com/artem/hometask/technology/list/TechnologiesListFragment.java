package com.artem.hometask.technology.list;


import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artem.hometask.R;
import com.artem.hometask.base.BaseFragment;
import com.artem.hometask.model.TechnologiesManager;
import com.artem.hometask.model.Technology;
import com.artem.hometask.technology.detail.TechnologyViewPagerFragment;

import java.util.List;


public class TechnologiesListFragment extends BaseFragment {


    public TechnologiesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View containerView =inflater.inflate(R.layout.fragment_technologies_list, container, false);
        setUpRecyclerView(containerView);
        return containerView;
    }

    void setUpRecyclerView(View containerView) {
        Activity activity = getActivity();
        if(activity != null) {
            List<Technology> list = TechnologiesManager.getInstance().getTechnologies();
            RecyclerView mRecyclerView = (RecyclerView)containerView.findViewById(R.id.recyclerview);
            SimpleRecyclerViewAdapter simpleViewHolderAdapter =
                    new SimpleRecyclerViewAdapter(activity.getLayoutInflater(), activity, list,
                            new SimpleRecyclerViewAdapter.IClickListener() {
                                @Override
                                public void onClick(int position) {
                                    Fragment fragment = TechnologyViewPagerFragment.newInstance(position);
                                    TechnologiesListFragment.this.getActivity().getSupportFragmentManager().beginTransaction().
                                            replace(R.id.tech_container_fl,fragment).addToBackStack(null).commit();
                                }
                            });
            mRecyclerView.setAdapter(simpleViewHolderAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setHasFixedSize(true);
        }
    }

}
