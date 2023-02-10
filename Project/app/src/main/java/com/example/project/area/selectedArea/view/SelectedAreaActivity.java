package com.example.project.area.selectedArea.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.R;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
import com.example.project.area.selectedArea.presenter.SelectedAreaInrerface;
import com.example.project.area.selectedArea.presenter.SelectedAreaPresenter;
import com.example.project.calender.CalendarActivity;
import com.example.project.favourite.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SelectedAreaActivity extends AppCompatActivity implements SelectedAreaViewInterface{
    private String nationality;

    RecyclerView rv;
    SelectedAreaAdapter ad;

    SelectedAreaInrerface presenter;
    RecyclerView.LayoutManager manger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_area);
        Intent recived = getIntent();
        nationality=recived.getStringExtra("area");
        Log.i("eslam",nationality);
        rv = findViewById(R.id.recyclerView);
        manger = new LinearLayoutManager(this);
        ad= new SelectedAreaAdapter( new ArrayList<>(),this );
        presenter = new SelectedAreaPresenter(this, GeneralRepository.getInstance(MealClient.getInstance(),this));
        rv.setLayoutManager(manger);
        rv.setAdapter(ad);
        presenter.getSelectedAreaMeals(nationality);


























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
    public void showData(ArrayList<SelectedAreaMeals> meals) {
       ad.setList(meals);
       ad.notifyDataSetChanged();
    }
}