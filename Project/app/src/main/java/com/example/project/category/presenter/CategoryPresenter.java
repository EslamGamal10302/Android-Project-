package com.example.project.category.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.category.view.CategoryViewInterface;

import java.util.ArrayList;

public class CategoryPresenter implements CategoryInterface, AreaNetworkDelegate {
    CategoryViewInterface view ;

    public CategoryPresenter(CategoryViewInterface view, GeneralRepositoryInterface GR) {
        this.view = view;
        this.GR = GR;
    }

    GeneralRepositoryInterface GR;
    @Override
    public void onSuccessResponse(ArrayList<Meal> response) {
        view.showData(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }

    @Override
    public void getAllCategory() {
     GR.resultCategory(this);
    }
}
