package com.example.project.calender.view;

import com.example.project.area.selectedArea.model.Meal;

public interface CalenderOnClickListner {
    public void OnClick(Meal meal);

    void showMealDetails(Meal meal);

}
