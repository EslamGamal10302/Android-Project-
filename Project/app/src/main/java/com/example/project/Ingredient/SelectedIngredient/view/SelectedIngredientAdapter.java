package com.example.project.Ingredient.SelectedIngredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Network.NetworkConnection;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SelectedIngredientAdapter extends RecyclerView.Adapter<SelectedIngredientAdapter.MyViewHolder> {
    ArrayList<Meal> myMeals;
    Context context ;

    SelectedIngrediantOnClick  listner ;

    FirebaseAuth firebaseAuth;

    FirebaseUser user ;

    public SelectedIngredientAdapter(Context context , SelectedIngrediantOnClick  listner ) {
        this.context = context;
        myMeals = new ArrayList<>();
        this.listner = listner;

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
    }

    public void setList(ArrayList<Meal> updateMales )
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
        String [] days ={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
        Meal meal = myMeals.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.thumbnails);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,days);
        holder.autoCompleteTextView.setAdapter(adapter);
        holder.autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                holder.autoCompleteTextView.showDropDown();
                } else  {
                    Toast.makeText(context, "You need to login to be able to save meals to your week calendar plan", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = parent.getItemAtPosition(position).toString();
                switch (day) {
                    case "Saturday":
                        meal.setDay("1");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Sunday":
                        meal.setDay("2");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Monday":
                        meal.setDay("3");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Tuesday":
                        meal.setDay("4");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;

                    case "Wednesday":
                        meal.setDay("5");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;

                    case "Thursday":
                        meal.setDay("6");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Friday":
                        meal.setDay("7");
                        listner.onAddToFavorite(meal);
                        Toast.makeText(context, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;


                }
                //  Toast.makeText(context, "Meal added to "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            }

        });
        holder.addToFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Boolean clicked = false ;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(user != null){
                if (!clicked){
                    // holder.addToFavourite.setChecked(false);
                    clicked = true;
                    holder.addToFav.setBackgroundResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(context, "meal added to your favourite list", Toast.LENGTH_SHORT).show();
                    meal.setDay("0");
                    listner.onAddToFavorite(meal);

                }
                } else {
                    Toast.makeText(context, "You need to login to be able to save meals to your favourit list ", Toast.LENGTH_SHORT).show();
                }/*else{
                   holder.addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                    Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                } */
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkConnection.getConnectivity(context)) {
                    listner.ShowMealDetails(meal);
                }else{
                    Toast.makeText(context, "There is no internet connection " + "\n" +"Please reconnect and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myMeals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView title ;

        ImageView thumbnails;

        ToggleButton addToFav ;

        AutoCompleteTextView autoCompleteTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cons_lay_Selected_ingredient);
            title = itemView.findViewById(R.id.text_selected_ingredient);
            thumbnails = itemView.findViewById(R.id.imageView_selected_ingredient);
            addToFav = itemView.findViewById(R.id.btn_addToFavorite);
            autoCompleteTextView =itemView.findViewById(R.id.days_drop_dawn);


        }


    }
}
