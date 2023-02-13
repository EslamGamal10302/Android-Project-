package com.example.project.allMeals.pressenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.allMeals.view.AllMealsViewInterface;
import com.example.project.area.selectedArea.model.Meal;


import java.util.ArrayList;

public class allMealsPressenter implements allMealPressenterInterface , AreaNetworkDelegate {

    GeneralRepositoryInterface general ;
    AllMealsViewInterface view ;

    public allMealsPressenter(GeneralRepositoryInterface general, AllMealsViewInterface view) {
        this.general = general;
        this.view = view;
    }



    @Override
    public void getAllMeals() {
         general.resultAllMeals(this);
    }

    @Override
    public void addToFavorite(Meal meal) {
        general.insert(meal);
    }

    @Override
    public void onSuccessResponse(ArrayList<Meal> response) {
        view.showAllMeals(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}
