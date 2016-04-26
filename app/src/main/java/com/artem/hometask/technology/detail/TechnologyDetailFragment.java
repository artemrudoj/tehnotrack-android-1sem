package com.artem.hometask.technology.detail;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artem.hometask.R;
import com.artem.hometask.base.BaseFragment;
import com.artem.hometask.imageloader.ImageDownloadManager;
import com.artem.hometask.model.TechnologiesManager;
import com.artem.hometask.model.Technology;


public class TechnologyDetailFragment extends BaseFragment {
    static public final String BASE_URL = "http://mobevo.ext.terrhq.ru/";
    final public static String TECHNOLODY_ID = "TechnologyDetailFragment:TECHNOLODY_ID";
    Technology item;
    public TechnologyDetailFragment() {
        // Required empty public constructor
    }

    public static TechnologyDetailFragment newInstance(int technologyPosition) {
        TechnologyDetailFragment technologyDetailFragment = new TechnologyDetailFragment();
        Bundle args = new Bundle();
        args.putInt(TECHNOLODY_ID, technologyPosition);
        technologyDetailFragment.setArguments(args);
        return technologyDetailFragment;

    }

    void setItem() {
        if(getArguments() == null)
            throw new IllegalArgumentException("can not found arguments");
        int index = getArguments().getInt(TECHNOLODY_ID, -1);
        if (index == -1 )
            throw new IllegalArgumentException("can not found tech index");
        item = TechnologiesManager.getInstance().getByIndex(index);
        if(item == null)
            throw new IllegalArgumentException("can not found tech by index");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setItem();
        View contentView = inflater.inflate(R.layout.fragment_technology_detail, container, false);
        setUpLayout(contentView);
        return contentView;
    }

    private void setUpLayout(View contentView) {
        ((TextView)contentView.findViewById(R.id.title_tv)).setText(item.getTitle());
        ((TextView)contentView.findViewById(R.id.info_tv)).setText(item.getInfo());
        ImageDownloadManager.getInstance().setImageViewByUrl(getActivity(), BASE_URL + item.getPicture(),
                ((ImageView)contentView.findViewById(R.id.picture_iv)), null);

    }


}
