package com.recipey.nhnic.recipey.tabs.cookbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.Application;

/**
 * Created by Nicky on 2/1/2017.
 */
public class CookbookAdapter extends FragmentPagerAdapter {
    private Fragment fragments[];
    private String[] titles = {Application.getInstance().getString(R.string.saved_subtitle), Application.getInstance().getString(R.string.past_subtitle)};

    public CookbookAdapter(FragmentManager fm, Fragment fragments[]) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
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
}
