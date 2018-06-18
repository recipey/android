package com.recipey.nhnic.recipey.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.recipey.nhnic.recipey.app.Application;
import com.recipey.nhnic.recipey.dtos.RecipesDTO;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by nhnic on 6/16/2018.
 */

public enum HistoryManager {
    INSTANCE;

    private final String TAG = "RecipeManager";

    private Context context;
    private APIManager apiManager;
    private SharedPreferences sharedPreferences;

    private RecipesDTO history;
    private HashSet<Long> historyIds;

    HistoryManager() {
        this.context = Application.getInstance();
        this.apiManager = new APIManager();
        this.history = new RecipesDTO();
        this.history.recipes = new ArrayList<>();
        this.historyIds = new HashSet<>();
    }

    public RecipesDTO getHistory() {
        return history;
    }

    public void addHistory(RecipesDTO.Recipe recipe) {
        if(historyIds.contains(recipe.recipeId)) {
            this.history.recipes.remove(recipe);
        } else {
            this.historyIds.add(recipe.recipeId);
        }

        this.history.recipes.add(0, recipe);

        Intent updateHistoryIntent = new Intent();
        updateHistoryIntent.setAction("com.recipey.UPDATE_HISTORY");
        Application.getInstance().sendBroadcast(updateHistoryIntent);
    }
}
