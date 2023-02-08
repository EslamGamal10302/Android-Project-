package com.example.project.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.home.FavActivity;
import com.example.project.home.HomeActivity;
import com.example.project.login.LoginScreen;
import com.example.project.login.SplashScreen;


public class ThirdFragment extends Fragment {

   Button three;

   Activity context;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        three= context.findViewById(R.id.button3);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Navigation.findNavController(v).navigate(R.id.action_thirdFragment_to_firstFragment);
                Intent intent = new Intent(context.getApplicationContext(), SplashScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
}