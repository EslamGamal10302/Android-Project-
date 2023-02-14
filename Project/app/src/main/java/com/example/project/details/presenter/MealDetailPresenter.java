package com.example.project.details.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.details.view.MealDetailViewInterface;

import java.util.ArrayList;

public class MealDetailPresenter implements MealDetailPresenterInterface , AreaNetworkDelegate {

    GeneralRepositoryInterface GR;

    MealDetailViewInterface view ;

    public MealDetailPresenter(GeneralRepositoryInterface GR ,MealDetailViewInterface view) {
        this.GR = GR;
        this.view=view;
    }

    @Override
    public void addToFavorite(Meal meal) {
       GR.insert(meal);
    }

    @Override
    public void getSelectedMealDetails(String mealName) {
        GR.resultMealBySearch(this,mealName);
    }

    @Override
    public void onSuccessResponse(ArrayList<Meal> response) {
        view.showSelectedMealDetails(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}
