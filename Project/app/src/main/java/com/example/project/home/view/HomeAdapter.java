package com.example.project.home.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.model.Meal;

public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.HomeViewHolder>  {
    Activity spinnerContext;

    Context context;
    Meal [] meals;


public HomeAdapter (Context context , Meal [] meals){
    this.context = context;
    this.meals = meals;

}



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.random_meals, parent ,false);
        HomeViewHolder vh = new HomeViewHolder(view);
       return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
       String [] days ={"satrday","sunday","monday","tusday"};
       Meal meal = meals[position];
       holder.mealName.setText(meal.getStrMeal());
       holder.mealImage.setImageResource(meal.getImage_thumbnail());
       ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(context,R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        holder.daysSpinner.setAdapter(adapter);
        holder.daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String day = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), day, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.length;
    }



    class HomeViewHolder extends RecyclerView.ViewHolder {
      private TextView mealName ;
        private ImageView mealImage;
        private ImageButton addToFavourite ;
        private Spinner daysSpinner ;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
          mealName = itemView.findViewById(R.id.tv_random_meals_name);
          mealImage = itemView.findViewById(R.id.img_random_meal);
          addToFavourite = itemView.findViewById(R.id.btn_addToFavorite);
          daysSpinner=itemView.findViewById(R.id.days_spinner);

        }
    }

}
