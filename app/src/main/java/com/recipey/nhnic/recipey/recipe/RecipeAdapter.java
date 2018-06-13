package com.recipey.nhnic.recipey.recipe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.Application;
import com.recipey.nhnic.recipey.dtos.IngredientsDTO;
import com.recipey.nhnic.recipey.dtos.RecipeDetailDTO;
import com.squareup.picasso.Picasso;

import java.util.Locale;

/**
 * Created by Nicky on 2/2/2017.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private final String TAG = "RecipeAdapter";

    private RecipeDetailDTO recipeDetailDTO;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView recipeImage;
//        public TextView preparationTime;
        public ImageView shareButton;
        public ImageView linkButton;
        public TextView recipeAuthor;
        public TextView ingredientAmount;
        public TextView ingredientName;
        public TextView instructionNumber;
        public TextView instructionDetail;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

        public ViewHolder(View v) {
            super(v);
        }
    }

    public RecipeAdapter(RecipeDetailDTO recipeDetailDTO) {
        this.recipeDetailDTO = recipeDetailDTO;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CardView v;
        ViewHolder vh;

        switch(viewType) {
            case R.layout.activity_recipe_header:
                v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipe_header, parent, false);
                vh = new ViewHolder(v);
                vh.recipeImage = v.findViewById(R.id.recipe_image);
                vh.shareButton = v.findViewById(R.id.share_button);
                vh.linkButton = v.findViewById(R.id.link_button);
                break;
            case R.layout.activity_recipe_info:
                v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipe_info, parent, false);
                vh = new ViewHolder(v);
                vh.recipeAuthor = v.findViewById(R.id.recipe_author);
                break;
            case R.layout.activity_recipe_ingredients_header:
                v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipe_ingredients_header, parent, false);
                vh = new ViewHolder(v);
                break;
            case R.layout.activity_recipe_ingredients:
                v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipe_ingredients, parent, false);
                vh = new ViewHolder(v);
                vh.ingredientAmount = v.findViewById(R.id.ingredient_amount);
                vh.ingredientName = v.findViewById(R.id.ingredient_name);
                break;
            case R.layout.activity_recipe_instructions_header:
                v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipe_instructions_header, parent, false);
                vh = new ViewHolder(v);
                break;
            default:
                v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipe_instructions, parent, false);
                vh = new ViewHolder(v);
                vh.instructionNumber = v.findViewById(R.id.instruction_number);
                vh.instructionDetail = v.findViewById(R.id.instruction_detail);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        switch(holder.getItemViewType()) {
            case R.layout.activity_recipe_header:
                Picasso.with(Application.getInstance()).load(recipeDetailDTO.recipeImageUrl).fit().centerCrop().into(holder.recipeImage);
                holder.shareButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_SEND);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), "Try out this great recipeDetailDTO from %s! %s", recipeDetailDTO.recipeAuthor, recipeDetailDTO.recipeSourceUrl));
                        i.setType("text/plain");
                        Application.getInstance().startActivity(i);
                    }
                });

                holder.linkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.setData(Uri.parse(recipeDetailDTO.recipeSourceUrl));
                        Application.getInstance().startActivity(i);
                    }
                });
                break;
            case R.layout.activity_recipe_info:
                holder.recipeAuthor.setText(String.format(Locale.getDefault(), "Created by: %s", recipeDetailDTO.recipeAuthor));
                break;
            case R.layout.activity_recipe_ingredients:
                IngredientsDTO.Ingredient ingredient = recipeDetailDTO.ingredients.get(position-3);
                holder.ingredientAmount.setText(ingredient.ingredientAmount);
                holder.ingredientName.setText(ingredient.ingredientName);
                break;
            case R.layout.activity_recipe_instructions:
                RecipeDetailDTO.Instruction instruction = recipeDetailDTO.instructions.get(position-3- recipeDetailDTO.ingredients.size()-1);
                holder.instructionNumber.setText(String.format(Locale.getDefault(), "%d.", instruction.instructionNumber));
                holder.instructionDetail.setText(instruction.instructionDetail);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4+ recipeDetailDTO.ingredients.size()+ recipeDetailDTO.instructions.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return R.layout.activity_recipe_ingredients;
        if(position == 0) {
            return R.layout.activity_recipe_header;
        } else if(position == 1) {
            return R.layout.activity_recipe_info;
        } else if(position == 2) {
            return R.layout.activity_recipe_ingredients_header;
//        } else if(position <= 2+ingredients.size()) {
        } else if(position <= 2+ recipeDetailDTO.ingredients.size()) {
            return R.layout.activity_recipe_ingredients;
//        } else if(position == 2+ingredients.size()+1) {
        } else if(position == 2+ recipeDetailDTO.ingredients.size()+1) {
            return R.layout.activity_recipe_instructions_header;
        } else {
            return R.layout.activity_recipe_instructions;
        }
    }


}
