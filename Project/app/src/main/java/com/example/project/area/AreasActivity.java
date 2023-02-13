package com.example.project.area;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.R;

import com.example.project.area.selectedArea.view.SelectedAreaActivity;
import com.example.project.calender.CalendarActivity;
import com.example.project.favourite.view.FavActivity;
import com.example.project.home.view.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AreasActivity extends AppCompatActivity implements AreaOnClickListner{
    RecyclerView myRecycleView;
    LinearLayoutManager myManger;
    AreaAdapter myAdapter;

    Area[] areas = {new Area(R.drawable.america,"American"),new Area(R.drawable.england,"British"),new Area(R.drawable.canada,"Canadian"),new Area(R.drawable.chinese,"Chinese"),new Area(R.drawable.croatia,"Croatian"),new Area(R.drawable.netherland,"Dutch"),new Area(R.drawable.egypt,"Egyptian"),new Area(R.drawable.france,"French"),new Area(R.drawable.greece,"Greek"),new Area(R.drawable.indian,"Indian"),new Area(R.drawable.ierland,"Irish"),new Area(R.drawable.italia,"Italian"),new Area(R.drawable.jamaika,"Jamaican"),new Area(R.drawable.japan,"Japanese"),new Area(R.drawable.kenia,"Kenyan"),new Area(R.drawable.malysia,"Malaysian"),new Area(R.drawable.mexico,"Mexican"),new Area(R.drawable.moroco,"Moroccan"),new Area(R.drawable.poland,"Polish"),new Area(R.drawable.porogal,"Portuguese"),new Area(R.drawable.russia,"Russian"),new Area(R.drawable.spain,"Spanish"),new Area(R.drawable.tailand,"Thali"),new Area(R.drawable.tunisa,"Tunisian"),new Area(R.drawable.turkey,"Turkish"),new Area(R.drawable.vitnam,"Vietnamese")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);
        myRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        myManger = new LinearLayoutManager(this);
        myManger.setOrientation(RecyclerView.VERTICAL);
        myRecycleView.setLayoutManager(myManger);
        myAdapter = new AreaAdapter(this , areas,this);
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
    }

    @Override
    public void onClick(String nationName) {
        Intent intent = new Intent(this, SelectedAreaActivity.class);
        intent.putExtra("area",nationName);
        startActivity(intent);
    }
}