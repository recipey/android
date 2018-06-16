package com.recipey.nhnic.recipey.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.recipey.nhnic.recipey.dtos.RecipeDetailDTO;
import com.recipey.nhnic.recipey.dtos.RecipesDTO;
import com.recipey.nhnic.recipey.main.DummyActivity;
import com.recipey.nhnic.recipey.recipe.RecipeActivity;

/**
 * Created by nhnic on 5/11/2018.
 */

public class GenericActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle onSavedState) {
        super.onCreate(onSavedState);
    }

    public void navigateToRecipe(RecipesDTO.Recipe recipe) {
        Intent intent = new Intent();
        intent.putExtra("RECIPE_ID", recipe.recipeId);
        intent.setClass(this, RecipeActivity.class);
        startActivity(intent);
    }

    public void navigateToRecipeSaved(RecipeDetailDTO recipeDetailDTO) {
        Intent intent = new Intent();
        intent.putExtra("RECIPE_FAVORITE", new Gson().toJson(recipeDetailDTO));
        intent.setClass(this, RecipeActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void navigateToDummy() {
        Intent intent = new Intent();
        intent.setClass(this, DummyActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
