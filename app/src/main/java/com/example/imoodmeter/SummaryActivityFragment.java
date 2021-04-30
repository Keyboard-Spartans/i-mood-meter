package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;



public class SummaryActivityFragment extends Fragment {

    public SummaryActivityFragment() {
        super(R.layout.activity_summary);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_summary);

        TabLayout tabLayout = view.findViewById(R.id.tabBar);
        TabItem tabCalendar = view.findViewById(R.id.tabCalendar);
        TabItem tabChart = view.findViewById(R.id.tabChart);

        ViewPager viewPager = view.findViewById(R.id.ViewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager); // this will automatically bind tab clicks to viewpager fragment
        tabLayout.getTabAt(0).setText("Calendar");
        tabLayout.getTabAt(1).setText("Chart");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });



    }




}