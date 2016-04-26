package com.artem.hometask.technology.detail;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artem.hometask.R;
import com.artem.hometask.base.BaseFragment;
import com.artem.hometask.model.Technology;


public class TechnologyDetailFragment extends BaseFragment {


    public TechnologyDetailFragment() {
        // Required empty public constructor
    }

    public static TechnologyDetailFragment newInstance(int technologyPosition) {
        TechnologyDetailFragment technologyDetailFragment = new TechnologyDetailFragment();
        return technologyDetailFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_technology_detail, container, false);
    }


}
