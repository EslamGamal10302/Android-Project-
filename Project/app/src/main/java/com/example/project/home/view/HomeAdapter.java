package com.example.project.home.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
import com.example.project.model.Meal;
import com.example.project.model.MealMeals;

import java.util.ArrayList;

public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.HomeViewHolder>  {
    Activity spinnerContext;

    Context context;
    ArrayList<SelectedAreaMeals> rnadomMels;


public HomeAdapter (Context context){
    this.context = context;
    rnadomMels = new ArrayList<>();

}

public void setList(ArrayList<SelectedAreaMeals> randomMeals){
    this.rnadomMels = randomMeals;
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

       SelectedAreaMeals meal = rnadomMels.get(position);
       holder.mealName.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);
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
        holder.addToFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.addToFavourite.setChecked(true);
                    holder.addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(context, "on Click", Toast.LENGTH_SHORT).show();

                }else{
                   holder.addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                    Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rnadomMels.size();
    }



    class HomeViewHolder extends RecyclerView.ViewHolder {
      private TextView mealName ;
        private ImageView mealImage;
        private ToggleButton addToFavourite ;
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
