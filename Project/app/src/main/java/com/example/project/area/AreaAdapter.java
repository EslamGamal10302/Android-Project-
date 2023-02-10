package com.example.project.area;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

public class AreaAdapter extends RecyclerView.Adapter <AreaAdapter.MyViewHolder> {
    Context context ;
    Area[] areas;


    AreaOnClickListner listner;

    public AreaAdapter(Context context, Area[] areas ,AreaOnClickListner listner) {
        this.context = context;
        this.areas = areas;
        this.listner=listner;
    }

    @NonNull
    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.area_view , parent , false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.MyViewHolder holder, int position) {
        Area area = areas[position];
        holder.nationality.setText(area.getNationality());
        holder.thumbnails.setImageResource(area.getImage_thumbnails());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClick(area.getNationality());
            }
        });
    }

    @Override
    public int getItemCount() {
        return areas.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView nationality ;
        ImageView thumbnails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay);
            nationality = itemView.findViewById(R.id.text_country);
            thumbnails = itemView.findViewById(R.id.imageView);

        }


    }


}
