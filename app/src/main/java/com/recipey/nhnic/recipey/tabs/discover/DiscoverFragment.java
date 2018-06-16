package com.recipey.nhnic.recipey.tabs.discover;

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

public class DiscoverFragment extends Fragment {
    private static final String TAG = "DiscoverFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType currentLayoutManagerType;
    protected ParallaxRecyclerView discoverResultsRecyclerView;
    protected DiscoverAdapter discoverAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        assignViews(rootView);
        assignVariables(savedInstanceState);
        assignHandlers();

        return rootView;
    }

    public void assignViews(View rootView) {
        discoverResultsRecyclerView = rootView.findViewById(R.id.discover_results_recycler_view);
    }

    public void assignVariables(Bundle savedInstanceState) {
        discoverAdapter = new DiscoverAdapter(RecipeManager.INSTANCE.getDiscoveredRecipesDTO().recipes);

        layoutManager = new LinearLayoutManager(getActivity());
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            currentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType);
        discoverResultsRecyclerView.setAdapter(discoverAdapter);
    }

    public void assignHandlers() {

    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (discoverResultsRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) discoverResultsRecyclerView.getLayoutManager())
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

        discoverResultsRecyclerView.setLayoutManager(layoutManager);
        discoverResultsRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
}
