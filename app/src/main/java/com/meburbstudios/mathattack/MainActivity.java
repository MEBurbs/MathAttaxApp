package com.meburbstudios.mathattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
TODO:
Create Subtraction Game Class
Create Multiplication Game Class
 */
public class MainActivity extends AppCompatActivity {


    Button addition;
    Button subtraction;
    Button multiplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //identifiers
        addition = findViewById(R.id.buttonAdd);
        subtraction = findViewById(R.id.buttonSub);
        multiplication = findViewById(R.id.buttonMult);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);

            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameSubtraction.class);
                startActivity(intent);

            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameMultiply.class);
                startActivity(intent);

            }
        });


    }
}