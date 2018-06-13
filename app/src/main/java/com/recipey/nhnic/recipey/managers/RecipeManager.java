package com.recipey.nhnic.recipey.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.recipey.nhnic.recipey.app.Application;
import com.recipey.nhnic.recipey.dtos.RecipeDetailDTO;
import com.recipey.nhnic.recipey.dtos.RecipesDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
    private HashMap<Long, RecipeDetailDTO> recipeDetails; //simulates a backend call for single resource for recipe
    private HashSet<RecipeDetailDTO> favorites;

    RecipeManager() {
        this.context = Application.getInstance();
        this.apiManager = new APIManager();
        this.recipesDTO = new RecipesDTO();
        this.recipeDetails = new HashMap<>();
        this.sharedPreferences = Application.getInstance().getSharedPreferences(Application.getInstance().getPackageName(), Context.MODE_PRIVATE);
        this.favorites = new HashSet<>();
        getFavorites();

        //test data
        this.recipeDetails.put((long)1, new RecipeDetailDTO(1, "Seared Ahi Tuna Tacos With Guacamole", "Nicky Huynh", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCQaBD-kga2C-NKq0WSRWAPouX-dNFeCKntpHrKhs58hV36t93", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)2, new RecipeDetailDTO(2, "Dinner With Friends", "Nicky Huynh", "https://media.istockphoto.com/photos/enjoying-dinner-with-friends-picture-id500516612?k=6&m=500516612&s=612x612&w=0&h=FRPjDK9iM8YUfYsUmQtndxJe5jQ1C8-XVcou5OSyJ1Q=", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)3, new RecipeDetailDTO(3, "Lunch and Beer", "Nicky Huynh", "https://images.pexels.com/photos/8313/food-eating-potatoes-beer-8313.jpg?auto=compress&cs=tinysrgb&h=2000", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)4, new RecipeDetailDTO(4, "Chicken Tikki Masala", "Nicky Huynh", "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/chicken_tikka_masala_11482_16x9.jpg", "https://www.pexels.com/search/food/"));
        this.recipeDetails.put((long)5, new RecipeDetailDTO(5, "Traditional Vietnamese Dinner", "Nicky Huynh", "https://assets.vogue.com/photos/5a3aac8f0193fe386b1e3898/master/pass/vietnamese-food-holding.jpg", "https://www.pexels.com/search/food/"));
    }

    public RecipesDTO getRecipesDTO() {
        //normally get call from backend
        return recipesDTO;
    }

    public void searchRecipes(String searchString) {

    }

    public RecipeDetailDTO getRecipe(Long recipeId) {
        //usually would be a function call to the backend to get specific recipe id
        return recipeDetails.get(recipeId);
    }

    public synchronized boolean inFavorites(RecipeDetailDTO recipeDetail) {
        return favorites.contains(recipeDetail);
    }

    public synchronized void addFavorite(RecipeDetailDTO recipeDetail) {
        if(favorites.contains(recipeDetail)) {
            Log.d("RecipeManager", recipeDetail.toString());
        }
        favorites.add(recipeDetail);
        saveFavorites();
    }

    public synchronized  void removeFavorite(RecipeDetailDTO recipeDetail) {
        favorites.remove(recipeDetail);
    }

    /*
        Checks if logged in
        Makes call to backend
        Load from backend
        Load from preferences
     */
    private void getFavorites() {
        String favoriteString = sharedPreferences.getString("RECIPE_FAVORITES", null);
        if(favoriteString != null) {
            Log.d(TAG, favoriteString);
            favorites = new Gson().fromJson(favoriteString, favorites.getClass());
        }
    }

    /*
        Checks if logged in
        Makes call to backend
        Saves to backend
        Saves to preferences
     */
    private void saveFavorites() {
        String jsonString=new Gson().toJson(favorites);
        Log.d(TAG, jsonString);
        sharedPreferences.edit().putString("RECIPE_FAVORITES", new Gson().toJson(favorites)).apply();
    }
}
