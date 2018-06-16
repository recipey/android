package com.recipey.nhnic.recipey.tabs.cookbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.GenericActivity;
import com.recipey.nhnic.recipey.app.SlidingTabLayout;
import com.recipey.nhnic.recipey.tabs.cookbook.favorites.FavoritesFragment;
import com.recipey.nhnic.recipey.tabs.discover.DiscoverFragment;

/**
 * Created by nhnic on 6/13/2018.
 */

public class CookbookFragment extends Fragment {

    private final String TAG = "CookbookFragment";
    private Fragment fragments[];

    private SlidingTabLayout tabs;
    private ViewPager pager;
    private CookbookAdapter cookbookAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cookbook, container, false);

        assignViews(rootView);
        assignVariables(savedInstanceState);
        assignHandlers();

        return rootView;
    }

    private void assignViews(View rootView) {
        tabs = rootView.findViewById(R.id.cookbook_tabs);
        pager = rootView.findViewById(R.id.cookbook_pager);
    }

    private void assignVariables(Bundle savedInstanceState) {
        fragments = new Fragment[2];
        fragments[0] = new FavoritesFragment();
        fragments[1] = new Fragment();

        tabs.setTabs(2);
        tabs.setTabSetting(false);
        tabs.setSelector(R.color.selector);
        tabs.setSelectedIndicatorColors(0xFFFF0000);
        pager.setOffscreenPageLimit(2);

        cookbookAdapter = new CookbookAdapter(((GenericActivity) getContext()).getSupportFragmentManager(), fragments);
        pager.setAdapter(cookbookAdapter);
        tabs.setViewPager(pager);
    }

    private void assignHandlers() {

    }
}
