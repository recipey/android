package com.recipey.nhnic.recipey.dtos;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nhnic on 6/12/2018.
 */

public class RecipesDTO extends JSONObject {
    public RecipesDTO() {
        this.recipes = new ArrayList<>();

        //hard-coded test info
        this.recipes.add(new Recipe((long)1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCQaBD-kga2C-NKq0WSRWAPouX-dNFeCKntpHrKhs58hV36t93", "Seared Ahi Tuna Tacos With Guacamole"));
        this.recipes.add(new Recipe((long)2, "https://media.istockphoto.com/photos/enjoying-dinner-with-friends-picture-id500516612?k=6&m=500516612&s=612x612&w=0&h=FRPjDK9iM8YUfYsUmQtndxJe5jQ1C8-XVcou5OSyJ1Q=", "Dinner With Friends"));
        this.recipes.add(new Recipe((long)3, "https://images.pexels.com/photos/8313/food-eating-potatoes-beer-8313.jpg?auto=compress&cs=tinysrgb&h=2000", "Lunch And Beer"));
        this.recipes.add(new Recipe((long)4, "https://assets.vogue.com/photos/5a3aac8f0193fe386b1e3898/master/pass/vietnamese-food-holding.jpg", "Traditional Vietnamese Dinner"));
        this.recipes.add(new Recipe((long)5, "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/chicken_tikka_masala_11482_16x9.jpg", "Chicken Tikki Masala"));
        this.recipes.add(new Recipe((long)6, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCQaBD-kga2C-NKq0WSRWAPouX-dNFeCKntpHrKhs58hV36t93", "Seared Ahi Tuna Tacos With Guacamole"));
        this.recipes.add(new Recipe((long)7, "https://media.istockphoto.com/photos/enjoying-dinner-with-friends-picture-id500516612?k=6&m=500516612&s=612x612&w=0&h=FRPjDK9iM8YUfYsUmQtndxJe5jQ1C8-XVcou5OSyJ1Q=", "Dinner With Friends"));
        this.recipes.add(new Recipe((long)8, "https://images.pexels.com/photos/8313/food-eating-potatoes-beer-8313.jpg?auto=compress&cs=tinysrgb&h=2000", "Lunch And Beer"));
        this.recipes.add(new Recipe((long)9, "https://assets.vogue.com/photos/5a3aac8f0193fe386b1e3898/master/pass/vietnamese-food-holding.jpg", "Traditional Vietnamese Dinner"));
        this.recipes.add(new Recipe((long)10, "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/chicken_tikka_masala_11482_16x9.jpg", "Chicken Tikki Masala"));
    }

    @SerializedName("recipes")
    public ArrayList<Recipe> recipes;

    public static class Recipe {
        public Recipe(Long recipeId, String recipeImageUrl, String recipeName) {
            this.recipeId = recipeId;
            this.recipeImageUrl = recipeImageUrl;
            this.recipeName = recipeName;
        }

        @SerializedName("recipeId")
        public Long recipeId;

        @SerializedName("recipeImageUrl")
        public String recipeImageUrl;

        @SerializedName("recipeName")
        public String recipeName;
    }
}
