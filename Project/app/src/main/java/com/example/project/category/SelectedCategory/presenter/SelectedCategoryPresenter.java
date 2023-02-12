package com.example.project.category.SelectedCategory.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.category.SelectedCategory.view.SelectedCategoryViewInterface;

import java.util.ArrayList;

public class SelectedCategoryPresenter implements SelectedCategoryInterface , AreaNetworkDelegate {
    SelectedCategoryViewInterface view ;

    GeneralRepositoryInterface GR;

    public SelectedCategoryPresenter(SelectedCategoryViewInterface view, GeneralRepositoryInterface GR) {
        this.view = view;
        this.GR = GR;
    }


    @Override
    public void onSuccessResponse(ArrayList<Meal> response) {
       view.showData(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }

    @Override
    public void getSelectedcategoryMeals(String category) {
       GR.resultMealsSelectedCategory(this,category);
    }
}
