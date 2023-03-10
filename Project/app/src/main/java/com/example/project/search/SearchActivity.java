package com.example.project.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project.Ingredient.view.IngredientActivity;
import com.example.project.R;
import com.example.project.allMeals.view.AllMealsActivity;
import com.example.project.calender.view.CalendarActivity;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.example.project.area.AreasActivity;
import com.example.project.category.view.CategoryActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SearchActivity extends AppCompatActivity {
    Button area;
    Button ingredient;

    Button category;

    Button allmeals ;

    FirebaseAuth firebaseAuth ;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        area=findViewById(R.id.btn_area);
        ingredient=findViewById(R.id.btn_ingradiant);
        category= findViewById(R.id.btn_gategory);
        allmeals = findViewById(R.id.btn_allmeals);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
            }
        });
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AreasActivity.class));
            }
        });
        ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IngredientActivity.class));
            }
        });


        allmeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllMealsActivity.class));
            }
        });


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
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favScreen:
                          if(user!= null){
                              startActivity(new Intent(getApplicationContext(),FavActivity.class));
                              overridePendingTransition(0,0);
                          } else {
                              Toast.makeText(SearchActivity.this, "you must login to enjoy with this feature", Toast.LENGTH_SHORT).show();
                              bottomNavigationView.setSelectedItemId(R.id.searchScreen);
                          }


                        return true;
                    case R.id.calScreen:
                        if(user!= null){
                            startActivity(new Intent(getApplicationContext(),CalendarActivity.class));
                            overridePendingTransition(0,0);
                        } else {
                            Toast.makeText(SearchActivity.this, "you must login to enjoy with this feature", Toast.LENGTH_SHORT).show();
                            bottomNavigationView.setSelectedItemId(R.id.searchScreen);
                        }

                        return true;
                }
                return false;
            }
        });
    }
}