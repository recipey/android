package com.recipey.nhnic.recipey.tabs.search;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.dtos.RecipesDTO.*;
import com.squareup.picasso.Picasso;
import com.yayandroid.parallaxrecyclerview.ParallaxImageView;
import com.yayandroid.parallaxrecyclerview.ParallaxViewHolder;

import java.util.ArrayList;

/**
 * Created by nhnic on 5/22/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private final String TAG = "SearchAdapter";

    private ArrayList<Recipe> recipes;

    public static class ViewHolder extends ParallaxViewHolder {
        public CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
        }


        public ViewHolder(View v) {
            super(v);
        }

        public int getParallaxImageId() {
            return 1;
        }
    }

    public SearchAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CardView v = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_recipe, parent, false);
        final ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.cardview_recipe;
    }

    public Recipe getItem(int position) {
        return recipes.get(position);
    }

}
