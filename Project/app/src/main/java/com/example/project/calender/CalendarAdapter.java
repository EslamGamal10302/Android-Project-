package com.example.project.calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

public class CalendarAdapter extends RecyclerView.Adapter <CalendarAdapter.MyViewHolder>{
    Calendar[] myItems ;
    Context context;

    public CalendarAdapter(Calendar[] myItems, Context context) {
        this.myItems = myItems;
        this.context = context;
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
        Calendar current = myItems[position];
        holder.name.setText(current.getName());
        holder.thumbnails.setImageResource(current.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return myItems.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      //  ConstraintLayout layout;
        TextView name ;

        ImageView thumbnails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          //  layout = itemView.findViewById(R.id.calendar_constrain);
            name = itemView.findViewById(R.id.tv_random_meals_name);
            thumbnails = itemView.findViewById(R.id.img_random_meal);

        }


    }
}
