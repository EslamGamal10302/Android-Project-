package com.example.project.favourite.view;

import com.example.project.area.selectedArea.model.Meal;

public interface FavOnClickListner {
    void onClick(Meal meal);

    void showMealDetails(Meal meal);
}
