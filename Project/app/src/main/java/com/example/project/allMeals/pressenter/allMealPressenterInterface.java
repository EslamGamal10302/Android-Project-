package com.example.project.allMeals.pressenter;

import com.example.project.area.selectedArea.model.Meal;

public interface allMealPressenterInterface {
    public void getAllMeals(String searchLitter);
    public void addToFavorite(Meal meal);
}
