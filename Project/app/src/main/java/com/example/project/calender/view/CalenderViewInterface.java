package com.example.project.calender.view;

import com.example.project.area.selectedArea.model.Meal;

import java.util.List;

import io.reactivex.Observable;

public interface CalenderViewInterface {
    public void showSaturdayMeals(Observable<List<Meal>> meals);
    public void showSundayMeals(Observable<List<Meal>> meals);
    public void showMondayMeals(Observable<List<Meal>> meals);
    public void showTusdayMeals(Observable<List<Meal>> meals);
    public void showwednsdayMeals(Observable<List<Meal>> meals);
    public void showThursdayMeals(Observable<List<Meal>> meals);
    public void showFridayMeals(Observable<List<Meal>> meals);
    public void removeMeal(Meal meal);

}
