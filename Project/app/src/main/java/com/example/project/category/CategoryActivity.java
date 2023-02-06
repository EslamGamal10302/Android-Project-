package com.example.project.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.Ingredient.IngredientAdapter;
import com.example.project.Ingredient.meals;
import com.example.project.R;
import com.example.project.home.CalendarActivity;
import com.example.project.home.FavActivity;
import com.example.project.home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView myRecycleView;
    LinearLayoutManager myManger;
    CategoryAdapter myAdapter;

    Category[] myCategory = {new Category("Pasta","https://www.themealdb.com/images/category/pasta.png"),new Category("Seafood","https://www.themealdb.com/images/category/seafood.png"),new Category("Starter","https://www.themealdb.com/images/category/starter.png"),new Category("Goat","https://www.themealdb.com/images/category/goat.png"),new Category("Breakfast","https://www.themealdb.com/images/category/breakfast.png"),new Category( "Dessert","https://www.themealdb.com/images/category/dessert.png")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        myRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.VERTICAL);
        myRecycleView.setLayoutManager(myManger);
        myAdapter = new CategoryAdapter(this , myCategory);
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