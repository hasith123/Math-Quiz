package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class PhoneVerification extends AppCompatActivity {

    Button btn_verify;
    EditText txt_userVerificationCode;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        btn_verify = findViewById(R.id.btn_continue);
        txt_userVerificationCode = findViewById(R.id.txt_enterPhoneNumber);
        progressBar = findViewById(R.id.progress_bar);

        //Method to send the verification code
        String phone = getIntent().getStringExtra("phone");


    }



}