package com.example.project.calender.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.calender.Calendar;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter <CalendarAdapter.MyViewHolder>{
   ArrayList<Meal> meals;
    Context context;

CalenderOnClickListner listner;
    TextView day;

    RecyclerView rv;

    public CalendarAdapter(Context context, CalenderOnClickListner listner, TextView day ,RecyclerView rv) {
        this.context = context;
        this.listner = listner;
        this.day = day;
        this.rv = rv;
        meals = new ArrayList<>();
    }

    public void setList(ArrayList<Meal> meals){
        this.meals=meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_view, parent , false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.MyViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.name.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.thumbnails);
        if(meals.size()>0){
            day.setVisibility(View.VISIBLE);
            rv.setVisibility(View.VISIBLE);
        } /*else if (meals.size()==0){
            day.setVisibility(View.GONE);


        }*/

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.OnClick(meal);

                if ((meals.size()-1)==0){
                    day.setVisibility(View.GONE);
                    rv.setVisibility(View.GONE);

                }
              /*  if (meals.size()==0){
                    day.setVisibility(View.GONE);
                    notifyDataSetChanged();
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      //  ConstraintLayout layout;
        TextView name ;

        ImageView thumbnails;

        ImageButton delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          //  layout = itemView.findViewById(R.id.calendar_constrain);
            name = itemView.findViewById(R.id.tv_calender_meal_name);
            thumbnails = itemView.findViewById(R.id.img_calender_meal);
            delete=itemView.findViewById(R.id.btn_removeFromCalender);

        }


    }
}
