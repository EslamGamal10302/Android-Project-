package com.example.project.details.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.project.DataBase.DataBaseRepository;
import com.example.project.GeneralRepositoryModel.GeneralRepository;
import com.example.project.Network.FirebaseDataBase;
import com.example.project.Network.MealClient;
import com.example.project.R;
import com.example.project.area.selectedArea.model.Meal;
import com.example.project.details.presenter.MealDetailPresenter;
import com.example.project.details.presenter.MealDetailPresenterInterface;
import com.example.project.home.view.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MealDetails extends AppCompatActivity implements MealDetailViewInterface {
    ImageView mealImage;

    ImageView calendar;
    TextView mealName;
    TextView mealContry;


    TextView mealCategory;

    TextView ingradiant1;
    TextView ingradiant2;
    TextView ingradiant3;
    TextView ingradiant4;
    TextView ingradiant5;
    TextView ingradiant6;
    TextView ingradiant7;
    TextView steps;
    YouTubePlayerView youTubePlayerView ;

    Meal response ;
    String VideoUrl;

    RecyclerView RV;
    LinearLayoutManager manger;
    MealDetailAdapter adapter;

    ArrayList<MealIngredients> resultToShow ;

    AutoCompleteTextView dropList;

    ToggleButton addToFavourite ;

    private  int stepNo = 0;

    String [] days ={"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};


    MealDetailPresenterInterface presenter;

    private String MealName ;

    private  Meal selectedSearchMeal;

    FirebaseAuth firebaseAuth;

    FirebaseUser user ;

    ProgressDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        dialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        dialog.setMessage("Loading....");
        dialog.show();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mealImage = findViewById(R.id.img_details_meal);
        calendar = findViewById(R.id.calendar);
        mealName = findViewById(R.id.mealname);
        mealContry =findViewById(R.id.mealContry);
        mealCategory=findViewById(R.id.meal_category);
        addToFavourite=findViewById(R.id.btn_add_mealDetail_toFav);
        dropList=findViewById(R.id.days_drop_dawn);
        steps=findViewById(R.id.steps);
        youTubePlayerView =findViewById(R.id.ybv);
        RV=findViewById(R.id.recyclerView2);
        manger = new LinearLayoutManager(this);
        manger.setOrientation(RecyclerView.HORIZONTAL);
        RV.setLayoutManager(manger);
        adapter = new MealDetailAdapter(this);
        RV.setAdapter(adapter);
        presenter = new MealDetailPresenter(GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(this),this),this);
        Intent intent = getIntent();
        MealName = intent.getStringExtra("name");
        presenter.getSelectedMealDetails(MealName);










        addToFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Boolean clicked = false ;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(user!= null){
                  if (!clicked){
                      // holder.addToFavourite.setChecked(false);
                      clicked = true;
                      addToFavourite.setBackgroundResource(R.drawable.baseline_favorite_24);
                      Toast.makeText(MealDetails.this, "meal added to your favourite list", Toast.LENGTH_SHORT).show();
                      selectedSearchMeal.setDay("0");
                      //  listner.onAddToFavorite(response);
                      presenter.addToFavorite(selectedSearchMeal);
                      FirebaseDataBase.addFavouriteToFirebase(MealDetails.this,selectedSearchMeal);

                  }
              } else {
                  Toast.makeText(MealDetails.this, "You need to login to be able to save meals to your favourit list", Toast.LENGTH_SHORT).show();
              }

            }
        });

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MealDetails.this, android.R.layout.simple_list_item_1,days);
        dropList.setAdapter(adapter);
        dropList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    dropList.showDropDown();
                } else {
                    Toast.makeText(MealDetails.this, "You need to login to be able to save meals to your week calendar plan", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dropList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = parent.getItemAtPosition(position).toString();
                switch (day) {
                    case "Saturday":
                        selectedSearchMeal.setDay("1");
                        presenter.addToFavorite(selectedSearchMeal);
                        FirebaseDataBase.addPlanToFirebase(MealDetails.this,selectedSearchMeal);
                        Toast.makeText(MealDetails.this, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Sunday":
                        selectedSearchMeal.setDay("2");
                        presenter.addToFavorite(selectedSearchMeal);
                        FirebaseDataBase.addPlanToFirebase(MealDetails.this,selectedSearchMeal);
                        Toast.makeText(MealDetails.this, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Monday":
                        selectedSearchMeal.setDay("3");
                        presenter.addToFavorite(selectedSearchMeal);
                        FirebaseDataBase.addPlanToFirebase(MealDetails.this,selectedSearchMeal);
                        Toast.makeText(MealDetails.this, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Tuesday":
                        selectedSearchMeal.setDay("4");
                        presenter.addToFavorite(selectedSearchMeal);
                        FirebaseDataBase.addPlanToFirebase(MealDetails.this,selectedSearchMeal);
                        Toast.makeText(MealDetails.this, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;

                    case "Wednesday":
                        selectedSearchMeal.setDay("5");
                        presenter.addToFavorite(selectedSearchMeal);
                        FirebaseDataBase.addPlanToFirebase(MealDetails.this,selectedSearchMeal);
                        Toast.makeText(MealDetails.this, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;

                    case "Thursday":
                        selectedSearchMeal.setDay("6");
                        presenter.addToFavorite(selectedSearchMeal);
                        FirebaseDataBase.addPlanToFirebase(MealDetails.this,selectedSearchMeal);
                        Toast.makeText(MealDetails.this, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;
                    case "Friday":
                        selectedSearchMeal.setDay("7");
                        presenter.addToFavorite(selectedSearchMeal);
                        FirebaseDataBase.addPlanToFirebase(MealDetails.this,selectedSearchMeal);
                        Toast.makeText(MealDetails.this, "Meal added to "+day, Toast.LENGTH_SHORT).show();
                        break;


                }
            }
        });



    calendar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(user!=null){
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE,selectedSearchMeal.getStrMeal());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,selectedSearchMeal.getStrArea());
                intent.putExtra(CalendarContract.Events.DESCRIPTION,selectedSearchMeal.getStrInstructions());
                intent.putExtra(CalendarContract.Events.ALL_DAY,true);
                startActivity(intent);
            } else {
                Toast.makeText(MealDetails.this, "You need to login to be able to save meals to your Google calendar plan", Toast.LENGTH_SHORT).show();
            }

        }
    });


    }

    public  void setScreenData(Meal meal){

      // response = HomeActivity.detail;
        Glide.with(this).load(meal.getStrMealThumb()).into(mealImage);
        resultToShow = prepareIngredient(meal);
        adapter.setList(resultToShow);
        adapter.notifyDataSetChanged();
        mealName.setText(meal.getStrMeal());
        mealContry.setText(meal.getStrArea());
        mealCategory.setText(meal.getStrCategory());
        StringTokenizer st = new StringTokenizer(meal.getStrInstructions(), ".");
        while (st.hasMoreTokens()){
            steps.append("Step "+String.valueOf(stepNo+1)+"\n"+st.nextToken()+"\n");
            stepNo++;

        }
       // steps.setText(response.getStrInstructions());
       VideoUrl = meal.getStrYoutube();
      // VideoUrl = "https://www.youtube.com/watch?v=omnQWLBe6tg";







        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                if(VideoUrl !=null && !VideoUrl.isEmpty() && VideoUrl.equalsIgnoreCase("")){
                    VideoUrl = VideoUrl.substring(VideoUrl.indexOf("=") + 1);
                    StringTokenizer st = new StringTokenizer(VideoUrl, "&");
                    VideoUrl = st.nextToken();
                    youTubePlayer.loadVideo(VideoUrl, 0);
                    youTubePlayer.pause();
                }
            }
        });

        dialog.dismiss();

    }



    public ArrayList<MealIngredients> prepareIngredient (Meal meal){
        ArrayList<MealIngredients> ingredientsList = new ArrayList<>();
        if (meal.getStrIngredient1()!=null&&  !meal.getStrIngredient1().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient1(),meal.getStrMeasure1()));
        if (meal.getStrIngredient2()!=null &&  !meal.getStrIngredient2().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient2(), meal.getStrMeasure2()));
        if (meal.getStrIngredient3()!=null &&  !meal.getStrIngredient3().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient3(), meal.getStrMeasure3()));
        if (meal.getStrIngredient4()!=null &&  !meal.getStrIngredient4().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient4(), meal.getStrMeasure4()));
        if (meal.getStrIngredient5()!=null &&  !meal.getStrIngredient5().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient5(), meal.getStrMeasure5()));
        if (meal.getStrIngredient6()!=null &&  !meal.getStrIngredient6().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient6(), meal.getStrMeasure6()));
        if (meal.getStrIngredient7()!=null &&  !meal.getStrIngredient7().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient7(), meal.getStrMeasure7()));
        if (meal.getStrIngredient8()!=null &&  !meal.getStrIngredient8().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient8(), meal.getStrMeasure8()));
        if (meal.getStrIngredient9()!=null &&  !meal.getStrIngredient9().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient9(), meal.getStrMeasure9()));
        if (meal.getStrIngredient10()!=null &&  !meal.getStrIngredient10().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient10(), meal.getStrMeasure10()));
        if (meal.getStrIngredient11()!=null &&  !meal.getStrIngredient11().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient11(), meal.getStrMeasure11()));
        if (meal.getStrIngredient12()!=null &&  !meal.getStrIngredient12().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient12(), meal.getStrMeasure12()));
        if (meal.getStrIngredient13()!=null &&  !meal.getStrIngredient13().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient13(), meal.getStrMeasure13()));
        if (meal.getStrIngredient14()!=null &&  !meal.getStrIngredient14().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient14(), meal.getStrMeasure14()));
        if (meal.getStrIngredient15()!=null &&  !meal.getStrIngredient15().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient15(), meal.getStrMeasure15()));
        if (meal.getStrIngredient16()!=null &&  !meal.getStrIngredient16().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient16(), meal.getStrMeasure16()));
        if (meal.getStrIngredient17()!=null &&  !meal.getStrIngredient17().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient17(), meal.getStrMeasure17()));
        if (meal.getStrIngredient18()!=null &&  !meal.getStrIngredient18().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient18(), meal.getStrMeasure18()));
        if (meal.getStrIngredient19()!=null &&  !meal.getStrIngredient19().isEmpty())
            ingredientsList.add(new MealIngredients(meal.getStrIngredient19(), meal.getStrMeasure19()));
        if (meal.getStrIngredient20()!=null && !meal.getStrIngredient20().isEmpty()){
            ingredientsList.add(new MealIngredients(meal.getStrIngredient20(), meal.getStrMeasure20()));
            Log.i("eslam","if here ");
            Log.i("eslam",meal.getStrIngredient20());
        }

     /*  MealIngredients one  = new MealIngredients("Lime" , "meald");
        MealIngredients two  = new MealIngredients("Lime" , "meald");
        MealIngredients three  = new MealIngredients("Lime" , "meald");
        MealIngredients four  = new MealIngredients("Lime" , "meald");
        ingredientsList.add(one);
        ingredientsList.add(two);
        ingredientsList.add(three);
        ingredientsList.add(four);*/



        return  ingredientsList;
    }

    @Override
    public void showSelectedMealDetails(ArrayList<Meal> allMeals) {

              for(Meal search : allMeals){
                  if(search.getStrMeal().equalsIgnoreCase(MealName)){
                      selectedSearchMeal =search;
                  }
              }
              setScreenData(selectedSearchMeal);


    }
}