package com.example.project.Network;

import com.example.project.area.selectedArea.model.Meal;

import java.util.ArrayList;

public interface AreaNetworkDelegate {
    public void onSuccessResponse (ArrayList<Meal> response);
    public void onFailureResponse (String response);
}
