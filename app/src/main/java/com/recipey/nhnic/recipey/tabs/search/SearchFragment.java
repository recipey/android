package com.recipey.nhnic.recipey.tabs.search;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipey.nhnic.recipey.R;

/**
 * Created by nhnic on 5/22/2018.
 */

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        assignViews(rootView);
        assignVariables(savedInstanceState);
        assignHandlers();

        return rootView;
    }

    public void assignViews(View rootView) {

    }

    public void assignVariables(Bundle savedInstanceState) {

    }

    public void assignHandlers() {

    }
}
