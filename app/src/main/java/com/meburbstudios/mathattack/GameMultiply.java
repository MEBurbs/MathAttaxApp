package com.meburbstudios.mathattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;


/*
TODO:
*Fix Bug where user can click next question before answering.
 */
public class GameMultiply extends AppCompatActivity {


    TextView score;
    TextView playerLife;
    TextView countdown;

    TextView question;
    EditText answer;

    Button okay;
    Button next;

    //random number generators and ints to hold random number creations
    Random random = new Random();
    int numberOne;
    int numberTwo;

    //user answer int checker
    int uAnswer;
    int correctAnswer;
    int uScore = 0;
    int uLife = 3;

    //Timer variables
    CountDownTimer timer;
    private static final long START_TIMER_MILIS = 60000;
    Boolean timerIsRunning;
    long time_left_milis = START_TIMER_MILIS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //identifiers
        score = findViewById(R.id.textViewUScore);
        playerLife = findViewById(R.id.textViewULife);
        countdown = findViewById(R.id.textViewUTime);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);
        okay = findViewById(R.id.buttonOK);
        next = findViewById(R.id.buttonNext);



        continueGame();

        //button click listeners
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check users answer;
                uAnswer = Integer.valueOf(answer.getText().toString());

                stopTimer();

                if(uAnswer == correctAnswer){

                    uScore += 10;
                    score.setText(""+uScore);
                    question.setText("Congratulations, " + uAnswer + " was correct!");
                }
                else {
                    uLife = uLife -1;
                    playerLife.setText(""+uLife);
                    question.setText("Sorry, " + uAnswer + " is incorrect");

                }

                next.setVisibility(View.VISIBLE);
                okay.setVisibility(View.INVISIBLE);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                answer.setText("");

                resetTimer();

                if(uLife == 0){
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(GameMultiply.this, Score.class);
                    intent.putExtra("score", uScore);
                    startActivity(intent);
                    finish();


                }
                else {

                    continueGame();

                }

            }
        });

    }

    public void continueGame(){

        okay.setVisibility(View.VISIBLE);
        next.setVisibility(View.INVISIBLE);

        //create the random number from 0 to 12
        numberOne = random.nextInt(12);
        numberTwo = random.nextInt(12);

        correctAnswer = numberOne*numberTwo;

        question.setText(numberOne + " * " + numberTwo);

        startTimer();

    }

    public void startTimer(){
        timer = new CountDownTimer(time_left_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_milis = millisUntilFinished;
                updateTimeText();
            }

            @Override
            public void onFinish() {
                //When timer finishes, a new question starts and a life is lost.

                timerIsRunning = false;
                stopTimer();
                resetTimer();
                updateTimeText();
                uLife = uLife - 1;
                playerLife.setText(""+uLife);
                question.setText("You ran out of time!");

            }
        }.start();

        timerIsRunning = true;
    }

    public void updateTimeText(){

        //using second to display the time. transforming time_left form long to int
        int second = (int)(time_left_milis/1000) % 60;
        //showing time in the digit format
        String timeLeft = String.format(Locale.getDefault(), "%02d", second);
        //now the timer textView will be seen
        countdown.setText(timeLeft);

    }

    public void stopTimer(){

        timer.cancel();
        timerIsRunning = false;

    }

    public void resetTimer(){
        //reset the timer using the constant
        time_left_milis = START_TIMER_MILIS;
        updateTimeText();

    }
}