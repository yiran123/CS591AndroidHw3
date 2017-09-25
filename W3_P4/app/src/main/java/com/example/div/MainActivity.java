package com.example.div;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    TextView textCounter;
    Button gen, submit;
    EditText et;
    public static final int TIMES = 10;
    public static final int MAX_GRADE = 100;
    private int currentIndex = 1;
    private int num1, num2;
    private int score = 0;
    private String text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        gen = (Button) findViewById(R.id.bt_gen);
        submit = (Button) findViewById(R.id.bt_submit);
        et = (EditText) findViewById(R.id.et);
        textCounter = (TextView) findViewById(R.id.textCounter);

        //landscape
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex");
            num1 = savedInstanceState.getInt("num1");
            num2 = savedInstanceState.getInt("num2");
            score = savedInstanceState.getInt("score");
            tv1.setText(num1 + "");
            tv2.setText(num2 + "");}
//        } else {
//            generate();
//        }
        String s = gen.getText().toString();
        gen.setText(s.replace("X", TIMES + ""));
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = 1;
                generate();
                textCounter.setText(intToStr(currentIndex) + "/10");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().toString().trim().length() == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                }
                else{
                    String s = et.getText().toString();
                    et.setText("");
                    int num = Integer.parseInt(s);
                    if (currentIndex < TIMES) {
                        if (num1 / num2 == num) {
                            score++;
                        }
                        currentIndex++;
                        textCounter.setText(intToStr(currentIndex) + "/10");
                        generate();
                    }
                    else if (currentIndex == TIMES) {
                        Toast.makeText(MainActivity.this, "You got " + intToStr(score+1) + " answers right", Toast.LENGTH_LONG).show();
                        textCounter.setText(null);
                        score = 0;

                    }
                    else {
                        textCounter.setText(null);
                        generate();
                    }
                }


            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex",currentIndex);
        outState.putInt("score",score);
        outState.putInt("num1",num1);
        outState.putInt("num2",num2);
    }

    private void generate() {
        Random random = new Random();
        while (true) {
            num1 = random.nextInt(100) + 1;
            num2 = random.nextInt(num1) + 1;
            if (num1 % num2 == 0) break;
        }
        tv1.setText(num1 + "");
        tv2.setText("" + num2);
    }
    public String intToStr(Integer i)
    {
        return i.toString();
    }

    public int strToInt(String S)
    {
        return Integer.parseInt(S);
    }
}
