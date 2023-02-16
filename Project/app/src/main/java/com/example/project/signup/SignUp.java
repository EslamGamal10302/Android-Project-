package com.example.project.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.login.LoginScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    Button btn_signup ;
    EditText email ;
    EditText password ;
    EditText confPassword ;
    ProgressDialog pd ;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_signup = findViewById(R.id.btn_signup);
        email =findViewById(R.id.tb_email);
        password = findViewById(R.id.tb_password);
        confPassword = findViewById(R.id.tb_confirm_password);
        pd = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        pd.setMessage("Registering User ...");

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();
                String userPass = password.getText().toString().trim();
                String userConfPasss = confPassword.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    email.setError("Invalid Email");
                    email.setFocusable(true);
                } else if (userPass.length()<8) {
                    password.setError("Invalid Password");
                    password.setFocusable(true);
                }else if (!userPass.equals(userConfPasss)){
                    confPassword.setError("Password Not Match");
                    confPassword.setFocusable(true);
                }else {
                    // calling method
                    RegsisterUser(userEmail,userPass);
                }

            }
        });

    }

    private void RegsisterUser(String userEmail, String userPass) {
        pd.show();
        auth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    pd.dismiss();
                    FirebaseUser user = auth.getCurrentUser();
                    Toast.makeText(SignUp.this, "Register Successful ", Toast.LENGTH_SHORT).show();
                    Log.i("milad",""+user.getEmail());
                    startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                    finish();
                }else{
                    pd.dismiss();
                    Toast.makeText(SignUp.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(SignUp.this, " "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}