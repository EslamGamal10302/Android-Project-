package com.example.project.Ingredient.SelectedIngredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;
import com.example.project.category.SelectedCategory.view.SelectedCategoryAdapter;

import java.util.ArrayList;

public class SelectedIngredientAdapter extends RecyclerView.Adapter<SelectedIngredientAdapter.MyViewHolder> {
    ArrayList<SelectedAreaMeals> myMeals;
    Context context ;

    public SelectedIngredientAdapter(Context context) {
        this.context = context;
        myMeals = new ArrayList<>();
    }

    public void setList(ArrayList<SelectedAreaMeals> updateMales )
    {this.myMeals = updateMales;}

    @NonNull
    @Override
    public SelectedIngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selected_ingredient_view, parent , false);
        SelectedIngredientAdapter.MyViewHolder vh = new SelectedIngredientAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedIngredientAdapter.MyViewHolder holder, int position) {
        SelectedAreaMeals meal = myMeals.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.thumbnails);
    }

    @Override
    public int getItemCount() {
        return myMeals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView title ;

        ImageView thumbnails;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_Selected_ingredient);
            title = itemView.findViewById(R.id.text_selected_ingredient);
            thumbnails = itemView.findViewById(R.id.imageView_selected_ingredient);

        }


    }
}
