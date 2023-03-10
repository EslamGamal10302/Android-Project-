package com.example.project.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Network.FirebaseDataBase;
import com.example.project.Network.NetworkConnection;
import com.example.project.R;
import com.example.project.home.view.HomeActivity;
import com.example.project.signup.SignUp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginScreen extends AppCompatActivity {
    TextView signup;
    Button logIn;

    FirebaseAuth firebaseAuth;

    TextView userName;

    TextView password;

    ProgressDialog dialog;

    TextView recovery;
    Button guest ;

    ImageView google ;
    GoogleSignInClient googleSignInClient;

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
        google = findViewById(R.id.img_google);



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


    dialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);


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


        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(LoginScreen.this,googleSignInOptions);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });


        // msh fahm leh bs mina 2aly hatdrab lw m7tthash ... isa mtdrabsh 7aga
    /*    FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(LoginScreen.this,HomeActivity.class));
            finish();
        }*/



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
                            FirebaseDataBase.getFavouriteFromFirebase(LoginScreen.this,user);
                            FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,user,"1");
                            FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,user,"2");
                            FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,user,"3");
                            FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,user,"4");
                            FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,user,"5");
                            FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,user,"6");
                            FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,user,"7");
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

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(signInAccountTask.isSuccessful()){
                Toast.makeText(this, "Google Sign In Successful", Toast.LENGTH_SHORT).show();
            }
            try {
                GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                if(googleSignInAccount != null){
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                    firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseDataBase.getFavouriteFromFirebase(LoginScreen.this,firebaseAuth.getCurrentUser());
                                FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,firebaseAuth.getCurrentUser(),"1");
                                FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,firebaseAuth.getCurrentUser(),"2");
                                FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,firebaseAuth.getCurrentUser(),"3");
                                FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,firebaseAuth.getCurrentUser(),"4");
                                FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,firebaseAuth.getCurrentUser(),"5");
                                FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,firebaseAuth.getCurrentUser(),"6");
                                FirebaseDataBase.getPlanFromFireBase(LoginScreen.this,firebaseAuth.getCurrentUser(),"7");
                                Toast.makeText(LoginScreen.this, "Authentication successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginScreen.this,HomeActivity.class));
                                finish();
                            }else{
                                Toast.makeText(LoginScreen.this, "Authentication Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
    }




}