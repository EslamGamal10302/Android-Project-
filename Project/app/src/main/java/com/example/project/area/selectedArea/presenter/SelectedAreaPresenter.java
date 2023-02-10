package com.example.project.area.selectedArea.presenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
import com.example.project.area.selectedArea.view.SelectedAreaViewInterface;

import java.util.ArrayList;

public class SelectedAreaPresenter implements SelectedAreaInrerface , AreaNetworkDelegate {
    SelectedAreaViewInterface view ;
    GeneralRepositoryInterface GR;

    public SelectedAreaPresenter(SelectedAreaViewInterface view, GeneralRepositoryInterface GR) {
        this.view = view;
        this.GR = GR;
    }

    @Override
    public void onSuccessResponse(ArrayList<SelectedAreaMeals> response) {
           view.showData(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }

    @Override
    public void getSelectedAreaMeals(String nationality) {
        GR.resultMealsSelectedArea(this ,nationality);

    }
}
