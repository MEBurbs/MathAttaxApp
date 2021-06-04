package com.meburbstudios.mathattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
TODO:

Keep a record of user highest score to display.
 */


public class Score extends AppCompatActivity {

    TextView result;
    Button playAgain;
    Button exit;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //identifiers
        result = findViewById(R.id.textViewUScore);
        playAgain = findViewById(R.id.buttonAgain);
        exit = findViewById(R.id.buttonExit);

        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        String userScore = String.valueOf(score);
        result.setText("Your final score is: " + userScore);

        //onClick Listeners

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Score.this, Game.class);
                startActivity(intent);
                //close the activity
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}