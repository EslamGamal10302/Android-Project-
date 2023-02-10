package com.example.project.area.selectedArea.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.area.selectedArea.model.SelectedAreaMeals;

import java.util.ArrayList;

public class SelectedAreaAdapter extends RecyclerView.Adapter <SelectedAreaAdapter.MyViewHolder>{

    ArrayList<SelectedAreaMeals> myMeals;
    Context context ;

    public SelectedAreaAdapter(ArrayList<SelectedAreaMeals> myMeals, Context context) {
        this.myMeals = myMeals;
        this.context = context;
    }


    public void setList(ArrayList<SelectedAreaMeals> updateMales )
    {this.myMeals = updateMales;}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selected_area_view , parent , false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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
            layout = itemView.findViewById(R.id.cons_lay_Selected_area);
            title = itemView.findViewById(R.id.text_selected_area);
            thumbnails = itemView.findViewById(R.id.imageView_selected_area);

        }


    }
}
