package com.example.project.Network;

public interface RemoteSource {
    public  void resultMealsSelectedArea (AreaNetworkDelegate networkDelegate , String nationality);

    public  void resultIngredientCategory (AreaNetworkDelegate networkDelegate );

    public  void resultCategory (AreaNetworkDelegate networkDelegate );

    //create method get random meals
    public void resultRandomMeals(AreaNetworkDelegate networkDelegate);

    public void resultAllMeals(AreaNetworkDelegate networkDelegate);

}
