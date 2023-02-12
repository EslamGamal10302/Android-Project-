package com.example.project.favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.model.MealMeals;

public class FavAdabter  extends  RecyclerView.Adapter<FavAdabter.FavViewHolder>  {

    Context context;
    MealMeals[] meals;


    public FavAdabter (Context context , MealMeals[] meals){
        this.context = context;
        this.meals = meals;

    }



    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.favourite_meals_layout, parent ,false);
        FavViewHolder vh = new FavViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        String [] days ={"satrday","sunday","monday","tusday"};
        MealMeals meal = meals[position];
        holder.mealName.setText(meal.getStrMeal());
        holder.mealImage.setImageResource(meal.getImage_thumbnail());
       /* ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(context,R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        holder.daysSpinner.setAdapter(adapter);*/


    }

    @Override
    public int getItemCount() {
        return meals.length;
    }

    class FavViewHolder extends RecyclerView.ViewHolder {
        private TextView mealName ;
        private ImageView mealImage;
        private ImageButton removeFromFavourite ;
        private Spinner daysSpinner ;
        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.tv_favourite_meal_name);
            mealImage = itemView.findViewById(R.id.img_favourite_meal);
           removeFromFavourite = itemView.findViewById(R.id.btn_remove_from_fav);


        }
    }

}
