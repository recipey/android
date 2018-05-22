package com.recipey.nhnic.recipey.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private Fragment fragments[];
    private int[] icons;

    public MainPagerAdapter(FragmentManager fm, Fragment fragments[], int[] icons) {
        super(fm);
        this.fragments = fragments;
        this.icons = icons;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Integer.toString(position);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    public int getDrawableID(int position) {
        return icons[position];
    }
}
