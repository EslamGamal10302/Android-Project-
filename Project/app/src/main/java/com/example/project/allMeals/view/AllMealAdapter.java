package com.example.project.allMeals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;

import com.example.project.home.view.HomeAdapter;

import java.util.ArrayList;

public class AllMealAdapter extends  RecyclerView.Adapter<AllMealAdapter.AllMealsViewHolder>{

   ArrayList<Meal>allmeals ;
   Context context ;

   AllMealOnClick  listner ;

    public AllMealAdapter(Context context , AllMealOnClick listener) {
        this.context = context;
        allmeals = new ArrayList<>();
        this. listner=listener;
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
        String [] days ={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};

        Meal meal = allmeals.get(position);
        holder.mealName.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.ShowMealDetails(meal);
            }
        });
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
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
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
        holder.addToFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Boolean clicked = false ;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!clicked){
                    // holder.addToFavourite.setChecked(false);
                    clicked = true;
                    holder.addToFav.setBackgroundResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(context, "Meal Added To Favourite", Toast.LENGTH_SHORT).show();
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
        return allmeals.size();
    }

    public class AllMealsViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        ImageView mealImage;

        ToggleButton addToFav ;

        AutoCompleteTextView autoCompleteTextView;

        ConstraintLayout layout;

        public AllMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.allmeals_name);
            mealImage = itemView.findViewById(R.id.allmeal_imageView);
            addToFav = itemView.findViewById(R.id.btn_addToFavorite);
            autoCompleteTextView = itemView.findViewById(R.id.days_drop_dawn);
            layout = itemView.findViewById(R.id.cons_lay_Selected_ingredient);


        }
    }
}
