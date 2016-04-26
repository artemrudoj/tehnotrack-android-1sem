package com.artem.hometask.technology.detail;



import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artem.hometask.R;
import com.artem.hometask.base.BaseFragment;
import com.artem.hometask.model.TechnologiesManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnologyViewPagerFragment extends BaseFragment {

    private static final int NUM_PAGES = 5;

    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private View upViewPager;

    public TechnologyViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_technology_view_pager, container, false);
        setUpViewPager(contentView);
        return contentView;
    }

    public void setUpViewPager(View contentView) {
        mPager = (ViewPager) contentView.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TechnologyDetailFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return TechnologiesManager.getInstance().itemCount();
        }
    }
}
