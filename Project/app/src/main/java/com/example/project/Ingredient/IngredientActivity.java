package com.example.project.Ingredient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.R;
import com.example.project.area.AreaAdapter;
import com.example.project.home.CalendarActivity;
import com.example.project.home.FavActivity;
import com.example.project.home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IngredientActivity extends AppCompatActivity {
    RecyclerView myRecycleView;
    LinearLayoutManager myManger;
    IngredientAdapter myAdapter;

    meals[] myMeals = {new meals("Chicken","https://www.themealdb.com/images/ingredients/Chicken.png"),new meals("Salmon","https://www.themealdb.com/images/ingredients/Salmon.png"),new meals("Beef","https://www.themealdb.com/images/ingredients/Beef.png"),new meals("Pork","https://www.themealdb.com/images/ingredients/Pork.png"),new meals("Avocado","https://www.themealdb.com/images/ingredients/Avocado.png"),new meals("Aubergine","https://www.themealdb.com/images/ingredients/Aubergine.png")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        myRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.VERTICAL);
        myRecycleView.setLayoutManager(myManger);
        myAdapter = new IngredientAdapter(this , myMeals);
        myRecycleView.setAdapter(myAdapter);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.searchScreen);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.searchScreen:
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
                        startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}