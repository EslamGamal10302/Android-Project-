package com.example.project.DataBase;

import com.example.project.area.selectedArea.model.Meal;

import java.util.List;

import io.reactivex.Observable;

public interface LocalSource {
    public Observable<List<Meal>> getStoredmeals();
    public void delete(Meal meal);

    public void insert(Meal meal);
}
