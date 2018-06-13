package com.recipey.nhnic.recipey.recipe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.Application;
import com.recipey.nhnic.recipey.app.GenericActivity;
import com.recipey.nhnic.recipey.dtos.RecipeDetailDTO;
import com.recipey.nhnic.recipey.managers.RecipeManager;

/**
 * Created by bummy on 7/25/17.
 */

public class RecipeActivity extends GenericActivity {
    private final String TAG = "RecipeActivity";
    private SharedPreferences sharedPreferences;

    private ImageView backButton;
    private ImageView favoriteButton;
    public TextView recipeTitle;

    private RecipeDetailDTO recipeDetailDTO;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        assignViews();
        assignVariables(savedInstanceState);
        assignHandlers();
    }

    private void assignViews() {
        backButton = findViewById(R.id.back_button);
        favoriteButton = findViewById(R.id.favorite_button);
        recipeTitle = findViewById(R.id.recipe_title);

        recyclerView = findViewById(R.id.recipe_details_recyclerview);
    }

    private void assignVariables(Bundle savedInstanceState) {
        sharedPreferences = Application.getInstance().getSharedPreferences(Application.getInstance().getPackageName(), Context.MODE_PRIVATE);

        Long recipeId = getIntent().getLongExtra("RECIPE_ID", -1);

        Log.d(TAG, recipeId + "");

        if(recipeId == -1) {
            //if it's -1 then return a message saying there has been an error
            recipeDetailDTO = RecipeManager.INSTANCE.getRecipe((long)1);
        } else {
            recipeDetailDTO = RecipeManager.INSTANCE.getRecipe(recipeId);
        }

        recipeTitle.setText(recipeDetailDTO.recipeName);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(recipeDetailDTO);
        recyclerView.setAdapter(recipeAdapter);

        if(RecipeManager.INSTANCE.inFavorites(recipeDetailDTO)) {
            favoriteButton.setColorFilter(Color.parseColor("RED"));
        }
    }

    private void assignHandlers() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RecipeManager.INSTANCE.inFavorites(recipeDetailDTO)) {
                    RecipeManager.INSTANCE.removeFavorite(recipeDetailDTO);
                    favoriteButton.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.white_on_black, null));
                } else {
                    RecipeManager.INSTANCE.addFavorite(recipeDetailDTO);
                    favoriteButton.setColorFilter(Color.parseColor("RED"));
                }
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}
