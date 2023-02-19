package com.example.project.area;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.project.R;

import com.example.project.area.selectedArea.view.SelectedAreaActivity;
import com.example.project.calender.view.CalendarActivity;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AreasActivity extends AppCompatActivity implements AreaOnClickListner{
    RecyclerView myRecycleView;
    LinearLayoutManager myManger;
    AreaAdapter myAdapter;

    EditText search ;
    Area[] areas = {new Area(R.drawable.america,"American"),new Area(R.drawable.england,"British"),new Area(R.drawable.canada,"Canadian"),new Area(R.drawable.chinese,"Chinese"),new Area(R.drawable.croatia,"Croatian"),new Area(R.drawable.netherland,"Dutch"),new Area(R.drawable.egypt,"Egyptian"),new Area(R.drawable.france,"French"),new Area(R.drawable.greece,"Greek"),new Area(R.drawable.indian,"Indian"),new Area(R.drawable.ierland,"Irish"),new Area(R.drawable.italia,"Italian"),new Area(R.drawable.jamaika,"Jamaican"),new Area(R.drawable.japan,"Japanese"),new Area(R.drawable.kenia,"Kenyan"),new Area(R.drawable.malysia,"Malaysian"),new Area(R.drawable.mexico,"Mexican"),new Area(R.drawable.moroco,"Moroccan"),new Area(R.drawable.poland,"Polish"),new Area(R.drawable.porogal,"Portuguese"),new Area(R.drawable.russia,"Russian"),new Area(R.drawable.spain,"Spanish"),new Area(R.drawable.tailand,"Thali"),new Area(R.drawable.tunisa,"Tunisian"),new Area(R.drawable.turkey,"Turkish"),new Area(R.drawable.vitnam,"Vietnamese")};

    ArrayList<Area> MyAreas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);
        MyAreas=ArrangeListOfCountries();
        search = findViewById(R.id.pt_searchForAllMeals);
        myRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.VERTICAL);
        myRecycleView.setLayoutManager(myManger);
        myAdapter = new AreaAdapter(this , MyAreas,this);
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


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter = s.toString();
                FiltterArea( filter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private ArrayList<Area> ArrangeListOfCountries() {
        ArrayList<Area> myAreas = new ArrayList<>();
        myAreas.add(new Area(R.drawable.america,"American"));
        myAreas.add(new Area(R.drawable.england,"British"));
        myAreas.add(new Area(R.drawable.canada,"Canadian"));
        myAreas.add(new Area(R.drawable.chinese,"Chinese"));
        myAreas.add(new Area(R.drawable.croatia,"Croatian"));
        myAreas.add(new Area(R.drawable.netherland,"Dutch"));
        myAreas.add(new Area(R.drawable.egypt,"Egyptian"));
        myAreas.add(new Area(R.drawable.france,"French"));
        myAreas.add(new Area(R.drawable.greece,"Greek"));
        myAreas.add(new Area(R.drawable.indian,"Indian"));
        myAreas.add(new Area(R.drawable.ierland,"Irish"));
        myAreas.add(new Area(R.drawable.italia,"Italian"));
        myAreas.add(new Area(R.drawable.jamaika,"Jamaican"));
        myAreas.add(new Area(R.drawable.japan,"Japanese"));
        myAreas.add(new Area(R.drawable.kenia,"Kenyan"));
        myAreas.add(new Area(R.drawable.malysia,"Malaysian"));
        myAreas.add(new Area(R.drawable.mexico,"Mexican"));
        myAreas.add(new Area(R.drawable.moroco,"Moroccan"));
        myAreas.add(new Area(R.drawable.poland,"Polish"));
        myAreas.add(new Area(R.drawable.porogal,"Portuguese"));
        myAreas.add(new Area(R.drawable.russia,"Russian"));
        myAreas.add(new Area(R.drawable.spain,"Spanish"));
        myAreas.add(new Area(R.drawable.tailand,"Thali"));
        myAreas.add(new Area(R.drawable.tunisa,"Tunisian"));
        myAreas.add(new Area(R.drawable.turkey,"Turkish"));
        myAreas.add(new Area(R.drawable.vitnam,"Vietnamese"));
        return myAreas;
    }

    private void FiltterArea(String s) {
        ArrayList<Area> myFilterAreas = new ArrayList<>();
        for(int i=0 ; i<MyAreas.size() ;i++){
            if(MyAreas.get(i).getNationality().toLowerCase().startsWith(s)){
              myFilterAreas.add(MyAreas.get(i));
            }
        }
        myAdapter.setAreas(myFilterAreas);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(String nationName) {
        Intent intent = new Intent(this, SelectedAreaActivity.class);
        intent.putExtra("area",nationName);
        startActivity(intent);
    }
}