package com.recipey.nhnic.recipey.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.recipey.nhnic.recipey.app.Application;
import com.recipey.nhnic.recipey.dtos.RecipeDetailDTO;
import com.recipey.nhnic.recipey.dtos.RecipesDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by nhnic on 6/6/2018.
 */

public enum RecipeManager {
    INSTANCE;

    private final String TAG = "RecipeManager";

    private Context context;
    private APIManager apiManager;
    private SharedPreferences sharedPreferences;

    private RecipesDTO recipesDTO;
    private RecipesDTO discoveredRecipesDTO;
    private HashMap<Long, RecipeDetailDTO> recipeDetails; //simulates a backend call for single resource for recipe
    private HashSet<Long> favoriteIds;
    private ArrayList<RecipeDetailDTO> favorites;

    RecipeManager() {
        this.context = Application.getInstance();
        this.apiManager = new APIManager();
        this.recipesDTO = new RecipesDTO();
        this.discoveredRecipesDTO = new RecipesDTO();
        this.recipeDetails = new HashMap<>();
        this.sharedPreferences = Application.getInstance().getSharedPreferences(Application.getInstance().getPackageName(), Context.MODE_PRIVATE);
        this.favoriteIds = new HashSet<>();
        this.favorites = new ArrayList<>();
        loadFavorites();

        //test data
        this.recipeDetails.put((long)1, new RecipeDetailDTO(1, "Seared Ahi Tuna Tacos With Guacamole", "Nicky Huynh", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCQaBD-kga2C-NKq0WSRWAPouX-dNFeCKntpHrKhs58hV36t93", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)2, new RecipeDetailDTO(2, "Dinner With Friends", "Nicky Huynh", "https://media.istockphoto.com/photos/enjoying-dinner-with-friends-picture-id500516612?k=6&m=500516612&s=612x612&w=0&h=FRPjDK9iM8YUfYsUmQtndxJe5jQ1C8-XVcou5OSyJ1Q=", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)3, new RecipeDetailDTO(3, "Lunch and Beer", "Nicky Huynh", "https://images.pexels.com/photos/8313/food-eating-potatoes-beer-8313.jpg?auto=compress&cs=tinysrgb&h=2000", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)5, new RecipeDetailDTO(5, "Chicken Tikki Masala", "Nicky Huynh", "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/chicken_tikka_masala_11482_16x9.jpg", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)4, new RecipeDetailDTO(4, "Traditional Vietnamese Dinner", "Nicky Huynh", "https://assets.vogue.com/photos/5a3aac8f0193fe386b1e3898/master/pass/vietnamese-food-holding.jpg", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)6, new RecipeDetailDTO(6, "Seared Ahi Tuna Tacos With Guacamole", "Nicky Huynh", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCQaBD-kga2C-NKq0WSRWAPouX-dNFeCKntpHrKhs58hV36t93", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)7, new RecipeDetailDTO(7, "Dinner With Friends", "Nicky Huynh", "https://media.istockphoto.com/photos/enjoying-dinner-with-friends-picture-id500516612?k=6&m=500516612&s=612x612&w=0&h=FRPjDK9iM8YUfYsUmQtndxJe5jQ1C8-XVcou5OSyJ1Q=", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)8, new RecipeDetailDTO(8, "Lunch and Beer", "Nicky Huynh", "https://images.pexels.com/photos/8313/food-eating-potatoes-beer-8313.jpg?auto=compress&cs=tinysrgb&h=2000", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)9, new RecipeDetailDTO(9, "Chicken Tikki Masala", "Nicky Huynh", "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/chicken_tikka_masala_11482_16x9.jpg", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)10, new RecipeDetailDTO(10, "Traditional Vietnamese Dinner", "Nicky Huynh", "https://assets.vogue.com/photos/5a3aac8f0193fe386b1e3898/master/pass/vietnamese-food-holding.jpg", "https://www.pexels.com/search/food/"));
    }

    public RecipesDTO getRecipesDTO() {
        //normally get call from backend
        return recipesDTO;
    }

    public void searchRecipes(String searchString) {

    }

    public RecipesDTO getDiscoveredRecipesDTO() {
        discoveredRecipesDTO.recipes = new ArrayList<>();
        return discoveredRecipesDTO;
    }

    public RecipeDetailDTO getRecipe(Long recipeId) {
        //usually would be a function call to the backend to get specific recipe id
        return recipeDetails.get(recipeId);
    }

    public ArrayList<RecipeDetailDTO> getFavorites() {
        return this.favorites;
    }

    public synchronized boolean inFavorites(RecipeDetailDTO recipeDetail) {
        return favoriteIds.contains(recipeDetail.recipeId);
    }

    public synchronized void addFavorite(RecipeDetailDTO recipeDetail) {
        favoriteIds.add(recipeDetail.recipeId);
        favorites.add(recipeDetail);

        Intent intent = new Intent();
        intent.setAction("com.recipey.UPDATE_FAVORITES");
        Application.getInstance().sendBroadcast(intent);

        saveFavorites();
    }

    public synchronized void removeFavorite(RecipeDetailDTO recipeDetail) {
        favoriteIds.remove(recipeDetail.recipeId);
        favorites.remove(recipeDetail);

        Intent intent = new Intent();
        intent.setAction("com.recipey.UPDATE_FAVORITES");
        Application.getInstance().sendBroadcast(intent);

        saveFavorites();
    }

    /*
        Checks if logged in
        Makes call to backend
        Load from backend
        Load from preferences
     */
    private void loadFavorites() {
        String favoriteIdStrings = sharedPreferences.getString("RECIPE_FAVORITE_IDS", null);
        String favoriteStrings = sharedPreferences.getString("FAVORITE_RECIPES", null);

        if(favoriteIdStrings != null && favorites != null) {
            favoriteIds = new Gson().fromJson(favoriteIdStrings, new TypeToken<HashSet<Long>>(){}.getType());
            favorites = new Gson().fromJson(favoriteStrings, new TypeToken<ArrayList<RecipeDetailDTO>>(){}.getType());
            Log.d(TAG, ""+favoriteIds);
        }
    }

    /*
        Checks if logged in
        Makes call to backend
        Saves to backend
        Saves to preferences
     */
    private void saveFavorites() {
        sharedPreferences.edit().putString("RECIPE_FAVORITE_IDS", new Gson().toJson(favoriteIds)).apply();
        sharedPreferences.edit().putString("FAVORITE_RECIPES", new Gson().toJson(favorites)).apply();
    }
}
