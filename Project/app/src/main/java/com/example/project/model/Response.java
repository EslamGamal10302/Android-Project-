package com.example.project.model;

import java.util.ArrayList;

public class Response {

    private ArrayList<Meal> meals ;

    public Response(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
}
