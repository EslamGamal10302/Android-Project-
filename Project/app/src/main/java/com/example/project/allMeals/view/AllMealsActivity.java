package com.example.project.allMeals.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.Network.NetworkConnection;
import com.example.project.R;
import com.example.project.allMeals.pressenter.allMealPressenterInterface;
import com.example.project.allMeals.pressenter.allMealsPressenter;
import com.example.project.area.selectedArea.model.Meal;

import com.example.project.details.view.MealDetails;
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

    ImageView internet_img ;
    TextView internet_1 ;
    TextView internet_2;
    Button internet_retray ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals);
        search = findViewById(R.id.pt_searchForAllMeals);
        allRecyclerView = findViewById(R.id.allmeals_recyclerView);
        internet_img = findViewById(R.id.internet_1);
        internet_1 = findViewById(R.id.internet_2);
        internet_2 = findViewById(R.id.internet_3);
        internet_retray = findViewById(R.id.internet_button);
        layoutManager=new LinearLayoutManager(this);
        pressenter = new allMealsPressenter(GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this),this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        allRecyclerView.setLayoutManager(layoutManager);
        allMealAdapter = new AllMealAdapter(this , this);
        allRecyclerView.setAdapter(allMealAdapter);
       // pressenter.getAllMeals("s");
        checkNetwork();


        internet_retray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetwork();
            }
        });

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

    @Override
    public void ShowMealDetails(Meal meal) {
        String mealName = meal.getStrMeal();
        Intent intent = new Intent(AllMealsActivity.this, MealDetails.class);
        intent.putExtra("name",mealName);
        startActivity(intent);
    }

    private void checkNetwork() {
      if(NetworkConnection.getConnectivity(this)){
          internet_img.setVisibility(View.GONE);
          internet_1.setVisibility(View.GONE);
          internet_2.setVisibility(View.GONE);
          internet_retray.setVisibility(View.GONE);
      }else{
          internet_img.setVisibility(View.VISIBLE);
          internet_1.setVisibility(View.VISIBLE);
          internet_2.setVisibility(View.VISIBLE);
          internet_retray.setVisibility(View.VISIBLE);
      }

    }
}