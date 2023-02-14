package com.example.project.home.view;

import com.example.project.area.selectedArea.model.Meal;

public interface HomeOnClickListner {
   void onAddToFavorite (Meal meal);

   void showMealDetails(Meal meal);
}
