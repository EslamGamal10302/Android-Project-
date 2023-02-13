package com.example.project.favourite.view;

import com.example.project.area.selectedArea.model.Meal;

import java.util.List;

import io.reactivex.Observable;

public interface FavViewInterface {
    public void showFav(Observable<List<Meal>> meals);
    public void removeProduct(Meal meal);
}
