package com.artyom.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score "
                        + Integer.toString(score)
                        + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
        
        generateQuestion();
    }

    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText((Integer.toString(answers.get(0))));
        button1.setText((Integer.toString(answers.get(1))));
        button2.setText((Integer.toString(answers.get(2))));
        button3.setText((Integer.toString(answers.get(3))));


    }

    public void chooseAnswer(View view) {

        if (view.getTag()
                .toString()
                .equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)
                + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);

    }

}