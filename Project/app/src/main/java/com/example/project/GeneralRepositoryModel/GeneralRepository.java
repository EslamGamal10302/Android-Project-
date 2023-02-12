package com.example.project.GeneralRepositoryModel;

import android.content.Context;

import com.example.project.Network.AreaNetworkDelegate;
import com.example.project.Network.RemoteSource;

public class GeneralRepository implements GeneralRepositoryInterface{
    Context context;
    RemoteSource RS;


    private static  GeneralRepository gr = null ;

    public GeneralRepository(Context context, RemoteSource RS) {
        this.context = context;
        this.RS = RS;
    }

    public static GeneralRepository getInstance(RemoteSource RS , Context context){
        if(gr ==null){
            gr = new GeneralRepository(context,RS);
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


}
