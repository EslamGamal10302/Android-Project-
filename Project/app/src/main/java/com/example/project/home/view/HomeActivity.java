package com.example.project.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.Network.NetworkConnection;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;

import com.example.project.calender.view.CalendarActivity;
import com.example.project.details.view.MealDetails;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.SearchActivity;

import com.example.project.home.pressenter.HomePressenter;
import com.example.project.home.pressenter.HomePressenterInterface;


import com.example.project.login.LoginScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements  HomeViewInterface , HomeOnClickListner{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    HomeAdapter homeAdapter;

    HomePressenterInterface pressenter;

    public static Meal detail;

    ImageView exit;

    FirebaseAuth firebaseAuth;

    ImageView internet_image ;
    TextView internet_1;
    TextView internet_2;
    Button internet_retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeScreen);
        exit=findViewById(R.id.log_out);
        firebaseAuth = FirebaseAuth.getInstance();
        internet_image=findViewById(R.id.internet_1);
        internet_1=findViewById(R.id.internet_2);
        internet_2=findViewById(R.id.internet_3);
        internet_retry=findViewById(R.id.internet_button);
        recyclerView = findViewById(R.id.home_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(this, this);
        recyclerView.setAdapter(homeAdapter);
        checkNetwork();



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.searchScreen:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.homeScreen:
                        return true;
                    case R.id.favScreen:
                        startActivity(new Intent(getApplicationContext(), FavActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.calScreen:
                        startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginScreen.class));
                finish();

            }
        });

        internet_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetwork();
            }
        });


    }

    private  void checkNetwork(){
        if(NetworkConnection.getConnectivity(this)){
            pressenter = new HomePressenter(GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this), this);
            pressenter.getDailyRandomMeals();
            internet_image.setVisibility(View.GONE);
            internet_1.setVisibility(View.GONE);
            internet_2.setVisibility(View.GONE);
            internet_retry.setVisibility(View.GONE);

        }else{
            internet_image.setVisibility(View.VISIBLE);
            internet_1.setVisibility(View.VISIBLE);
            internet_2.setVisibility(View.VISIBLE);
            internet_retry.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void showRandomMeals(ArrayList<Meal> randomMeals) {
        homeAdapter.setList(randomMeals);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddToFavorite(Meal meal) {
          pressenter.addToFavorite(meal);
    }

    @Override
    public void showMealDetails(Meal meal) {
        detail = meal;
        String mealName = meal.getStrMeal();
        Intent intent = new Intent(HomeActivity.this, MealDetails.class);
        intent.putExtra("name",mealName);
        startActivity(intent);

    }
}


