package com.example.project.area.selectedArea.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.MealClient;
import com.example.project.Network.NetworkConnection;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.area.selectedArea.model.SelectedResponse;
import com.example.project.area.selectedArea.presenter.SelectedAreaInrerface;
import com.example.project.area.selectedArea.presenter.SelectedAreaPresenter;
import com.example.project.calender.view.CalendarActivity;
import com.example.project.details.view.MealDetails;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SelectedAreaActivity extends AppCompatActivity implements SelectedAreaViewInterface , SelectedAreaOnClickListner{
    private String nationality;


    RecyclerView rv;
    SelectedAreaAdapter ad;

    SelectedAreaInrerface presenter;
    RecyclerView.LayoutManager manger;

    EditText search ;

    ArrayList<Meal> myApiMeals ;

    ProgressDialog dialog;

    ImageView internet_img ;
    TextView internet_1 ;
    TextView internet_2;
    Button internet_retray ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_area);
        dialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        dialog.setMessage("Loading....");

        Intent recived = getIntent();
        search = findViewById(R.id.tb_search_area_meals);
        nationality=recived.getStringExtra("area");
        internet_img = findViewById(R.id.internet_1);
        internet_1 = findViewById(R.id.internet_2);
        internet_2 = findViewById(R.id.internet_3);
        internet_retray = findViewById(R.id.internet_button);
        Log.i("eslam",nationality);
        rv = findViewById(R.id.recyclerView);
        manger = new LinearLayoutManager(this);
        ad= new SelectedAreaAdapter( this , this );
       // presenter = new SelectedAreaPresenter(this, GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));
        rv.setLayoutManager(manger);
        rv.setAdapter(ad);
     //   presenter.getSelectedAreaMeals(nationality);
        cehckNetwork ();


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
        this.myApiMeals = meals;
       ad.setList(meals);
       ad.notifyDataSetChanged();

        Log.i("converted",SelectedResponse.convertListToHashMap(meals).get(0).get("strMeal"));
        Log.i("converted",SelectedResponse.convertListToHashMap(meals).get(1).get("strMeal"));
        Log.i("converted",SelectedResponse.convertListToHashMap(meals).get(2).get("strMeal"));
        Log.i("converted",SelectedResponse.convertListToHashMap(meals).get(3).get("strMeal"));
        Log.i("converted",SelectedResponse.convertListToHashMap(meals).get(4).get("strMeal"));

       dialog.dismiss();
    }

    @Override
    public void onAddToFavorite(Meal meal) {
        presenter.addToFavorite(meal);
    }

    @Override
    public void ShowMealDetails(Meal meal) {
        String mealName = meal.getStrMeal();
        Intent intent = new Intent(SelectedAreaActivity.this, MealDetails.class);
        intent.putExtra("name",mealName);
        startActivity(intent);
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


    private void cehckNetwork (){
        if(NetworkConnection.getConnectivity(this)){
            dialog.show();
            presenter = new SelectedAreaPresenter(this, GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this));
            presenter.getSelectedAreaMeals(nationality);
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
