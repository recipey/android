package com.recipey.nhnic.recipey.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.recipey.nhnic.recipey.dtos.RecipesDTO;
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
}
