package com.example.project.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.R;
import com.example.project.calender.CalendarActivity;
import com.example.project.favourite.FavActivity;
import com.example.project.model.Meal;
import com.example.project.search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    HomeAdapter homeAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeScreen);
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


        Meal [] meals ={new Meal("Pasta", R.drawable.allmeals) , new Meal("Eshta",R.drawable.cat)};

        recyclerView =findViewById(R.id.home_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        homeAdapter = new HomeAdapter(this,meals);
        recyclerView.setAdapter(homeAdapter);










    }
}