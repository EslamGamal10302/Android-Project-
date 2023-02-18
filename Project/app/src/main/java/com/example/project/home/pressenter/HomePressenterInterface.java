package com.example.project.home.pressenter;

import com.example.project.area.selectedArea.model.Meal;

public interface HomePressenterInterface {
    public void getDailyRandomMeals();
    public void addToFavorite(Meal meal);

    public  void deleteAllMeals();
}
