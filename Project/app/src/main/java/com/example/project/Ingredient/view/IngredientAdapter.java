package com.example.project.Ingredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Network.NetworkConnection;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;

import java.util.ArrayList;

public class IngredientAdapter  extends RecyclerView.Adapter <IngredientAdapter.MyViewHolder> {
    Context context ;
    ArrayList<Meal> myMeals;

    private String url_part1="https://www.themealdb.com/images/ingredients/";

    private  String url_part2=".png";

    private IngredientOnClickListner listner ;

    public IngredientAdapter(Context context, IngredientOnClickListner listner) {
        this.context = context;
        this.listner = listner;
        myMeals=new ArrayList<>();
    }

    public void setList(ArrayList<Meal> updateMales )
    {this.myMeals = updateMales;}

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
        Meal meal = myMeals.get(position);
        holder.ingredient.setText(meal.getStrIngredient());
        Glide.with(context).load(url_part1+meal.getStrIngredient()+url_part2).into(holder.thumbnails);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkConnection.getConnectivity(context)){
                    listner.onClick(meal.getStrIngredient());
                }else{
                    Toast.makeText(context, "There is no internet connection " + "\n" +"Please reconnect and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  myMeals.size();
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
