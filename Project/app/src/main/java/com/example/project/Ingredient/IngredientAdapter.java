package com.example.project.Ingredient;

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
import com.example.project.area.Area;
import com.example.project.area.AreaAdapter;

public class IngredientAdapter  extends RecyclerView.Adapter <IngredientAdapter.MyViewHolder> {
    Context context ;
    meals [] myMeals;

    public IngredientAdapter(Context context, meals[] myMeals) {
        this.context = context;
        this.myMeals = myMeals;
    }






    @NonNull
    @Override
    public IngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient_view , parent , false);
        IngredientAdapter.MyViewHolder vh = new IngredientAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.MyViewHolder holder, int position) {
        meals meal = myMeals[position];
        holder.ingredient.setText(meal.getStrIngredient());
        Glide.with(context).load(meal.getImage()).into(holder.thumbnails);
    }

    @Override
    public int getItemCount() {
        return  myMeals.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView ingredient ;
        ImageView thumbnails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_inger);
            ingredient = itemView.findViewById(R.id.text_inger);
            thumbnails = itemView.findViewById(R.id.imageView_inger);

        }


    }

}
