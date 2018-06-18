package com.recipey.nhnic.recipey.tabs.cookbook.history;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.Application;
import com.recipey.nhnic.recipey.app.GenericActivity;
import com.recipey.nhnic.recipey.dtos.RecipeDetailDTO;
import com.recipey.nhnic.recipey.dtos.RecipesDTO;
import com.squareup.picasso.Picasso;
import com.yayandroid.parallaxrecyclerview.ParallaxImageView;
import com.yayandroid.parallaxrecyclerview.ParallaxViewHolder;

import java.util.ArrayList;

/**
 * Created by nhnic on 5/22/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final String TAG = "HistoryAdapter";

    private ArrayList<RecipesDTO.Recipe> recipes;

    public static class ViewHolder extends ParallaxViewHolder {
        public CardView cardView;

        public ParallaxImageView recipeImage;
        public TextView recipeName;

        public ViewHolder(CardView v) {
            super(v);

            cardView = v;
            recipeImage = v.findViewById(R.id.recipe_image);
            recipeName = v.findViewById(R.id.recipe_name);
        }


        public ViewHolder(View v) {
            super(v);
        }

        public int getParallaxImageId() {
            return R.id.recipe_image;
        }
    }

    public HistoryAdapter(ArrayList<RecipesDTO.Recipe> history) {
        this.recipes = history;
        Log.d(TAG, "qwerqwerqwer");
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_recipe, parent, false);
        final ViewHolder vh = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GenericActivity) parent.getContext()).navigateToRecipe(recipes.get(vh.getLayoutPosition()));
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        RecipesDTO.Recipe recipe = recipes.get(position);

        Picasso.with(Application.getInstance()).load(recipe.recipeImageUrl).resize(300, 300).centerCrop().into(holder.recipeImage);
        holder.recipeName.setText(recipe.recipeName);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.cardview_recipe;
    }

    public RecipesDTO.Recipe getItem(int position) {
        return recipes.get(position);
    }
}
