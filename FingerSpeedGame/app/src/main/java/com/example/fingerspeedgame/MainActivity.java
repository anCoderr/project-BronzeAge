package com.example.fingerspeedgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //////////////////////////////////////////////////////////////// Method and Variables ////////////////////////////////////////////////////////////////

    // Member variables and view declaration

    private TextView timerTextView;
    private TextView tapCountdownTextView;
    private CountDownTimer countDownTimer;
    private long initialCountdownInMillis = 60000;
    private int timeIntervalInMillis = 1000;
    private int remainingTime = 60;
    private int tapsLeft = 1000;

    private final String REMAINING_TIME_KEY = "remaining time key";
    private final String TAPS_LEFT_KEY = "taps left key";


    // Show alert method

    private void showAlert(String title) {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
            .setTitle(title)
            .setMessage("Would you like to reset the game?")
            .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    resetTheGame();
                 }
            })
            .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    android.os.Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            })    .show();
        alertDialog.setCancelable(false);
    }

    // Reset game method when savedInstanceState is not null

    @SuppressLint("SetTextI18n")
    private void resetTheGame(int timeRemaining, int tapsRemaining){

        initialCountdownInMillis = timeRemaining*1000;
        timeIntervalInMillis = 1000;
        remainingTime = timeRemaining;
        tapsLeft = tapsRemaining;
        timerTextView.setText(remainingTime + '\n' + getString(R.string.initial_time_left));
        tapCountdownTextView.setText(tapsLeft + '\n' + getString(R.string.taps_left));

        if(countDownTimer != null){

            countDownTimer.cancel();
            countDownTimer = null;

        }

        countDownTimer = new CountDownTimer(initialCountdownInMillis, timeIntervalInMillis) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {

                remainingTime = (int) millisUntilFinished / 1000;
                timerTextView.setText(remainingTime + "\n" + getString(R.string.seconds_left));

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                tapCountdownTextView.setText(tapsLeft  + "\n" + getString(R.string.taps_left));
                timerTextView.setText(0 + "\n" + getString(R.string.seconds_left));
                showAlert("Time's Up Sucker");
            }
        };
        countDownTimer.start();
    }

    // Reset the game when the savedInstanceState is null

    private void resetTheGame(){

        initialCountdownInMillis = 60000;
        timeIntervalInMillis = 1000;
        remainingTime = 60;
        tapsLeft = 1000;
        timerTextView.setText(getString(R.string.initial_time_left));
        tapCountdownTextView.setText(getString(R.string.initial_taps_left));

        if(countDownTimer != null){

            countDownTimer.cancel();
            countDownTimer = null;

        }

        countDownTimer = new CountDownTimer(initialCountdownInMillis, timeIntervalInMillis) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                remainingTime = (int) millisUntilFinished / 1000;
                timerTextView.setText(remainingTime + "\n" + getString(R.string.seconds_left));

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                tapCountdownTextView.setText(tapsLeft  + "\n" + getString(R.string.taps_left));
                timerTextView.setText(0 + "\n" + getString(R.string.seconds_left));
                showAlert("Time's Up Sucker");
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(REMAINING_TIME_KEY, remainingTime);
        outState.putInt(TAPS_LEFT_KEY, tapsLeft);
        countDownTimer.cancel();
    }

    ////////////////////////////////////////////////////////////////////// Driver Code //////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    

    // Member variables initialization

        timerTextView = findViewById(R.id.countdownTimerTextVIew);
        tapCountdownTextView = findViewById(R.id.countdownTapsTextView);
        Button tapTapButton = findViewById(R.id.tapTapButton);

    // Tap button OnClick method

        tapTapButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tapsLeft > 0) {
                    tapsLeft--;
                }
                tapCountdownTextView.setText(tapsLeft  + "\n" + getString(R.string.taps_left));
                if(tapsLeft == 0 && remainingTime > 0){
                    tapCountdownTextView.setText(tapsLeft  + "\n" + getString(R.string.taps_left));
                    showAlert("Congratulations Sucker");
                    countDownTimer.cancel();
                }
            }
        });
        if(savedInstanceState != null){
            resetTheGame(savedInstanceState.getInt(REMAINING_TIME_KEY),savedInstanceState.getInt(TAPS_LEFT_KEY) );
        } else {
            resetTheGame();
        }
    }
}

