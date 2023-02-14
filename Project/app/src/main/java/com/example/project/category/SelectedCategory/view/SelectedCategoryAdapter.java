package com.example.project.category.SelectedCategory.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
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

import java.util.ArrayList;

public class SelectedCategoryAdapter extends RecyclerView.Adapter<SelectedCategoryAdapter.MyViewHolder> {

    ArrayList<Meal> myMeals;
    Context context ;

    SelectedCategoryOnClickListner listner;
    public SelectedCategoryAdapter(Context context , SelectedCategoryOnClickListner listner) {
        this.context = context;
        this.listner=listner;
        myMeals= new ArrayList<>();
    }

    public void setList(ArrayList<Meal> updateMales )
    {this.myMeals = updateMales;}

    @NonNull
    @Override
    public SelectedCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selected_category_view, parent , false);
        SelectedCategoryAdapter.MyViewHolder vh = new SelectedCategoryAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedCategoryAdapter.MyViewHolder holder, int position) {
        String [] days ={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
        Meal meal = myMeals.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.thumbnails);
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

                }
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.ShowMealDetails(meal);
            }
        });



    }

    @Override
    public int getItemCount() {
        return myMeals.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView title ;

        ImageView thumbnails;

        ToggleButton addToFavourite ;

        AutoCompleteTextView autoCompleteTextView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_Selected_category);
            title = itemView.findViewById(R.id.text_selected_category);
            thumbnails = itemView.findViewById(R.id.imageView_selected_category);
            addToFavourite = itemView.findViewById(R.id.btn_addToFavorite);
            autoCompleteTextView= itemView.findViewById(R.id.days_drop_dawn);

        }


    }
}
