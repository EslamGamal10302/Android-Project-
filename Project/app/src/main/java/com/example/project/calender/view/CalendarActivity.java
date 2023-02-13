package com.example.project.calender.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.project.calender.Calendar;
import com.example.project.calender.pressenter.CalenderPressenter;
import com.example.project.calender.pressenter.CalenderPressenterInterface;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.SearchActivity;
import com.example.project.home.view.HomeActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CalendarActivity extends AppCompatActivity implements CalenderViewInterface , CalenderOnClickListner{

    CalenderPressenterInterface pressenter ;





    RecyclerView saturdayRecycle;
    CalendarAdapter saturdayAdapter;

    LinearLayoutManager myManger;


    RecyclerView sundayRecycle;
    CalendarAdapter sundayAdapter;

    LinearLayoutManager sundayManger;
    RecyclerView mondayRecycle;
    CalendarAdapter monddayAdapter;

    LinearLayoutManager mondayManger;

    RecyclerView tuesdaydayRecycle;

    CalendarAdapter tuesdaydayAdapter;

    LinearLayoutManager tuesdaydayManger;


    RecyclerView wednesdayRecycle;
    CalendarAdapter wednesdayAdapter;

    LinearLayoutManager wednesdayManger;


    RecyclerView thursdaydayRecycle;
    CalendarAdapter thursdaydayAdapter;

    LinearLayoutManager thursdaydayManger;



    RecyclerView fridayRecycle;
    CalendarAdapter fridayAdapter;

    LinearLayoutManager fridayManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        pressenter = new CalenderPressenter(this ,  GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));



        saturdayRecycle = findViewById(R.id.saturday);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.HORIZONTAL);
        saturdayRecycle.setLayoutManager(myManger);
        saturdayAdapter = new CalendarAdapter(this,this ,findViewById(R.id.txt_saturday) );
        saturdayRecycle.setAdapter(saturdayAdapter);



        sundayManger = new LinearLayoutManager(this);
        sundayManger.setOrientation(RecyclerView.HORIZONTAL);
        sundayRecycle=findViewById(R.id.sunday);
        sundayRecycle.setLayoutManager(sundayManger);
        sundayAdapter= new CalendarAdapter(this,this,findViewById(R.id.txt_sunday));
        sundayRecycle.setAdapter(sundayAdapter);



        mondayManger = new LinearLayoutManager(this);
        mondayManger.setOrientation(RecyclerView.HORIZONTAL);
        mondayRecycle=findViewById(R.id.monday);
        mondayRecycle.setLayoutManager(mondayManger);
        monddayAdapter= new CalendarAdapter(this,this,findViewById(R.id.txt_monday));
        mondayRecycle.setAdapter(monddayAdapter);



         tuesdaydayManger = new LinearLayoutManager(this);
        tuesdaydayManger.setOrientation(RecyclerView.HORIZONTAL);
        tuesdaydayRecycle=findViewById(R.id.tuesday);
        tuesdaydayRecycle.setLayoutManager(tuesdaydayManger);
        tuesdaydayAdapter= new CalendarAdapter(this,this,findViewById(R.id.txt_tuesday));
        tuesdaydayRecycle.setAdapter(tuesdaydayAdapter);



         wednesdayManger= new LinearLayoutManager(this);
        wednesdayManger.setOrientation(RecyclerView.HORIZONTAL);
        wednesdayRecycle=findViewById(R.id.wednesday);
        wednesdayRecycle.setLayoutManager(wednesdayManger);
         wednesdayAdapter= new CalendarAdapter(this,this,findViewById(R.id.txt_wednesday));
        wednesdayRecycle.setAdapter(wednesdayAdapter);




        thursdaydayManger = new LinearLayoutManager(this);
        thursdaydayManger.setOrientation(RecyclerView.HORIZONTAL);
        thursdaydayRecycle=findViewById(R.id.thuresday);
        thursdaydayRecycle.setLayoutManager(thursdaydayManger);
        thursdaydayAdapter= new CalendarAdapter(this,this,findViewById(R.id.txt_thuresday));
        thursdaydayRecycle.setAdapter(thursdaydayAdapter);




        fridayManger = new LinearLayoutManager(this);
        fridayManger.setOrientation(RecyclerView.HORIZONTAL);
        fridayRecycle=findViewById(R.id.friday);
        fridayRecycle.setLayoutManager(fridayManger);
        fridayAdapter= new CalendarAdapter(this,this,findViewById(R.id.txt_friday));
        fridayRecycle.setAdapter(fridayAdapter);


        pressenter.getSaturdayMeals();
        pressenter.getSundayMeals();
        pressenter.getMondayMeals();
        pressenter.getTusdayMeals();
        pressenter.getwednsdayMeals();
        pressenter.getThursdayMeals();
        pressenter.getFridayMeals();


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calScreen);
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
                        startActivity(new Intent(getApplicationContext(), FavActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calScreen:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void OnClick(Meal meal) {
        removeMeal(meal);
    }

    @Override
    public void showSaturdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->saturdayAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void showSundayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->sundayAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void showMondayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->monddayAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void showTusdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->tuesdaydayAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void showwednsdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->wednesdayAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void showThursdayMeals(Observable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->thursdaydayAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void showFridayMeals(Observable<List<Meal>> meals) {
           meals.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(o->fridayAdapter.setList((ArrayList<Meal>) o));
    }

    @Override
    public void removeMeal(Meal meal) {
         pressenter.remove(meal);
    }
}