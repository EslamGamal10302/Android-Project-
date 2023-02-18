package com.example.project.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.area.selectedArea.model.Meal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDataBase {
    public  static void  addFavouriteToFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Favorites").child(myMeal.getStrMeal()).setValue(myMeal)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "added to firebase", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }

    public static void addPlanToFirebase(Context context, Meal myMeal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Plan").child(myMeal.getDay()).child(myMeal.getStrMeal()).setValue(myMeal)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "added to firebase", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


        }
    }

    public static void getFavouriteFromFirebase(Context context, FirebaseUser user) {

        DatabaseReference rootFav = FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(user.getUid()).child("Favorites");
        rootFav.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren() ){
                    Meal meal = dataSnapshot.getValue(Meal.class);
                //    GeneralRepository repo=  GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(context),context);
                 //   repo.insert(meal);
                 Log.i("finaaaaaaaal",meal.getStrMeal()+""+meal.getIdMeal());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("test",error.getMessage());
            }
        });
    }


}
