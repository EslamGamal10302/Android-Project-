package com.example.project.calender.pressenter;

import com.example.project.area.selectedArea.model.Meal;

public interface CalenderPressenterInterface {
    public void getSaturdayMeals();
    public void getSundayMeals();
    public void getMondayMeals();
    public void getTusdayMeals();
    public void getwednsdayMeals();
    public void getThursdayMeals();
    public void getFridayMeals();
    public void remove(Meal meal);
}
