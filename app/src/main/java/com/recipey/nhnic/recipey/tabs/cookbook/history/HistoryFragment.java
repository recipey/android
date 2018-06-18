package com.recipey.nhnic.recipey.tabs.cookbook.history;

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
import com.recipey.nhnic.recipey.managers.HistoryManager;
import com.yayandroid.parallaxrecyclerview.ParallaxRecyclerView;

/**
 * Created by nhnic on 6/16/2018.
 */

public class HistoryFragment extends Fragment {
    private static final String TAG = "HistoryFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType currentLayoutManagerType;
    protected ParallaxRecyclerView historyRecyclerView;
    protected static HistoryAdapter historyAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    private HistoryUpdateBroadcastReceiver historyUpdateBroadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        assignViews(rootView);
        assignVariables(savedInstanceState);
        assignHandlers();

        return rootView;
    }

    public void assignViews(View rootView) {
        historyRecyclerView = rootView.findViewById(R.id.history_recycler_view);
    }

    public void assignVariables(Bundle savedInstanceState) {
        historyAdapter = new HistoryAdapter(HistoryManager.INSTANCE.getHistory().recipes);

        layoutManager = new LinearLayoutManager(getActivity());
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            currentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType);
        historyRecyclerView.setAdapter(historyAdapter);

        historyUpdateBroadcastReceiver = new HistoryUpdateBroadcastReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.recipey.UPDATE_HISTORY");

        Application.getInstance().registerReceiver(historyUpdateBroadcastReceiver, filter);
    }

    public void assignHandlers() {

    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (historyRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) historyRecyclerView.getLayoutManager())
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

        historyRecyclerView.setLayoutManager(layoutManager);
        historyRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    public static class HistoryUpdateBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(historyAdapter != null) {
                historyAdapter.notifyDataSetChanged();
                Log.d(TAG, "asdfasdfasdf");
            } else {
                Log.d(TAG, "zxcvzxcvzxcv");
            }
        }
    }
}
