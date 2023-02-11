package com.example.project.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MealDetails extends AppCompatActivity {
    ImageView mealImage;
    TextView mealName;
    TextView mealContry;
    TextView mealIngradiant;
    TextView ingradiant1;
    TextView ingradiant2;
    TextView ingradiant3;
    TextView ingradiant4;
    TextView ingradiant5;
    TextView ingradiant6;
    TextView ingradiant7;
    TextView steps;
    YouTubePlayerView youTubePlayerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        mealImage = findViewById(R.id.img_details_meal);
        mealName = findViewById(R.id.mealname);
        mealContry =findViewById(R.id.mealContry);
        ingradiant1=findViewById(R.id.ingradiant1);
        ingradiant2=findViewById(R.id.ingradiant2);
        ingradiant3=findViewById(R.id.ingradiant3);
        ingradiant4=findViewById(R.id.ingradiant4);
        ingradiant5=findViewById(R.id.ingradiant5);
        ingradiant6=findViewById(R.id.ingradiant6);
        ingradiant7=findViewById(R.id.ingradiant7);
        steps=findViewById(R.id.steps);
        youTubePlayerView =findViewById(R.id.ybv);




    /*    youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo("mulqW-J3Yy4",0);
                // youTubePlayer.play();
            }
        });*/






    }
}