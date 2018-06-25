package com.recipey.nhnic.recipey.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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


    private APIManager apiManager;
    private SharedPreferences sharedPreferences;

    private RecipesDTO history;
    private HashSet<Long> historyIds;

    HistoryManager() {
        this.apiManager = new APIManager();
        this.history = new RecipesDTO();
        this.history.recipes = new ArrayList<>();
        this.historyIds = new HashSet<>();
        this.sharedPreferences = Application.getInstance().getSharedPreferences(Application.getInstance().getPackageName(), Context.MODE_PRIVATE);
        loadHistory();
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

        saveHistory();
    }

    public void loadHistory() {
        String historyIdStrings = sharedPreferences.getString("RECIPE_HISTORY_IDS", null);
        String historyStrings = sharedPreferences.getString("HISTORY_RECIPES", null);

        if(historyIdStrings != null && historyStrings != null && history.recipes != null) {
            historyIds = new Gson().fromJson(historyIdStrings, new TypeToken<HashSet<Long>>(){}.getType());
            history.recipes = new Gson().fromJson(historyStrings, new TypeToken<ArrayList<RecipesDTO.Recipe>>(){}.getType());
        }
    }

    public void saveHistory() {
        sharedPreferences.edit().putString("RECIPE_HISTORY_IDS", new Gson().toJson(historyIds)).apply();
        sharedPreferences.edit().putString("HISTORY_RECIPES", new Gson().toJson(history.recipes)).apply();
    }
}
