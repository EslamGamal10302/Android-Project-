package com.example.project.home.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.example.project.area.selectedArea.model.Meal;


import com.example.project.model.MealMeals;

import java.util.ArrayList;

public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.HomeViewHolder>  {
    Activity spinnerContext;

    Context context;
    ArrayList<Meal> rnadomMels;

    HomeOnClickListner listner ;




public HomeAdapter (Context context , HomeOnClickListner listner){
    this.context = context;
    this.listner= listner;
    rnadomMels = new ArrayList<>();

}

public void setList(ArrayList<Meal> randomMeals){
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
    String [] days ={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};

       Meal meal = rnadomMels.get(position);
       holder.mealName.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,days);
        holder.autoCompleteTextView.setAdapter(adapter);
        holder.autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.autoCompleteTextView.showDropDown();
            }
        });
        holder.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = parent.getItemAtPosition(position).toString();
                switch (day) {
                    case "Saturday":
                       meal.setDay("1");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                       break;
                    case "Sunday":
                        meal.setDay("2");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+meal.getDay(), Toast.LENGTH_SHORT).show();
                        break;
                    case "Monday":
                        meal.setDay("3");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Tuesday":
                        meal.setDay("4");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;

                    case "Wednesday":
                        meal.setDay("5");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;

                    case "Thursday":
                        meal.setDay("6");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Friday":
                        meal.setDay("7");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;


                }
              //  Toast.makeText(context, "Meal added to "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            }

        });
        holder.addToFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Boolean clicked = false ;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!clicked){
                   // holder.addToFavourite.setChecked(false);
                    clicked = true;
                    holder.addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(context, "on Click", Toast.LENGTH_SHORT).show();
                   meal.setDay("0");
                   listner.onAddToFavorite(meal);

                } /*else{
                   holder.addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                    Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                } */
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

        AutoCompleteTextView autoCompleteTextView;


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
          mealName = itemView.findViewById(R.id.tv_random_meals_name);
          mealImage = itemView.findViewById(R.id.img_random_meal);
          addToFavourite = itemView.findViewById(R.id.btn_addToFavorite);
          autoCompleteTextView =itemView.findViewById(R.id.days_drop_dawn);

        }
    }

}
