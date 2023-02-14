package com.example.project.allMeals.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

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

public class AllMealsActivity extends AppCompatActivity implements AllMealsViewInterface , AllMealOnClick {
    RecyclerView allRecyclerView;
    LinearLayoutManager layoutManager;
    AllMealAdapter allMealAdapter;

    allMealPressenterInterface pressenter ;

    EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals);
        search = findViewById(R.id.bt_searchForAllMeals);
        allRecyclerView = findViewById(R.id.allmeals_recyclerView);
        layoutManager=new LinearLayoutManager(this);
        pressenter = new allMealsPressenter(GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this),this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        allRecyclerView.setLayoutManager(layoutManager);
        allMealAdapter = new AllMealAdapter(this , this);
        allRecyclerView.setAdapter(allMealAdapter);
       // pressenter.getAllMeals("s");


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    String searchLitter = s.toString();
                    pressenter.getAllMeals(searchLitter);
                } else if (s.length()==0) {
                    allMealAdapter.setList(new ArrayList<>());
                    allMealAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void showAllMeals(ArrayList<Meal> allMeals) {
        if(allMeals.isEmpty()){
            Log.i("error","no data");
        }else {
            allMealAdapter.setList(allMeals);
            allMealAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onAddToFavorite(Meal meal) {
        pressenter.addToFavorite(meal);
    }
}