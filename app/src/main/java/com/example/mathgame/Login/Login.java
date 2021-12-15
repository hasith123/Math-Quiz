package com.example.mathgame.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathgame.MainActivity;
import com.example.mathgame.R;
import com.example.mathgame.RegisterForm.RegisterForm;
import com.example.mathgame.UserProfile;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Login extends AppCompatActivity {

    ImageView imageTop;
    TextView logo, slogan, ip_address;
    Button btnLogin, newUser;
    TextInputLayout txt_login_username, txt_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        logo = findViewById(R.id.logo_name);
        slogan = findViewById(R.id.slogan_name);
        ip_address = findViewById(R.id.ip_address);
        btnLogin = findViewById(R.id.btn_login);
        newUser = findViewById(R.id.btn_newUser);
        txt_login_username = findViewById(R.id.txt_login_username);
        txt_login_password = findViewById(R.id.txt_login_password);


        //Setting up the new user button
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisterForm.class);
                startActivity(intent);


            }
        });


    }


    //Validate username
    public Boolean checkUsername() {
        String value = txt_login_username.getEditText().getText().toString();


        if (value.isEmpty()) {
            txt_login_username.setError("Required field!");
            return false;
        } else {
            txt_login_username.setError(null);
            txt_login_username.setErrorEnabled(false);
            return true;
        }
    }

    //Validate password
    public Boolean checkPassword() {
        String value = txt_login_password.getEditText().getText().toString();

        if (value.isEmpty()) {
            txt_login_password.setError("Required field!");
            return false;
        } else {
            txt_login_password.setError(null);
            txt_login_password.setErrorEnabled(false);
            return true;
        }
    }


    //Login method
    public void userLogin(View view) {
        if (!checkUsername() | !checkPassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String userGivenUsername = txt_login_username.getEditText().getText().toString().trim();
        final String userGivenPassword = txt_login_password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");

        Query validateUser = reference.orderByChild("userName").equalTo(userGivenUsername);


        //Checking if there is like a given username in the database
        validateUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    txt_login_username.setError(null);
                    txt_login_username.setErrorEnabled(false);

                    String passwordFromDatabase = snapshot.child(userGivenUsername).child("password").getValue(String.class);


                    //If the username and password is correct user will be redirected to the quiz.
                    if (passwordFromDatabase.equals(userGivenPassword)) {
                        txt_login_username.setError(null);
                        txt_login_username.setErrorEnabled(false);

                        //Getting all the fields from the firebase
                        String emailFromDataBase = snapshot.child(userGivenUsername).child("email").getValue(String.class);
                        String nameFromDataBase = snapshot.child(userGivenUsername).child("fName").getValue(String.class);
                        String phoneFromDatabase = snapshot.child(userGivenUsername).child("phone").getValue(String.class);
                        String usernameFromDatabase = snapshot.child(userGivenUsername).child("userName").getValue(String.class);

                        IP_Load();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        txt_login_password.setError("Incorrect Password!");
                        txt_login_password.requestFocus();
                    }
                } else {
                    txt_login_username.setError("There is no such a user!");
                    txt_login_username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    //Show the ip address of the player
    private void IP_Load() {
//        ip_address = (TextView) findViewById(R.id.ip_address);
//        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
//        ip_address.setText("Player IP Address is "+ Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress()));



        try{
            StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(threadpolicy);
            URL url = new URL("http://checkip.amazonaws.com/");
            URLConnection  urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            ip_address.setText("IP is "+bufferedReader.readLine());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}