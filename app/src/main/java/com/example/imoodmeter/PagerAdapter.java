package com.example.imoodmeter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                return new CalendarFragment();
            case 1:
                return new ChartFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount(){
        return numOfTabs;
    }
}