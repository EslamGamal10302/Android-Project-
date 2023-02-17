package com.example.project.area.selectedArea.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SelectedResponse {
    private ArrayList<Meal> meals ;



    public ArrayList<Meal> getAreaMeals() {
        return meals;
    }

    public void setAreaMeals(ArrayList<Meal> areaMeals) {
        this.meals = areaMeals;
    }



    public HashMap<String , Meal> toJson(){
       HashMap<String , Meal>  resultMap = new HashMap<>();
       for(Meal meal :this.meals){
           resultMap.put(meal.getIdMeal(),meal);
       }

        return resultMap;
    }
}
