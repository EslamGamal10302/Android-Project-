package com.example.project.area.selectedArea.presenter;

import com.example.project.area.selectedArea.model.Meal;

public interface SelectedAreaInrerface {
    public void getSelectedAreaMeals(String nationality);
    public void addToFavorite(Meal meal);
}
