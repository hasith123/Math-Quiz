package com.example.mathgame.RegisterForm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathgame.DBHelperClass.UserHelperClass;
import com.example.mathgame.API.GoogleSignInAPI;
import com.example.mathgame.Login.Login;
import com.example.mathgame.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterForm extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    Layout linear;

    Animation iconAnim , detailsAnim;

    ImageView iv_top_icon;

    TextView txt_welcome, txt_startGame;

    TextInputLayout  txt_fname, txt_uname, txt_email, txt_contact, txt_password;

    Button btn_register , btn_LoginAccount , btn_googleSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        btn_register = findViewById(R.id.btn_register);
        btn_LoginAccount = findViewById(R.id.btn_LoginAccount);
        btn_googleSign = findViewById(R.id.btn_googleSign);

        txt_welcome = findViewById(R.id.txt_welcome);
        txt_startGame = findViewById(R.id.txt_startGame);


        txt_fname = findViewById(R.id.txt_fname);
        txt_uname = findViewById(R.id.txt_uname);
        txt_email = findViewById(R.id.txt_email);
        txt_contact = findViewById(R.id.txt_contact);
        txt_password = findViewById(R.id.txt_password);

        iv_top_icon = findViewById(R.id.iv_verification);



        iconAnim = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        detailsAnim = AnimationUtils.loadAnimation(this,R.anim.logo_button_animation);


        iv_top_icon.setAnimation(iconAnim);
        txt_welcome.setAnimation(detailsAnim);
        txt_startGame.setAnimation(detailsAnim);
        txt_fname.setAnimation(detailsAnim);
        txt_uname.setAnimation(detailsAnim);
        txt_email.setAnimation(detailsAnim);
        txt_contact.setAnimation(detailsAnim);
        txt_password.setAnimation(detailsAnim);


        //Login button
        btn_LoginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Login.class);
                startActivity(intent);
            }
        });


        //Google sign in button
        btn_googleSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , GoogleSignInAPI.class);
                startActivity(intent);
            }
        });

    }



    //Validations.
    public Boolean checkName() {
        String value = txt_fname.getEditText().getText().toString();

        if (value.isEmpty()) {
            txt_fname.setError("Required field!");
            return false;
        } else {
            txt_fname.setError(null);
            txt_fname.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean checkUsername() {
        String value = txt_uname.getEditText().getText().toString();

        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (value.isEmpty()) {
            txt_uname.setError("Required field!");
            return false;
        } else if (value.length() >= 15) {
            txt_uname.setError("Username is too long!");
            return false;
        } else if (!value.matches(noWhiteSpace)) {
            txt_uname.setError("Spaces can not be allowed!");
            return false;
        }
            else {
            txt_uname.setError(null);
            txt_uname.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean checkEmail() {
        String value = txt_email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (value.isEmpty()) {
            txt_email.setError("Required field!");
            return false;
        } else if (value.matches(emailPattern)) {
            txt_email.setError("Invalid email!");
            return false;
        }
        else {
            txt_email.setError(null);
            txt_email.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean checkContact() {
        String value = txt_contact.getEditText().getText().toString();

        if (value.isEmpty()) {
            txt_contact.setError("Required field!");
            return false;
        } else {
            txt_contact.setError(null);
            txt_contact.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean checkPassword() {
        String value = txt_password.getEditText().getText().toString();
        //String passwordValue = "^" +
          //      "(?=.*[a-zA-Z])" +
            //    "(?=.*[@#$%^&+-])" +
              //  "(?=\\s+$)" +
                //".{4,}" +
                //"$";

        if (value.isEmpty()) {
            txt_password.setError("Required field!");
            return false;
        } //else if (!value.matches(passwordValue)) {
            //txt_password.setError("password is weak");
            //return false;
        //}
        else {
            txt_password.setError(null);
            txt_password.setErrorEnabled(false);
            return true;
        }
    }



    //Method to insert user data to the firebase database once the user clicks the register button
    public void registerUser(View view) {

        if (!checkName() | !checkUsername() | !checkEmail() | !checkContact() | !checkPassword()) {
            return;
        }

            //Get inside the database instance
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("user");

            //Getting all the fields
            String fName = txt_fname.getEditText().getText().toString();
            String userName = txt_uname.getEditText().getText().toString();
            String email = txt_email.getEditText().getText().toString();
            String phone = txt_contact.getEditText().getText().toString();
            String password = txt_password.getEditText().getText().toString();


            //Creating a new object
            UserHelperClass helperClass = new UserHelperClass(fName, userName, email, phone, password);

            reference.child(userName).setValue(helperClass);

            Intent intent = new Intent(RegisterForm.this,Login.class);
            startActivity(intent);


    }



}