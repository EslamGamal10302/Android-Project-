package com.example.project.Network;


import android.util.Log;

import com.example.project.area.selectedArea.model.SelectedResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MealClient implements RemoteSource{
    private static MealClient client = null ;

    public MealClient() {

    }

    public static MealClient getInstance(){
        if(client ==null){
            client = new MealClient();
        }
        return client;
    }

    public MealService RetrofitCall() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit myRetrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").
                addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MealService myApi = myRetrofit.create(MealService.class);

        return myApi;
    }


    @Override
    public void resultMealsSelectedArea(AreaNetworkDelegate networkDelegate , String nationality) {
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getMealsOfSelectedArea(nationality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );

    }

    @Override
    public void resultMealsSelectedCategory(AreaNetworkDelegate networkDelegate, String category) {
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getMealsOfSelectedCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }

    @Override
    public void resultMealsSelectedIngredient(AreaNetworkDelegate networkDelegate, String ingredient) {
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getMealsOfSelectedIngredient(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }

    @Override
    public void resultIngredientCategory(AreaNetworkDelegate networkDelegate) {
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getIngredientsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }

    @Override
    public void resultCategory(AreaNetworkDelegate networkDelegate) {
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getCategoriesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }


    @Override
    public void resultRandomMeals(AreaNetworkDelegate networkDelegate){
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getRandomMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }


    @Override
    public void resultAllMeals(AreaNetworkDelegate networkDelegate){
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getAllMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }

    @Override
    public void resultMealBySearch(AreaNetworkDelegate networkDelegate , String searchLitter) {
        MealService myApi=RetrofitCall();
        Observable<SelectedResponse> observable = myApi.getMealsBySearch(searchLitter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals()),
                e->Log.i("milad" , "error"+e.getMessage())
        );
    }


}
