package com.example.project.category.SelectedCategory.presenter;

import com.example.project.area.selectedArea.model.Meal;

public interface SelectedCategoryInterface {
    public void getSelectedcategoryMeals(String category);

    public void addToFavorite(Meal meal);
}
