package com.example.diceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidanimations.library.attention.ShakeAnimator;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnRollTheDice);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.dice_sound);

        final ImageView diceImage1 = findViewById(R.id.imgDice1);
        final ImageView diceImage2 = findViewById(R.id.imgDice2);

        final int [] diceImages = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("MyDiceApp", "btn is tapped now !");

                Random rndObject = new Random();

                int myRandomNumber = rndObject.nextInt(6);

                diceImage1.setImageResource(diceImages[myRandomNumber]);

                myRandomNumber = rndObject.nextInt(6);

                diceImage2.setImageResource(diceImages[myRandomNumber]);

                YoYo.with(Techniques.Shake)
                        .duration(500)
                        .repeat(1)
                        .playOn(diceImage1);
                YoYo.with(Techniques.Shake)
                        .duration(500)
                        .repeat(1)
                        .playOn(diceImage2);

                mp.start();

            }
        });




    }
}
