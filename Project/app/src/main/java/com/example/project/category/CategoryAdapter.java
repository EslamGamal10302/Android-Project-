package com.example.project.category;

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
import com.example.project.Ingredient.IngredientAdapter;
import com.example.project.Ingredient.meals;
import com.example.project.R;

public class CategoryAdapter extends RecyclerView.Adapter <CategoryAdapter.MyViewHolder>{
    Context context ;
    Category[] myCategory;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_view , parent , false);
        CategoryAdapter.MyViewHolder vh = new CategoryAdapter.MyViewHolder(view);
        return vh;
    }

    public CategoryAdapter(Context context, Category[] myCategory) {
        this.context = context;
        this.myCategory = myCategory;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = myCategory[position];
        holder.category.setText(category.getStrCategory());
        Glide.with(context).load(category.getImage()).into(holder.thumbnails);
    }

    @Override
    public int getItemCount() {
        return myCategory.length;
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
