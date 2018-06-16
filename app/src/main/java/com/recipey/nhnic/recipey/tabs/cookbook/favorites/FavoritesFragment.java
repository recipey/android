package com.recipey.nhnic.recipey.tabs.cookbook.favorites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.Application;
import com.recipey.nhnic.recipey.dtos.RecipeDetailDTO;
import com.recipey.nhnic.recipey.managers.RecipeManager;
import com.yayandroid.parallaxrecyclerview.ParallaxRecyclerView;

/**
 * Created by nhnic on 6/13/2018.
 */

public class FavoritesFragment extends Fragment {
    private static final String TAG = "FavoritesFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType currentLayoutManagerType;
    protected ParallaxRecyclerView favoritesRecyclerView;
    protected static FavoritesAdapter favoritesAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    private FavoritesUpdateBroadcastReceiver favoritesUpdateBroadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        assignViews(rootView);
        assignVariables(savedInstanceState);
        assignHandlers();

        return rootView;
    }

    public void assignViews(View rootView) {
        favoritesRecyclerView = rootView.findViewById(R.id.favorites_recycler_view);
    }

    public void assignVariables(Bundle savedInstanceState) {
        favoritesAdapter = new FavoritesAdapter(RecipeManager.INSTANCE.getFavorites());

        layoutManager = new LinearLayoutManager(getActivity());
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            currentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType);
        favoritesRecyclerView.setAdapter(favoritesAdapter);

        favoritesUpdateBroadcastReceiver = new FavoritesUpdateBroadcastReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.recipey.UPDATE_FAVORITES");

        Application.getInstance().registerReceiver(favoritesUpdateBroadcastReceiver, filter);
    }

    public void assignHandlers() {

    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (favoritesRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) favoritesRecyclerView.getLayoutManager())
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

        favoritesRecyclerView.setLayoutManager(layoutManager);
        favoritesRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    public static class FavoritesUpdateBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(favoritesAdapter != null) {
                favoritesAdapter.notifyDataSetChanged();
            }
        }
    }
}
