package com.recipey.nhnic.recipey.dtos;

/**
 * Created by nhnic on 6/7/2018.
 */

public class IngredientsDTO {

    public static class Ingredient {
        public Ingredient(String ingredientAmount, String ingredientName) {
            this.ingredientAmount = ingredientAmount;
            this.ingredientName = ingredientName;
        }

        public String ingredientAmount;

        public String ingredientName;
    }
}
