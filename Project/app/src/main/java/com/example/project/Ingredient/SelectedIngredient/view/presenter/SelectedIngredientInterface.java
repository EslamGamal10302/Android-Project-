package com.example.project.Ingredient.SelectedIngredient.view.presenter;

import com.example.project.area.selectedArea.model.Meal;

public interface SelectedIngredientInterface {
    public void getSelectedIngredientMeals(String ingredient);
    public void addToFavorite(Meal meal);
}
