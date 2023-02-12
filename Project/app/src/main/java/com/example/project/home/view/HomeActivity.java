package com.example.project.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.R;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
import com.example.project.calender.CalendarActivity;
import com.example.project.favourite.FavActivity;
import com.example.project.home.SearchActivity;
import com.example.project.home.pressenter.HomePressenter;
import com.example.project.home.pressenter.HomePressenterInterface;
import com.example.project.model.Meal;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements  HomeViewInterface {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    HomeAdapter homeAdapter;

    HomePressenterInterface pressenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeScreen);

      pressenter = new HomePressenter(GeneralRepository.getInstance(MealClient.getInstance(),this),this);
        recyclerView =findViewById(R.id.home_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(this);
        recyclerView.setAdapter(homeAdapter);
        pressenter.getDailyRandomMeals();

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
                        return true;
                    case R.id.favScreen:
                        startActivity(new Intent(getApplicationContext(), FavActivity.class));
                        overridePendingTransition(0,0);
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
    public void showRandomMeals(ArrayList<SelectedAreaMeals> randomMeals) {
         homeAdapter.setList(randomMeals);
         homeAdapter.notifyDataSetChanged();
    }
}