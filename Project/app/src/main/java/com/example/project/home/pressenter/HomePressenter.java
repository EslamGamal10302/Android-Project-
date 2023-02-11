package com.example.project.home.pressenter;

import com.example.project.GeneralRepositoryModel.GeneralRepositoryInterface;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
import com.example.project.home.view.HomeViewInterface;

import java.util.ArrayList;

public class HomePressenter implements HomePressenterInterface , AreaNetworkDelegate {

    GeneralRepositoryInterface genral ;
    HomeViewInterface view ;

    public HomePressenter(GeneralRepositoryInterface genral, HomeViewInterface view) {
        this.genral = genral;
        this.view = view;
    }



    @Override
    public void getDailyRandomMeals() {
        genral.resultRandomMeals(this);
    }

    @Override
    public void onSuccessResponse(ArrayList<SelectedAreaMeals> response) {
        view.showRandomMeals(response);
    }

    @Override
    public void onFailureResponse(String response) {

    }
}
