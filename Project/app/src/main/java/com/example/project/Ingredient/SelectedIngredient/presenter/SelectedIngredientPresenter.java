package com.example.project.Ingredient.SelectedIngredient.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Ingredient.SelectedIngredient.view.SelectedIngredientViewInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.Meal;

import java.util.ArrayList;

public class SelectedIngredientPresenter implements SelectedIngredientInterface , AreaNetworkDelegate {
    SelectedIngredientViewInterface view ;
    GeneralRepositoryInterface GR;

    public SelectedIngredientPresenter(SelectedIngredientViewInterface view, GeneralRepositoryInterface GR) {
        this.view = view;
        this.GR = GR;
    }

    @Override
    public void getSelectedIngredientMeals(String ingredient) {
        GR.resultMealsSelectedIngredient(this , ingredient);
    }

    @Override
    public void onSuccessResponse(ArrayList<Meal> response) {
         view.showData(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}
