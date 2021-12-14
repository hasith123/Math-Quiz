package com.example.mathgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class UserProfile extends AppCompatActivity {

    private TextView txt_fullName , txt_username , txt_userEmail , txt_userContact , txt_userPassword;
    private TextView full_name , txt_titleUsername;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USER = "user";
    private String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        txt_fullName = findViewById(R.id.txt_fullName);
        txt_username = findViewById(R.id.txt_username);
        txt_userEmail = findViewById(R.id.txt_userEmail);
        txt_userContact = findViewById(R.id.txt_userContact);
        txt_userPassword = findViewById(R.id.txt_userPassword);
        full_name = findViewById(R.id.full_name);
        txt_titleUsername = findViewById(R.id.txt_titleUsername);


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USER);

        Log.v("EMAILADD", userRef.orderByChild("email").equalTo(email).toString());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(email)) {
                        txt_fullName.setText(ds.child("fName").getValue(String.class));
                        txt_username.setText(ds.child("userName").getValue(String.class));
                        txt_userEmail.setText(email);
                        txt_userContact.setText(ds.child("phone").getValue(String.class));
                        txt_userPassword.setText(ds.child("password").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}