package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String SCORE_KEY = "SCORE";
    private final String QUES_NUM_KEY = "QUESTION_INDEX";
    private final String RIGHT_ANS_KEY = "RIGHT_ANS";
    private final String WRONG_ANS_KEY = "WRONG_ANS";

    private int quesNum;
    private int rightNum;
    private int wrongNum;
    private int answeredNum;
    private int score;

    private Button nextQues;
    private Button prevQues;
    private Button trueButton;
    private Button falseButton;
    private Button resetQuiz;
    private Button resetQues;

    private TextView quesNumText;
    private TextView quesText;
    private TextView rightAnsNumText;
    private TextView wrongAnsNumText;

    private ProgressBar progressBar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"The code has been loaded !",Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null ) {

            quesNum = savedInstanceState.getInt(QUES_NUM_KEY);
            score = savedInstanceState.getInt(SCORE_KEY);
            rightNum = savedInstanceState.getInt(RIGHT_ANS_KEY);
            wrongNum = savedInstanceState.getInt(WRONG_ANS_KEY);
            answeredNum = rightNum + wrongNum;

        } else {

            quesNum = 0;
            rightNum = 0;
            wrongNum =0;
            score = rightNum*10;
            wrongNum = 0;

        }

        nextQues = findViewById(R.id.nextQues);
        prevQues = findViewById(R.id.prevQues);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        resetQuiz = findViewById(R.id.resetQuizButton);
        resetQues = findViewById(R.id.resetQuestionButton);

        quesNumText = findViewById(R.id.quesNumText);
        quesText = findViewById(R.id.quesText);
        rightAnsNumText = findViewById(R.id.rightAnsNumText);
        wrongAnsNumText = findViewById(R.id.wrongAnsNumText);

        progressBar = findViewById(R.id.progressBar);

        final String[] ques = {getString(R.string.q1),getString(R.string.q2),getString(R.string.q3),getString(R.string.q4),getString(R.string.q5),getString(R.string.q6),getString(R.string.q7),getString(R.string.q8),getString(R.string.q9),getString(R.string.q10)};
        final String[] ansKey = {getString(R.string.a1),getString(R.string.a2),getString(R.string.a3),getString(R.string.a4),getString(R.string.a5),getString(R.string.a6),getString(R.string.a7),getString(R.string.a8),getString(R.string.a9),getString(R.string.a10)};
        final String[] userAns = {"0","0","0","0","0","0","0","0","0","0"};
        final String[] bool = {"0","0","0","0","0","0","0","0","0","0"};

        quesNumText.setText("Question number " + (quesNum + 1) + " out of 10");
        quesText.setText(ques[quesNum]);
        rightAnsNumText.setText("Right:\n  " + rightNum);
        wrongAnsNumText.setText(("Wrong:\n "+ wrongNum));

//       *Dynamic method for an onClick as it creates a OC for a view type object and then assigns that OC to the nextQues button view.
//       ***advantage is that we can assign same OC to multiple views
//
//
//        View.OnClickListener myClickListenerForNextQues = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(quesNum[0]<9) {
//
//                    quesNum[0]++;
//                    quesNumText.setText("Question number " + (quesNum[0] + 1) + " out of 10");
//                    quesText.setText(ques[quesNum[0]]);
//
//                }
//
//            }
//        };
//        nextQues.setOnClickListener(myClickListenerForNextQues);

        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quesNum<9) {

                    quesNum++;
                    quesNumText.setText("Question number " + (quesNum + 1) + " out of 10");
                    quesText.setText(ques[quesNum]);

                }

            }
        });
        prevQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quesNum>0) {

                    quesNum--;
                    quesNumText.setText("Question number " + (quesNum + 1) + " out of 10");
                    quesText.setText(ques[quesNum]);

                }

            }
        });
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userAns[quesNum].equals("0")) {

                    userAns[quesNum] = "true";
                    answeredNum++;

                    if (userAns[quesNum].equals(ansKey[quesNum])) {

                        rightNum++;
                        score = rightNum*10;
                        rightAnsNumText.setText("Right:\n  " + rightNum);
                        bool[quesNum] = "right";
                        progressBar.setProgress(score);

                    } else {

                        wrongNum++;
                        wrongAnsNumText.setText("Wrong:\n  " + wrongNum);
                        bool[quesNum] = "wrong";

                    }

                }

                if(quesNum<9) {

                    quesNum++;
                    quesNumText.setText("Question number " + (quesNum + 1) + " out of 10");
                    quesText.setText(ques[quesNum]);

                }

            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userAns[quesNum].equals("0")) {

                    userAns[quesNum] = "false";
                    answeredNum++;


                    if (userAns[quesNum].equals(ansKey[quesNum])) {

                        rightNum++;
                        score = rightNum*10;
                        rightAnsNumText.setText("Right:\n  " + rightNum);
                        bool[quesNum] = "right";
                        progressBar.setProgress(score);

                    } else {

                        wrongNum++;
                        wrongAnsNumText.setText("Wrong:\n  " + wrongNum);
                        bool[quesNum] = "wrong";

                    }

                }

                if(quesNum<9) {

                    quesNum++;
                    quesNumText.setText("Question number " + (quesNum + 1) + " out of 10");
                    quesText.setText(ques[quesNum]);

                }

            }
        });
        resetQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!userAns[quesNum].equals("0")) {

                    userAns[quesNum] = "0";
                    answeredNum--;

                    if(bool[quesNum].equals("right")) {

                        rightNum--;
                        score = rightNum*10;
                        rightAnsNumText.setText("Right:\n  " + rightNum);
                        progressBar.setProgress(score);

                    } else {

                        wrongNum--;
                        wrongAnsNumText.setText("Wrong:\n  " + wrongNum);

                    }

                }
            }
        });
        resetQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quesNum = 0;
                rightNum = 0;
                wrongNum = 0;
                answeredNum = 0;
                score = rightNum*10;

                rightAnsNumText.setText(getString(R.string.right_counter));
                wrongAnsNumText.setText(getString(R.string.wrong_counter));

                quesNumText.setText("Question number " + (quesNum + 1) + " out of 10");
                quesText.setText(ques[0]);

                progressBar.setProgress(score);

                for(int i=0 ; i<10 ; i++) {

                    userAns[i] = "0";
                    bool[i] = "0";

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(),"The app has started !",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(),"The app can be now used !",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(),"The app goes into partial background !",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(),"The app goes into background !",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(),"The app has been terminated !",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY, score);
        outState.putInt(QUES_NUM_KEY, quesNum);
        outState.putInt(RIGHT_ANS_KEY, rightNum);
        outState.putInt(WRONG_ANS_KEY, wrongNum);

    }


}
