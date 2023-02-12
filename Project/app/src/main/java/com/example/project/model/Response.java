package com.example.project.model;

import java.util.ArrayList;

public class Response {

    private ArrayList<MealMeals> meals ;

    public Response(ArrayList<MealMeals> meals) {
        this.meals = meals;
    }

    public ArrayList<MealMeals> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealMeals> meals) {
        this.meals = meals;
    }
}
