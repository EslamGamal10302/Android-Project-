package com.example.project.details.presenter;

import com.example.project.area.selectedArea.model.Meal;

public interface MealDetailPresenterInterface {
    public void addToFavorite(Meal meal);

    public void getSelectedMealDetails(String mealName);
}
