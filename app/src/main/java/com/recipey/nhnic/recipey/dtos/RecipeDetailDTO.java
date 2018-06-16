package com.recipey.nhnic.recipey.dtos;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by nhnic on 5/22/2018.
 */

public class RecipeDetailDTO extends JSONObject{
    public RecipeDetailDTO() {
        ingredients = new ArrayList<>();
        instructions = new ArrayList<>();
    }

    public RecipeDetailDTO(long s1, String s2, String s3, String s4, String s5){
        this();
        recipeId = s1;
        recipeName = s2;
        recipeAuthor = s3;
        recipeImageUrl = s4;
        recipeSourceUrl = s5;

        ingredients = new ArrayList<>();

//        String[] is = new String[]{"Tomatoes", "Grapes", "Olives", "Ribs", "Milk"};
//
//        for (int i = 0; i < 10; i++) {
//            ingredients.add(new IngredientsDTO.Ingredient(String.format(Locale.getDefault(),"%d ounces", i + 10), is[i % 5]));
//        }

        instructions = new ArrayList<>();

//        String[] ins = new String[]{"Boil the tomatoes.", "Spread each slice of bread with thousand island dressing. Top 4 of the bread slices with sauerkraut, cheese and pastrami. Place remaining bread slices on sandwich. Spread margarine on the outsides of each sandwich.", "Stir ketchup, brown sugar, Worcestershire sauce, vinegar, mustard, and lemon juice through the beef mixture. Reduce heat to medium-low and cook mixture at a simmer until mixture is hot and sauce has thickened, about 20 minutes.", "Place a large skillet over medium-high heat. Crumble ground beef into skillet; add onion and celery. Cook and stir beef mixture until beef is completely browned, 7 to 10 minutes.", "Heat a large skillet over medium high heat. Grill until browned, then turn and grill until heated through, and cheese is melted."};
//
//        for (int i = 0; i < 10; i++) {
//            instructions.add(new Instruction(i + 1, ins[i % 5]));
//        }
    }

    @SerializedName("id")
    public long recipeId;

    @SerializedName("name")
    public String recipeName;

    @SerializedName("author")
    public String recipeAuthor;

    @SerializedName("image_url")
    public String recipeImageUrl;

    @SerializedName("source_url")
    public String recipeSourceUrl;

    @SerializedName("ingredients")
    public ArrayList<IngredientsDTO.Ingredient> ingredients;

    @SerializedName("instructions")
    public ArrayList<Instruction> instructions;

    @Override
    public boolean equals(Object a) {
        return ((RecipeDetailDTO)a).recipeId == this.recipeId;
    }

    @Override
    public int hashCode() {
            return Long.valueOf(recipeId).hashCode();
        }

    public static class Instruction {
        public Instruction(int instructionNumber, String instructionDetail) {
            this.instructionNumber = instructionNumber;
            this.instructionDetail = instructionDetail;
        }

        public int instructionNumber;

        public String instructionDetail;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
