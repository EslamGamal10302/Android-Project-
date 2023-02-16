package com.example.project.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.home.view.HomeActivity;
import com.example.project.signup.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {
    TextView signup;
    Button logIn;

    FirebaseAuth firebaseAuth;

    TextView userName;

    TextView password;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        signup = findViewById(R.id.tv_regester);
        logIn = findViewById(R.id.bt_login);
        userName= findViewById(R.id.tb_email);
        password= findViewById(R.id.tb_password);
        firebaseAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userName.getText().toString();
                String userPassword=password.getText().toString();
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    userName.setError("Invalid Email");
                    userName.setFocusable(true);
                } else {
                    loginUser(email,userPassword);
                }


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });


    dialog = new ProgressDialog(this);
    dialog.setMessage("Loggin In....");


    }

    private void loginUser(String email, String userPassword) {
        dialog.show();
        firebaseAuth.signInWithEmailAndPassword(email , userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                                   dialog.dismiss();
                                   FirebaseUser user = firebaseAuth.getCurrentUser();
                                   startActivity(new Intent(LoginScreen.this,HomeActivity.class));
                                   finish();
                        }else {
                                  dialog.dismiss();
                            Toast.makeText(LoginScreen.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(LoginScreen.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }




}