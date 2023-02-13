package com.example.project.calender.pressenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.calender.view.CalenderViewInterface;

public class CalenderPressenter implements CalenderPressenterInterface {

    CalenderViewInterface view ;

    GeneralRepositoryInterface genral ;


    public CalenderPressenter(CalenderViewInterface view, GeneralRepositoryInterface genral) {
        this.view = view;
        this.genral = genral;
    }

    @Override
    public void getSaturdayMeals() {
        view.showSaturdayMeals(genral.getSutrdaydmeals());
    }

    @Override
    public void getSundayMeals() {
          view.showSundayMeals(genral.getSundaydmeals());
    }

    @Override
    public void getMondayMeals() {
         view.showMondayMeals(genral.getMondaymeals());
    }

    @Override
    public void getTusdayMeals() {
           view.showTusdayMeals(genral.getTusdaymeals());
    }

    @Override
    public void getwednsdayMeals() {
         view.showwednsdayMeals(genral.getWednsdaymeals());
    }

    @Override
    public void getThursdayMeals() {
            view.showThursdayMeals(genral.getThursdaymeals());
    }

    @Override
    public void getFridayMeals() {
         view.showFridayMeals(genral.getFridaymeals());
    }

    @Override
    public void remove(Meal meal) {
          genral.delete(meal);
    }
}
