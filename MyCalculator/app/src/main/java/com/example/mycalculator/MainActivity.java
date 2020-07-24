package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] calculation = {""};
        final double[] answer = {0};

        final Button buttonNumber0 = findViewById(R.id.buttonNumber0);
        final Button buttonNumber1 = findViewById(R.id.buttonNumber1);
        final Button buttonNumber2 = findViewById(R.id.buttonNumber2);
        final Button buttonNumber3 = findViewById(R.id.buttonNumber3);
        final Button buttonNumber4 = findViewById(R.id.buttonNumber4);
        final Button buttonNumber5 = findViewById(R.id.buttonNumber5);
        final Button buttonNumber6 = findViewById(R.id.buttonNumber6);
        final Button buttonNumber7 = findViewById(R.id.buttonNumber7);
        final Button buttonNumber8 = findViewById(R.id.buttonNumber8);
        final Button buttonNumber9 = findViewById(R.id.buttonNumber9);
        final Button buttonBracketOpen = findViewById(R.id.buttonBracketOpen);
        final Button buttonBracketClose = findViewById(R.id.buttonBracketClose);
        final Button buttonDecimal = findViewById(R.id.buttonDecimal);
        final Button buttonReset = findViewById(R.id.buttonReset);
        final Button buttonAdd = findViewById(R.id.buttonAdd);
        final Button buttonSubtract = findViewById(R.id.buttonSubtract);
        final Button buttonMultiply = findViewById(R.id.buttonMultiply);
        final Button buttonDivide = findViewById(R.id.buttonDivide);
        final Button buttonEquals = findViewById(R.id.buttonEquals);
        final Button buttonDelete = findViewById(R.id.buttonDelete);

        final TextView str = findViewById(R.id.textView1);
        final TextView ans = findViewById(R.id.textView2);

        buttonNumber0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "0";
                str.setText(calculation[0]);

            }
        });
        buttonNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "1";
                str.setText(calculation[0]);

            }
        });
        buttonNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "2";
                str.setText(calculation[0]);

            }
        });
        buttonNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "3";
                str.setText(calculation[0]);

            }
        });
        buttonNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "4";
                str.setText(calculation[0]);

            }
        });
        buttonNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "5";
                str.setText(calculation[0]);

            }
        });
        buttonNumber6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "6";
                str.setText(calculation[0]);

            }
        });
        buttonNumber7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "7";
                str.setText(calculation[0]);

            }
        });
        buttonNumber8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "8";
                str.setText(calculation[0]);

            }
        });
        buttonNumber9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "9";
                str.setText(calculation[0]);

            }
        });
        buttonBracketOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "(";
                str.setText(calculation[0]);

            }
        });
        buttonBracketClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + ")";
                str.setText(calculation[0]);

            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "+";
                str.setText(calculation[0]);

            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "-";
                str.setText(calculation[0]);

            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "*";
                str.setText(calculation[0]);

            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + "/";
                str.setText(calculation[0]);

            }
        });
        buttonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculation[0] = calculation[0] + ".";
                str.setText(calculation[0]);

            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(calculation[0].length()>0) {
                    calculation[0] = calculation[0].substring(0, calculation[0].length() - 1);
                    str.setText(calculation[0]);
                }
            }
        });
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    answer[0] = eval(calculation[0]);
                    ans.setText("" + answer[0]);
                } catch (Exception e) {
                    ans.setText("Input Is Wrong!!");
                }

            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                calculation[0] = "";
                str.setText(calculation[0]);
                ans.setText("");

            }
        });
    }
}
