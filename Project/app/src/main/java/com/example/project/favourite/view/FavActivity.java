package com.example.project.favourite.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.calender.view.CalendarActivity;
import com.example.project.details.view.MealDetails;
import com.example.project.favourite.presenter.FavouritePresenterInterface;
import com.example.project.favourite.presenter.favouritePresenter;
import com.example.project.home.SearchActivity;
import com.example.project.home.view.HomeActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavActivity extends AppCompatActivity implements  FavViewInterface , FavOnClickListner {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;


    FavAdabter favAdapter;

    FavouritePresenterInterface favPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        recyclerView =findViewById(R.id.fav_recyclerView);
        layoutManager = new GridLayoutManager(this,2);
      //  layoutManager = new LinearLayoutManager(this);
       // layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        favAdapter = new FavAdabter(this,this);
        recyclerView.setAdapter(favAdapter);
        favPresenter = new favouritePresenter(this , GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));
       favPresenter.getFav();


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.favScreen);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.searchScreen:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.homeScreen:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favScreen:
                        return true;
                    case R.id.calScreen:
                        startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });




    }

    @Override
    public void onClick(Meal meal) {
       removeProduct(meal);
    }

    @Override
    public void showMealDetails(Meal meal) {
        String mealName = meal.getStrMeal();
        Intent intent = new Intent(FavActivity.this, MealDetails.class);
        intent.putExtra("name",mealName);
        startActivity(intent);
    }

    @Override
    public void showFav(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->favAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void removeProduct(Meal meal) {
      favPresenter.remove(meal);
    }
}