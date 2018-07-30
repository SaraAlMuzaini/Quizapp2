package com.example.sarahahmad.quizapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // score
    int score;
    // Question 1
    boolean q1IsAnswer = false;
    EditText q1_text;
    boolean q1correct = false;
    // Question 2
    boolean q2IsAnswer = false;
    CheckBox q2Ch1;
    CheckBox q2Ch2;
    CheckBox q2Ch3;
    CheckBox q2Ch4;
    boolean q2correct = false;
    int count ;
    // Question 3
    boolean q3IsAnswer = false;
    RadioGroup q3_Rg;
    RadioButton q3Ch2;
    boolean q3correct = false;
    // Question 4
    boolean q4IsAnswer = false;
    RadioGroup q4_Rg;
    RadioButton q4Ch3;
    boolean q4correct = false;
    String name = " ";
    String result = " ";
    LinearLayout resultLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(getClass().getName(), "value = " + score);
        resultLayout = (LinearLayout) findViewById(R.id.resultLL);
        q1_text = (EditText) this.findViewById(R.id.q1Answer);
        q2Ch1 = (CheckBox) this.findViewById(R.id.q2chose1);
        q2Ch2 = (CheckBox) this.findViewById(R.id.q2chose2);
        q2Ch3 = (CheckBox) this.findViewById(R.id.q2chose3);
        q2Ch4 = (CheckBox) this.findViewById(R.id.q2chose4);
        q3Ch2 = (RadioButton) this.findViewById(R.id.q3ch2);
        q4Ch3 = (RadioButton) this.findViewById(R.id.q4chose3);
        q3_Rg = (RadioGroup) this.findViewById(R.id.q3choose);
        q4_Rg = (RadioGroup) this.findViewById(R.id.q4choose);
    }

    public void q1Result() {
        // Q1 - Correct Answer is chose2 "No"
        String answerQ1 = q1_text.getText().toString();
        if (!answerQ1.matches("")) {
            q1IsAnswer = true;
            boolean answer1 = answerQ1.equalsIgnoreCase("No");

            if (answer1) {
                q1correct = true;
                score++;
            } else {
                q1correct = false;
            }
        } else {
            q1IsAnswer = false;
        }
    }


    public void q2Result() {
        // Q2 - Correct Answer is chose1  "square" and chose3 "rectangle"
        Boolean answer2ch1 = q2Ch1.isChecked();
        Boolean answer2ch2 = q2Ch2.isChecked();
        Boolean answer2ch3 = q2Ch3.isChecked();
        Boolean answer2ch4 = q2Ch4.isChecked();
        if (!answer2ch1 && !answer2ch2 && !answer2ch3 && !answer2ch4) {
            q2IsAnswer = false;
        }else{
            q2IsAnswer = true;
            if (!answer2ch2 || !answer2ch4) {
                if (answer2ch1 && answer2ch3) {
                    q2correct = true;
                    count = 2;
                    score++;
                } else {
                    if (answer2ch1 || answer2ch3) {
                        q2correct = true;
                        count = 1;
                    }
                }
            } else {
                q2correct = false;
                count = 0;
            }
        }

    }

    public void q3Result() {
        // Q3 - Correct Answer is chose2 "5"
        if (q3_Rg.getCheckedRadioButtonId() != -1) {
            q3IsAnswer = true;
            Boolean answer3;
            answer3 = q3Ch2.isChecked();
            if (answer3) {
                q3correct = true;
                score++;
            } else {
                q3correct = false;
            }
        } else {
            q3IsAnswer = false;
        }
    }


    public void q4Result() {
        // Q4 - Correct Answer is chose3 "Blue Circle"
        if (q4_Rg.getCheckedRadioButtonId() != -1) {
            q4IsAnswer = true;
            Boolean answer4;
            answer4 = q4Ch3.isChecked();
            if (answer4) {
                q4correct = true;
                score++;
            } else {
                q4correct = false;
            }
        } else {
            q4IsAnswer = false;
        }
    }

    public void isAnswers() {
        q1Result();
        q2Result();
        q3Result();
        q4Result();

    }

    public void answerDisplay() {
        result += "Q1: ";
        if (q1correct) {
            result += getString(R.string.correct);
            result += getString(R.string.A1);
        } else {
            result += getString(R.string.incorrect);
            result += getString(R.string.A1);
        }
        result += "Q2: ";
        if (!q2correct) {
            result += getString(R.string.incorrect);
            result += getString(R.string.A2);
        } else {
            if (count == 2) {
                result += getString(R.string.correct);
                result += getString(R.string.A2);

            } else {
                if (count == 1) {
                    result += getString(R.string.incomplete);
                    result += getString(R.string.A2);
                }
            }
        }
        result += "Q3: ";
        if (q3correct) {
            result += getString(R.string.correct);
            result += getString(R.string.A3);
        } else {
            result += getString(R.string.incorrect);
            result += getString(R.string.A3);
        }
        result += "Q4: ";
        if (q4correct) {
            result += getString(R.string.correct);
            result += getString(R.string.A4);
        } else {
            result += getString(R.string.incorrect);
            result += getString(R.string.A4);
        }
    }

    public void Summary() {
        //Get name from EditText
        EditText Name = (EditText) findViewById(R.id.AnswerName);
        name = Name.getText().toString();
        result = getString(R.string.hello);
        result += name;
        result += "..\n\n";


        result += getString(R.string.score);
        result += score;
        result += getString(R.string.totalScore);

        result += getString(R.string.answers);
        answerDisplay();


        result += getString(R.string.end);

    }

    public void getResult(View view) {
        EditText Name = (EditText) findViewById(R.id.AnswerName);
        name = Name.getText().toString();
        isAnswers();
        if (!q1IsAnswer || !q2IsAnswer || !q3IsAnswer || !q4IsAnswer) {
            result += getString(R.string.noAnswer);
        } else {
            Summary();
        }
        TextView displayResult = (TextView) findViewById(R.id.displayText);
        displayResult.setText(result);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, result, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        resultLayout.setVisibility(View.VISIBLE);
        reSet();
    }

    public void reSet() {
        score = 0;
        count = 0;
    }
}
