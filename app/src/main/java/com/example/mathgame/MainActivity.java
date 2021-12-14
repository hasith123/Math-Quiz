package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mathgame.ScoreandCurrentQuestion.GameQuestions;
import com.example.mathgame.SplashScreen.Register;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //Connecting all the buttons and text views (initializing)
    Button btn_start , btn_answer1, btn_answer2, btn_answer3, btn_answer4, btn_logout;
    TextView txt_time, txt_score, txt_questions, txt_description;
    ProgressBar time_progress;

    //Creating a new variable to count the seconds
    private int remainingSeconds = 30;  //This will tell how many seconds are left in the timer and we can use it to represent in our progress bar

    //Creating an object to access GameQuestion class
    GameQuestions game = new GameQuestions();

    //Creating an object to implement the timer
    CountDownTimer countDownTimer = new CountDownTimer(30000 , 1000) {  //30000ms means 30 seconds & 1000ms means 1sec.
        @Override
        //onTick supposed to run what happens every one second
        public void onTick(long millisUntilFinished) {
            remainingSeconds--;
            //Displaying the timer
            txt_time.setText(Integer.toString(remainingSeconds) + "sec");
            //Setting up the progress bar
            time_progress.setProgress(30 - remainingSeconds);
        }

        @Override
        //After 30 seconds it will run this method
        public void onFinish() {
            //Disabling the buttons after 30 seconds
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            btn_answer4.setEnabled(false);

            txt_description.setText("Your time is up!" + game.getCorrectNumber() + "/" + (game.getTotalQuestions() - 1));

            //Using a handler object to show the start button again to the user
            final Handler handler = new Handler();

            //postDelayed method
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Displaying the start button after end of the game
                    btn_start.setVisibility(View.VISIBLE);
                }
            }, 5000);  //5000 ms means 5 seconds. Start button will appear after 5 seconds once the game is over.


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defining the buttons
        btn_start = findViewById(R.id.btn_start);
        btn_logout = findViewById(R.id.btn_logout);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);
        btn_answer4 = findViewById(R.id.btn_answer4);

        //Defining the text views
        txt_time = findViewById(R.id.txt_time);
        txt_score = findViewById(R.id.txt_score);
        txt_questions = findViewById(R.id.txt_questions);
        txt_description = findViewById(R.id.txt_description);

        //Defining the progress bar
        time_progress = findViewById(R.id.time_progress);

        //Setting initial text for the each text view
        txt_time.setText("0sec");
        txt_questions.setText("");
        txt_score.setText("0 points");
        txt_description.setText("Click Start");
        time_progress.setProgress(0);




        //Setting up the Start button
        View.OnClickListener startButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button button_start = (Button) v;

                //Once the user clicks the start button it goes invisible
                button_start.setVisibility(View.INVISIBLE);

                //To restart the game when the time is over
                remainingSeconds = 30;
                game = new GameQuestions();

                //Creating a function to start the game
                nextQuestion();

                //Setting up the timer after clicks the start button
                countDownTimer.start();



            }
        };

        //Setting up the log out button
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });



        //Setting up the answer buttons
        View.OnClickListener answerButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedAnswer = (Button) v;

                int selectedAnswer = Integer.parseInt(clickedAnswer.getText().toString());

                //Test weather the answer is correct.
                //Toast.makeText(MainActivity.this, "Answer is " + selectedAnswer, Toast.LENGTH_SHORT).show();

                game.checkAnswer(selectedAnswer);

                //Updating the score
                txt_score.setText(Integer.toString(game.getScore()));
                nextQuestion();




            }
        };


        //Creating on click button listeners
        btn_start.setOnClickListener(startButton);

        btn_answer1.setOnClickListener(answerButton);
        btn_answer2.setOnClickListener(answerButton);
        btn_answer3.setOnClickListener(answerButton);
        btn_answer4.setOnClickListener(answerButton);



    }



    //Implement the method to start the game + setting up new questions
    private void nextQuestion() {
        //Creating a new question
        game.newQuestion();
        int [] answer = game.getCurrentQuestion().getArrayAnswer();

        btn_answer1.setText(Integer.toString(answer[0]));
        btn_answer2.setText(Integer.toString(answer[1]));
        btn_answer3.setText(Integer.toString(answer[2]));
        btn_answer4.setText(Integer.toString(answer[3]));

        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);
        btn_answer4.setEnabled(true);


        //Displaying the question
        txt_questions.setText(game.getCurrentQuestion().getDisplayQuestion());

        //Displaying how many correct answers user got out of how many questions
        txt_description.setText(game.getCorrectNumber() + "/" + (game.getTotalQuestions()-1));
    }
}