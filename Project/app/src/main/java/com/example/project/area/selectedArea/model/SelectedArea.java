package com.example.project.area.selectedArea.model;

import java.util.ArrayList;

public class SelectedArea {
    private ArrayList<SelectedAreaMeals> meals ;

    public ArrayList<SelectedAreaMeals> getAreaMeals() {
        return meals;
    }

    public void setAreaMeals(ArrayList<SelectedAreaMeals> areaMeals) {
        this.meals = areaMeals;
    }
}
