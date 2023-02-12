package com.example.project.area.selectedArea.model;

import java.util.ArrayList;

public class SelectedResponse {
    private ArrayList<Meal> meals ;

    public ArrayList<Meal> getAreaMeals() {
        return meals;
    }

    public void setAreaMeals(ArrayList<Meal> areaMeals) {
        this.meals = areaMeals;
    }
}
