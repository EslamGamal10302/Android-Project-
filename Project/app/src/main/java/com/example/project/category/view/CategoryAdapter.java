package com.example.project.category.view;

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
import com.example.project.category.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter <CategoryAdapter.MyViewHolder>{
    Context context ;
    ArrayList<SelectedAreaMeals> myMeals;

    private String url_part1="https://www.themealdb.com/images/category/";

    private  String url_part2=".png";

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_view , parent , false);
        CategoryAdapter.MyViewHolder vh = new CategoryAdapter.MyViewHolder(view);
        return vh;
    }

    public CategoryAdapter(Context context) {
        this.context = context;
        myMeals=new ArrayList<>();
    }


    public void setList(ArrayList<SelectedAreaMeals> updateMales )
    {this.myMeals = updateMales;}

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SelectedAreaMeals meal = myMeals.get(position);
        holder.category.setText(meal.getStrCategory());
        Glide.with(context).load(url_part1+meal.getStrCategory()+url_part2).into(holder.thumbnails);
    }

    @Override
    public int getItemCount() {
        return myMeals.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView category ;
        ImageView thumbnails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_category);
            category = itemView.findViewById(R.id.text_category);
            thumbnails = itemView.findViewById(R.id.imageView_category);

        }


    }


}