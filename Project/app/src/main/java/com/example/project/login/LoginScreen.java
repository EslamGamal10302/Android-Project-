package com.example.project.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Patterns;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Network.NetworkConnection;
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


    TextView recovery;





    Button guest ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        signup = findViewById(R.id.tv_regester);
        logIn = findViewById(R.id.bt_login);
        userName= findViewById(R.id.tb_email);
        password= findViewById(R.id.tb_password);
        guest = findViewById(R.id.btn_guest);
        recovery = findViewById(R.id.tv_recovery);
        firebaseAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(NetworkConnection.getConnectivity(LoginScreen.this)){
                String email = userName.getText().toString();
                String userPassword=password.getText().toString();
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    userName.setError("Invalid Email");
                    userName.setFocusable(true);
                } else {
                    loginUser(email,userPassword);
                }
            } else {
                Toast.makeText(LoginScreen.this, "There is no internet connection " + "\n" +"Please reconnect and try again", Toast.LENGTH_SHORT).show();
            }




            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });


    dialog = new ProgressDialog(this);


    guest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                 alertDialog();
        }
    });

    recovery.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showRecoveryPasswordDialog();
        }
    });


    }

    private void showRecoveryPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recover your password");
        LinearLayout layout = new LinearLayout(this);
        EditText email = new EditText(this);
        email.setHint("Please Enter Your Email ");
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setMinEms(16);
        layout.addView(email);
        layout.setPadding(10,10,10,10);
        builder.setView(layout);
        String yes = "RECOVER";
        String no =  "CANCEL";
        builder.setPositiveButton(Html.fromHtml("<font color='#FFBF00'>"+yes+"</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {
                   String recoverEmail = email.getText().toString().trim();
                  beginRecovery(recoverEmail);

        });
        builder.setNegativeButton(Html.fromHtml("<font color='#FFBF00'>"+no+"</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {

            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }

    private void beginRecovery(String recoverEmail) {
        dialog.setMessage("Sending mail....");
        dialog.show();
                firebaseAuth.sendPasswordResetEmail(recoverEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                          if(task.isSuccessful()){
                              Toast.makeText(LoginScreen.this, "Email sent", Toast.LENGTH_SHORT).show();
                          } else {
                              Toast.makeText(LoginScreen.this, "Failed...", Toast.LENGTH_SHORT).show();
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

    private void loginUser(String email, String userPassword) {
        dialog.setMessage("Loggin In....");
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
                            Toast.makeText(LoginScreen.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
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

    private void alertDialog(){

        String yes = "YES,I'M SURE";
        String no =  "NO,GO BACK";
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogTheme));
        builder.setMessage("You will lose some of our awsome features like saving your favourite meals ");
        builder.setTitle("Wait ! Are You Sure You Want To Scroll As Guest ?");
        builder.setCancelable(false);
        builder.setPositiveButton(Html.fromHtml("<font color='#FFBF00'>"+yes+"</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {
            startActivity(new Intent(LoginScreen.this,HomeActivity.class));
            Toast.makeText(this, "Login as a guest was successfully", Toast.LENGTH_SHORT).show();


        });
        builder.setNegativeButton(Html.fromHtml("<font color='#FFBF00'>"+no+"</font>"), (DialogInterface.OnClickListener) (dialog, which) -> {

            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }




}