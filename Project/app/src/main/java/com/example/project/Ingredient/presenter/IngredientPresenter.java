package com.example.project.Ingredient.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Ingredient.view.IngredientViewInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.Meal;

import java.util.ArrayList;

public class IngredientPresenter implements IngredientInterface , AreaNetworkDelegate {
    IngredientViewInterface view ;
    GeneralRepositoryInterface GR;

    public IngredientPresenter(IngredientViewInterface view, GeneralRepositoryInterface GR) {
        this.view = view;
        this.GR = GR;
    }

    @Override
    public void getAllIngredient() {
        GR.resultIngredientCategory(this);
    }

    @Override
    public void onSuccessResponse(ArrayList<Meal> response) {
        view.showData(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}
