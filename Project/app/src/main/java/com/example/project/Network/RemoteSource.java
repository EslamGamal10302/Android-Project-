package com.example.project.Network;

public interface RemoteSource {
    public  void resultMealsSelectedArea (AreaNetworkDelegate networkDelegate , String nationality);

    public  void resultMealsSelectedCategory (AreaNetworkDelegate networkDelegate , String category);

    public  void resultMealsSelectedIngredient (AreaNetworkDelegate networkDelegate , String ingredient);

    public  void resultIngredientCategory (AreaNetworkDelegate networkDelegate );

    public  void resultCategory (AreaNetworkDelegate networkDelegate );

    //create method get random meals
    public void resultRandomMeals(AreaNetworkDelegate networkDelegate);

    public void resultAllMeals(AreaNetworkDelegate networkDelegate);

    public void resultMealBySearch(AreaNetworkDelegate networkDelegate , String searchLitter);


}
