package com.example.project.favourite.view;

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

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.model.MealMeals;

import java.util.ArrayList;

public class FavAdabter  extends  RecyclerView.Adapter<FavAdabter.FavViewHolder>  {

    Context context;
    ArrayList<Meal> myMeals;

    FavOnClickListner listner ;


    public FavAdabter (Context context ,FavOnClickListner listner){
        this.context = context;
        this.listner=listner;
        myMeals = new ArrayList<>();

    }

    public  void setList(ArrayList<Meal> meals){
        myMeals = meals;
        notifyDataSetChanged();
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
        Meal myMeal = myMeals.get(position);
        holder.mealName.setText(myMeal.getStrMeal());
        Glide.with(context).load(myMeal.getStrMealThumb()).into(holder.mealImage);
       holder.removeFromFavourite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listner.onClick(myMeal);
           }
       });


    }

    @Override
    public int getItemCount() {
        return myMeals.size();
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
