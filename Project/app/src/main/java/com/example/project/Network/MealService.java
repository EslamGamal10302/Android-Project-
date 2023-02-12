package com.example.project.Network;

import com.example.project.area.selectedArea.model.SelectedResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("filter.php")
    Observable<SelectedResponse> getMealsOfSelectedArea(@Query("a") String areaSelected);

    @GET("filter.php")
    Observable<SelectedResponse> getMealsOfSelectedCategory(@Query("c") String categorySelected);



    @GET("filter.php")
    Observable<SelectedResponse> getMealsOfSelectedIngredient(@Query("i") String ingredientSelected);


    @GET("list.php?i=list")
    Observable<SelectedResponse> getIngredientsList();


    @GET("list.php?c=list")
    Observable<SelectedResponse> getCategoriesList();
}
