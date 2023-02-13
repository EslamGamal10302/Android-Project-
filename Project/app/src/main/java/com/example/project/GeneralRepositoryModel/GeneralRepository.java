package com.example.project.GeneralRepositoryModel;

import android.content.Context;

import com.example.project.DataBase.LocalSource;
import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.Network.RemoteSource;
import com.example.project.area.selectedArea.model.Meal;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class GeneralRepository implements GeneralRepositoryInterface{

    RemoteSource RS;

    LocalSource LS;

    Context context;


    private static  GeneralRepository gr = null ;

    public GeneralRepository(RemoteSource RS, LocalSource LS, Context context) {
        this.RS = RS;
        this.LS = LS;
        this.context = context;
    }

    public static GeneralRepository getInstance(RemoteSource RS , LocalSource LS ,  Context context){
        if(gr ==null){
            gr = new GeneralRepository(RS , LS , context);
        }
        return gr;
    }

    @Override
    public void resultMealsSelectedArea(AreaNetworkDelegate networkDelegate, String nationality) {
        RS.resultMealsSelectedArea(networkDelegate,nationality);

    }

    @Override
    public void resultMealsSelectedCategory(AreaNetworkDelegate networkDelegate, String category) {
        RS.resultMealsSelectedCategory(networkDelegate,category);
    }

    @Override
    public void resultMealsSelectedIngredient(AreaNetworkDelegate networkDelegate, String ingredient) {
        RS.resultMealsSelectedIngredient(networkDelegate,ingredient);
    }

    @Override
    public void resultIngredientCategory(AreaNetworkDelegate networkDelegate) {
        RS.resultIngredientCategory(networkDelegate);
    }

    @Override
    public void resultCategory(AreaNetworkDelegate networkDelegate) {
        RS.resultCategory(networkDelegate);
    }

    @Override
    public void resultRandomMeals(AreaNetworkDelegate networkDelegate) {
        RS.resultRandomMeals(networkDelegate);
    }

    @Override
    public void resultAllMeals(AreaNetworkDelegate networkDelegate) {
        RS.resultAllMeals(networkDelegate);
    }


    @Override
    public Observable<List<Meal>> getStoredmeals() {
       return LS.getStoredmeals();
    }

    @Override
    public void delete(Meal meal) {
        LS.delete(meal);

    }

    @Override
    public void insert(Meal meal) {
        LS.insert(meal);

    }
}
