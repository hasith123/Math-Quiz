package com.example.mathgame.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathgame.Login.Login;
import com.example.mathgame.R;

public class Register extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;


    Animation imageAnim, logoAnim;

    ImageView imageView;

    TextView txtTitle , txtLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This will hide the notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);



        imageAnim = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        logoAnim = AnimationUtils.loadAnimation(this,R.anim.logo_button_animation);

        imageView = findViewById(R.id.iv_icon);

        txtTitle = findViewById(R.id.txt_phoneTitle);

        txtLogo = findViewById(R.id.txt_logo);

        //Setting up the animations
        imageView.setAnimation(imageAnim);
        txtTitle.setAnimation(logoAnim);
        txtLogo.setAnimation(logoAnim);


        //This will handle the delay process
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }



}