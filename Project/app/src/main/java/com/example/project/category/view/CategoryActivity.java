package com.example.project.category.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
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

import com.example.project.area.selectedArea.model.Meal;
import com.example.project.calender.view.CalendarActivity;
import com.example.project.category.SelectedCategory.view.SelectedCategoryActivity;
import com.example.project.category.presenter.CategoryInterface;
import com.example.project.category.presenter.CategoryPresenter;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements CategoryViewInterface ,CategoryOnClickListner {
    RecyclerView myRecycleView;
    LinearLayoutManager myManger;
    CategoryAdapter myAdapter;

    CategoryInterface presenter;

    ProgressDialog dialog;

    ImageView internet_image ;
    TextView internet1 ;
    TextView internet2;
    Button internet_retray;

    EditText search ;

    ArrayList<Meal> myApiMeals ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        internet_image = findViewById(R.id.internet_1);
        internet1 = findViewById(R.id.internet_2);
        internet2 = findViewById(R.id.internet_3);
        internet_retray = findViewById(R.id.internet_button);
         search = findViewById(R.id.pt_searchForAllMeals);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");

        myRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.VERTICAL);
        myRecycleView.setLayoutManager(myManger);
        myAdapter = new CategoryAdapter(this ,this);
        myRecycleView.setAdapter(myAdapter);
      //  presenter = new CategoryPresenter(this, GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));
     //   presenter.getAllCategory();
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
        myApiMeals = meals;
        myAdapter.setList(meals);
        myAdapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    @Override
    public void onClick(String category) {
        Intent intent = new Intent(this, SelectedCategoryActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);

    }

    private void checkNetwork() {
        if(NetworkConnection.getConnectivity(this)){
            dialog.show();
            presenter = new CategoryPresenter(this, GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));
            presenter.getAllCategory();
            internet_image.setVisibility(View.GONE);
            internet1.setVisibility(View.GONE);
            internet2.setVisibility(View.GONE);
            internet_retray.setVisibility(View.GONE);
        }else{
            internet_image.setVisibility(View.VISIBLE);
            internet1.setVisibility(View.VISIBLE);
            internet2.setVisibility(View.VISIBLE);
            internet_retray.setVisibility(View.VISIBLE);

        }
    }

    private void filtterMeals(CharSequence s) {
        ArrayList<Meal> fillteredMeals = new ArrayList<>();
        for(int j = 0 ; j< myApiMeals.size() ;j++){
            if(myApiMeals.get(j).getStrCategory().toLowerCase().startsWith(s.toString())){
                fillteredMeals.add(myApiMeals.get(j));
            }
            myAdapter.setList(fillteredMeals);
            myAdapter.notifyDataSetChanged();
        }
    }
}