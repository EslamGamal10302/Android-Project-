package com.example.project.category.SelectedCategory.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.calender.view.CalendarActivity;
import com.example.project.category.SelectedCategory.presenter.SelectedCategoryInterface;
import com.example.project.category.SelectedCategory.presenter.SelectedCategoryPresenter;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SelectedCategoryActivity extends AppCompatActivity implements SelectedCategoryViewInterface,SelectedCategoryOnClickListner {
    private String category ;

    RecyclerView rv;
    SelectedCategoryAdapter ad;

    SelectedCategoryInterface presenter;
    RecyclerView.LayoutManager manger;

    ArrayList<Meal> myApiMeals ;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_category);
        search = findViewById(R.id.tb_search_category_meals);
        Intent recived = getIntent();
        category = recived.getStringExtra("category");
        Log.i("eslam",category);
        rv = findViewById(R.id.recyclerView);
        manger = new LinearLayoutManager(this);
        ad= new SelectedCategoryAdapter( this ,this );
        presenter = new SelectedCategoryPresenter(this, GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));
        rv.setLayoutManager(manger);
        rv.setAdapter(ad);
        presenter.getSelectedcategoryMeals(category);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                           filtterMeals(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        myApiMeals= meals ;
        ad.setList(meals);
        ad.notifyDataSetChanged();
    }

    @Override
    public void onAddToFavorite(Meal meal) {
        presenter.addToFavorite(meal);
    }

    public void filtterMeals(CharSequence s  ){
        ArrayList<Meal> fillteredMeals = new ArrayList<>();
        for(int j = 0 ; j< myApiMeals.size() ;j++){
            if(myApiMeals.get(j).getStrMeal().toLowerCase().startsWith(s.toString())){
                fillteredMeals.add(myApiMeals.get(j));
            }
            ad.setList(fillteredMeals);
            ad.notifyDataSetChanged();
        }
    }
}