package com.example.project.DataBase;

import android.content.Context;

import com.example.project.area.selectedArea.model.Meal;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataBaseRepository implements LocalSource {
    private Context context ;
    private MealDAO mealDAO;
    private Observable<List<Meal>> storedMeals;

    public DataBaseRepository(Context context) {
        this.context = context;
        AppDataBase db =AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.mealsDAO();
        storedMeals = mealDAO.getAllmeals();
    }

    private static  DataBaseRepository repo = null ;

    public static  DataBaseRepository getInstance(Context context){
        if(repo ==null){
            repo = new DataBaseRepository(context);
        }
        return repo;
    }

    @Override
    public Observable<List<Meal>> getStoredmeals() {
        return storedMeals;
    }

    @Override
    public void delete(Meal meal) {

        mealDAO.deleteMeal(meal).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void insert(Meal meal) {
        mealDAO.insertMeal(meal).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
