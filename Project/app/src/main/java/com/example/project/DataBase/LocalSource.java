package com.example.project.DataBase;

import com.example.project.area.selectedArea.model.Meal;

import java.util.List;

import io.reactivex.Observable;

public interface LocalSource {
    public Observable<List<Meal>> getStoredmeals();
    public void delete(Meal meal);
    public void insert(Meal meal);
    public Observable<List<Meal>> getSutrdaydmeals();
    public Observable<List<Meal>> getSundaydmeals();
    public Observable<List<Meal>> getMondaymeals();
    public Observable<List<Meal>> getTusdaymeals();
    public Observable<List<Meal>> getWednsdaymeals();
    public Observable<List<Meal>> getThursdaymeals();
    public Observable<List<Meal>> getFridaymeals();
}
