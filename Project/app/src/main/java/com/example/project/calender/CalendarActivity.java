package com.example.project.calender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.R;
import com.example.project.favourite.FavActivity;
import com.example.project.home.SearchActivity;
import com.example.project.home.view.HomeActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarActivity extends AppCompatActivity {
    RecyclerView saturdayRecycle;
    CalendarAdapter saturdayAdapter;

    LinearLayoutManager myManger;


    RecyclerView sundayRecycle;
    CalendarAdapter sundayAdapter;

    LinearLayoutManager sundayManger;
    RecyclerView mondayRecycle;
    CalendarAdapter monddayAdapter;

    LinearLayoutManager mondayManger;

    RecyclerView tuesdaydayRecycle;

    CalendarAdapter tuesdaydayAdapter;

    LinearLayoutManager tuesdaydayManger;


    RecyclerView wednesdayRecycle;
    CalendarAdapter wednesdayAdapter;

    LinearLayoutManager wednesdayManger;


    RecyclerView thursdaydayRecycle;
    CalendarAdapter thursdaydayAdapter;

    LinearLayoutManager thursdaydayManger;



    RecyclerView fridayRecycle;
    CalendarAdapter fridayAdapter;

    LinearLayoutManager fridayManger;

    Calendar [] saturday = {new Calendar("beef",R.drawable.egypt),new Calendar("burger",R.drawable.malysia)};

    Calendar [] sunday = {new Calendar("chicken",R.drawable.england),new Calendar("eslam",R.drawable.netherland)};

    Calendar []  monday = {new Calendar("molokhia",R.drawable.tunisa),new Calendar("melad",R.drawable.canada)};

    Calendar []  tuesday = {new Calendar("fata",R.drawable.turkey),new Calendar("melad",R.drawable.canada)};

    Calendar []  wednesday = {new Calendar("stribs",R.drawable.canada),new Calendar("melad",R.drawable.canada)};

    Calendar []  thuresday = {new Calendar("rice",R.drawable.allmeals),new Calendar("melad",R.drawable.canada)};

    Calendar []  friday = {new Calendar("botatos",R.drawable.chinese),new Calendar("melad",R.drawable.canada)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        saturdayRecycle = findViewById(R.id.saturday);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.HORIZONTAL);
        saturdayRecycle.setLayoutManager(myManger);
        saturdayAdapter = new CalendarAdapter(saturday,this );
        saturdayRecycle.setAdapter(saturdayAdapter);



        sundayManger = new LinearLayoutManager(this);
        sundayManger.setOrientation(RecyclerView.HORIZONTAL);
        sundayRecycle=findViewById(R.id.sunday);
        sundayRecycle.setLayoutManager(sundayManger);
        sundayAdapter= new CalendarAdapter(sunday,this);
        sundayRecycle.setAdapter(sundayAdapter);



        mondayManger = new LinearLayoutManager(this);
        mondayManger.setOrientation(RecyclerView.HORIZONTAL);
        mondayRecycle=findViewById(R.id.monday);
        mondayRecycle.setLayoutManager(mondayManger);
        monddayAdapter= new CalendarAdapter(monday,this);
        mondayRecycle.setAdapter(monddayAdapter);



        tuesdaydayManger = new LinearLayoutManager(this);
        tuesdaydayManger.setOrientation(RecyclerView.HORIZONTAL);
        tuesdaydayRecycle=findViewById(R.id.tuesday);
        tuesdaydayRecycle.setLayoutManager(tuesdaydayManger);
        tuesdaydayAdapter= new CalendarAdapter(tuesday,this);
        tuesdaydayRecycle.setAdapter(tuesdaydayAdapter);



         wednesdayManger= new LinearLayoutManager(this);
        wednesdayManger.setOrientation(RecyclerView.HORIZONTAL);
        wednesdayRecycle=findViewById(R.id.wednesday);
        wednesdayRecycle.setLayoutManager(wednesdayManger);
         wednesdayAdapter= new CalendarAdapter(wednesday,this);
        wednesdayRecycle.setAdapter(wednesdayAdapter);




        thursdaydayManger = new LinearLayoutManager(this);
        thursdaydayManger.setOrientation(RecyclerView.HORIZONTAL);
        thursdaydayRecycle=findViewById(R.id.thuresday);
        thursdaydayRecycle.setLayoutManager(thursdaydayManger);
        thursdaydayAdapter= new CalendarAdapter(thuresday,this);
        thursdaydayRecycle.setAdapter(thursdaydayAdapter);




        fridayManger = new LinearLayoutManager(this);
        fridayManger.setOrientation(RecyclerView.HORIZONTAL);
        fridayRecycle=findViewById(R.id.friday);
        fridayRecycle.setLayoutManager(fridayManger);
        fridayAdapter= new CalendarAdapter(friday,this);
        fridayRecycle.setAdapter(fridayAdapter);





        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calScreen);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.searchScreen:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
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
                        return true;
                }
                return false;
            }
        });
    }
}