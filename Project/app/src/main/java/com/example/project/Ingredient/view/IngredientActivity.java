package com.example.project.Ingredient.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Ingredient.SelectedIngredient.view.SelectedIngredientActivity;
import com.example.project.Ingredient.presenter.IngredientInterface;
import com.example.project.Ingredient.presenter.IngredientPresenter;
import com.example.project.Network.MealClient;
import com.example.project.R;

import com.example.project.area.selectedArea.model.Meal;
import com.example.project.calender.view.CalendarActivity;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class IngredientActivity extends AppCompatActivity implements IngredientViewInterface , IngredientOnClickListner{
    RecyclerView myRecycleView;
    LinearLayoutManager myManger;
    IngredientAdapter myAdapter;

    IngredientInterface presnter;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        dialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        dialog.setMessage("Loading....");
        dialog.show();
        myRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.VERTICAL);
        myRecycleView.setLayoutManager(myManger);
        myAdapter = new IngredientAdapter(this , this );
        myRecycleView.setAdapter(myAdapter);
        presnter = new IngredientPresenter(this, GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));
        presnter.getAllIngredient();








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

    @Override
    public void showData(ArrayList<Meal> meals) {
       myAdapter.setList(meals);
       myAdapter.notifyDataSetChanged();
       dialog.dismiss();
    }

    @Override
    public void onClick(String ingredient) {
        Intent intent = new Intent(this , SelectedIngredientActivity.class);
        intent.putExtra("ingredient",ingredient);
        startActivity(intent);
    }
}