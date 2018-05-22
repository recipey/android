package com.recipey.nhnic.recipey.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.GenericActivity;
import com.recipey.nhnic.recipey.app.SlidingTabLayout;

/**
 * Created by nhnic on 5/11/2018.
 */

public class MainActivity extends GenericActivity {
    private final String TAG = "MainActivity";

    private Fragment[] fragments;
    private int[] icons = {};

    private SlidingTabLayout tabs;
    private ViewPager pager;
    private MainPagerAdapter mainPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assignViews();
        assignVariables(savedInstanceState);
        assignHandlers();
    }

    private void assignViews() {
        pager = findViewById(R.id.pager);
        tabs = findViewById(R.id.tabs);
    }

    private void assignVariables(Bundle savedInstanceState) {
        fragments = new Fragment[5];

        tabs.setTabs(5);
        tabs.setTabSetting(true);
        pager.setOffscreenPageLimit(4);

        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments, icons);
        pager.setAdapter(mainPagerAdapter);
        tabs.setViewPager(pager);
    }

    private void assignHandlers() {

    }
}
