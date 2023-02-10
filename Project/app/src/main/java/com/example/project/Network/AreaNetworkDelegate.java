package com.example.project.Network;

import com.example.project.area.selectedArea.model.SelectedAreaMeals;

import java.util.ArrayList;

public interface AreaNetworkDelegate {
    public void onSuccessResponse (ArrayList<SelectedAreaMeals> response);
    public void onFailureResponse (String response);
}
