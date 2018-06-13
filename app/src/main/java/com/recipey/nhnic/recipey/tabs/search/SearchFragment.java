package com.recipey.nhnic.recipey.tabs.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.managers.RecipeManager;
import com.yayandroid.parallaxrecyclerview.ParallaxRecyclerView;

/**
 * Created by nhnic on 5/22/2018.
 */

public class SearchFragment extends Fragment {
        private static final String TAG = "SearchFragment";
        private static final String KEY_LAYOUT_MANAGER = "layoutManager";
        private static final int SPAN_COUNT = 2;

        private enum LayoutManagerType {
            GRID_LAYOUT_MANAGER,
            LINEAR_LAYOUT_MANAGER
        }

        protected LayoutManagerType currentLayoutManagerType;
        protected ParallaxRecyclerView searchResultsRecyclerView;
        protected SearchAdapter searchAdapter;
        protected RecyclerView.LayoutManager layoutManager;

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
        searchResultsRecyclerView = rootView.findViewById(R.id.search_results_recycler_view);
    }

    public void assignVariables(Bundle savedInstanceState) {
        searchAdapter = new SearchAdapter(RecipeManager.INSTANCE.getRecipesDTO().recipes);

        layoutManager = new LinearLayoutManager(getActivity());
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            currentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType);
        searchResultsRecyclerView.setAdapter(searchAdapter);
    }

    public void assignHandlers() {

    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (searchResultsRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) searchResultsRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                layoutManager = new LinearLayoutManager(getActivity());
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                layoutManager = new LinearLayoutManager(getActivity());
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        searchResultsRecyclerView.setLayoutManager(layoutManager);
        searchResultsRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
}
