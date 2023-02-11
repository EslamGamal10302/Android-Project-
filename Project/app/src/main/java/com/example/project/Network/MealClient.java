package com.example.project.Network;

import com.example.project.area.selectedArea.model.SelectedArea;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
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
        Observable<SelectedArea> observable = myApi.getMealsOfSelectedArea(nationality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );

    }

    @Override
    public void resultMealsSelectedCategory(AreaNetworkDelegate networkDelegate, String category) {
        MealService myApi=RetrofitCall();
        Observable<SelectedArea> observable = myApi.getMealsOfSelectedCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }

    @Override
    public void resultMealsSelectedIngredient(AreaNetworkDelegate networkDelegate, String ingredient) {
        MealService myApi=RetrofitCall();
        Observable<SelectedArea> observable = myApi.getMealsOfSelectedIngredient(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }

    @Override
    public void resultIngredientCategory(AreaNetworkDelegate networkDelegate) {
        MealService myApi=RetrofitCall();
        Observable<SelectedArea> observable = myApi.getIngredientsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }

    @Override
    public void resultCategory(AreaNetworkDelegate networkDelegate) {
        MealService myApi=RetrofitCall();
        Observable<SelectedArea> observable = myApi.getCategoriesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o->networkDelegate.onSuccessResponse(o.getAreaMeals())
        );
    }
}
