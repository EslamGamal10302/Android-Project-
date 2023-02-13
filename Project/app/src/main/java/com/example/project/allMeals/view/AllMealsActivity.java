package com.example.project.allMeals.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.R;
import com.example.project.allMeals.pressenter.allMealPressenterInterface;
import com.example.project.allMeals.pressenter.allMealsPressenter;
import com.example.project.area.selectedArea.model.Meal;

import com.example.project.home.pressenter.HomePressenter;
import com.example.project.home.pressenter.HomePressenterInterface;
import com.example.project.home.view.HomeAdapter;

import java.util.ArrayList;

public class AllMealsActivity extends AppCompatActivity implements AllMealsViewInterface {
    RecyclerView allRecyclerView;
    LinearLayoutManager layoutManager;
    AllMealAdapter allMealAdapter;

    allMealPressenterInterface pressenter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals);
        allRecyclerView = findViewById(R.id.allmeals_recyclerView);
        layoutManager=new LinearLayoutManager(this);
        pressenter = new allMealsPressenter(GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this),this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        allRecyclerView.setLayoutManager(layoutManager);
        allMealAdapter = new AllMealAdapter(this);
        allRecyclerView.setAdapter(allMealAdapter);
        pressenter.getAllMeals();



    }

    @Override
    public void showAllMeals(ArrayList<Meal> allMeals) {
        allMealAdapter.setList(allMeals);
        allMealAdapter.notifyDataSetChanged();
    }
}