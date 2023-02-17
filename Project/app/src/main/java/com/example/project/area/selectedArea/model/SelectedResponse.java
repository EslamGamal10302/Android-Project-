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

    public void tofire (){
        ArrayList<HashMap<String , String>> result = new ArrayList<>();
        for(Meal meal :this.meals){
            HashMap<String , String>  resultMap = new HashMap<>();
               resultMap.put("strMeal",meal.getStrMeal());
               resultMap.put("strMealThumb",meal.getStrMeal());
               resultMap.put("strArea",meal.getStrMeal());
               resultMap.put("day",meal.getStrMeal());
               resultMap.put("strIngredient",meal.getStrMeal());
               resultMap.put("strCategory",meal.getStrMeal());
               resultMap.put("idMeal",meal.getStrMeal());
               resultMap.put("strInstructions",meal.getStrMeal());
               resultMap.put("strYoutube",meal.getStrMeal());
               resultMap.put("strIngredient1", meal.getStrIngredient());



            resultMap.put("strMeasure1", meal.getStrIngredient());

        }

    }




}
