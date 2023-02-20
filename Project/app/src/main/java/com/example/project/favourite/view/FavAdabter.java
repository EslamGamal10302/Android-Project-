package com.example.project.favourite.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Network.FirebaseDataBase;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.home.view.HomeActivity;
import com.example.project.login.LoginScreen;

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
               String yes = "YES,I'M SURE";
               String no =  "NO,GO BACK";
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setMessage("You will lose it from your favourite  meals list");
               builder.setTitle("Wait ! Are You Sure You Want To Delete This Meal ?");
               builder.setCancelable(false);
               builder.setPositiveButton(Html.fromHtml("<font color='#FFBF00'>"+yes+"</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {

                   listner.onClick(myMeal);
                   FirebaseDataBase.removeFavouriteFromFirebase(context,myMeal);
                   Toast.makeText(context, "Removed from your favorite list", Toast.LENGTH_SHORT).show();

               });
               builder.setNegativeButton(Html.fromHtml("<font color='#FFBF00'>"+no+"</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {

                   dialog.cancel();
               });

               AlertDialog alertDialog = builder.create();

               alertDialog.show();

           }
       });

       holder.layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listner.showMealDetails(myMeal);
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

        ConstraintLayout layout ;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.tv_calender_meal_name);
            mealImage = itemView.findViewById(R.id.img_calender_meal);
           removeFromFavourite = itemView.findViewById(R.id.btn_removeFromCalender);
            layout =itemView.findViewById(R.id.cons_favMeals_layout);


        }
    }



}
