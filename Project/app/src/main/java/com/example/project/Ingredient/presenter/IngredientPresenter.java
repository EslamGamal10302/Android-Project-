package com.example.project.Ingredient.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Ingredient.view.IngredientViewInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
import com.example.project.area.selectedArea.view.SelectedAreaViewInterface;

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
    public void onSuccessResponse(ArrayList<SelectedAreaMeals> response) {
        view.showData(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}
