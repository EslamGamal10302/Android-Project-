package com.example.project.allMeals.pressenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.allMeals.view.AllMealsViewInterface;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;

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
    public void onSuccessResponse(ArrayList<SelectedAreaMeals> response) {
        view.showAllMeals(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}
