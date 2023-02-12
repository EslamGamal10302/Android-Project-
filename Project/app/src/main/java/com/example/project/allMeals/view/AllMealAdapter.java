package com.example.project.allMeals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;

import com.example.project.home.view.HomeAdapter;

import java.util.ArrayList;

public class AllMealAdapter extends  RecyclerView.Adapter<AllMealAdapter.AllMealsViewHolder>{

   ArrayList<Meal>allmeals ;
   Context context ;

    public AllMealAdapter(Context context) {
        this.context = context;
        allmeals = new ArrayList<>();
    }

    public void setList(ArrayList<Meal> allmeals){
        this.allmeals = allmeals;
    }



    @NonNull
    @Override
    public AllMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.allmeals_layout, parent ,false);
        AllMealAdapter.AllMealsViewHolder vh = new AllMealAdapter.AllMealsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AllMealsViewHolder holder, int position) {
        Meal meal = allmeals.get(position);
        holder.mealName.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);


    }

    @Override
    public int getItemCount() {
        return allmeals.size();
    }

    public class AllMealsViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        ImageView mealImage;

        public AllMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.allmeals_name);
            mealImage = itemView.findViewById(R.id.allmeal_imageView);


        }
    }
}
