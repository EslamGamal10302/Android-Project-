package com.example.project.Network;

import com.example.project.area.selectedArea.model.SelectedArea;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("filter.php")
    Observable<SelectedArea> getMealsOfSelectedArea(@Query("a") String areaSelected);

    @GET("list.php?i=list")
    Observable<SelectedArea> getIngredientsList();


    @GET("list.php?c=list")
    Observable<SelectedArea> getCategoriesList();
}
