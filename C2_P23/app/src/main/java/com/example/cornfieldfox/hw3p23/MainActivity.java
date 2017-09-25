package com.example.cornfieldfox.hw3p23;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String states = "red";
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {

                    View tfLight = (TextView) findViewById(R.id.light);

                  if(states.equals("red"))
                  {
                      states ="yellow";
                      tfLight.setBackgroundColor(getResources().getColor(R.color.yellow));
                      flag = true;

                  }
                  else{
                      {
                          if(states.equals("green"))
                          {
                              states = "yellow";
                              tfLight.setBackgroundColor(getResources().getColor(R.color.yellow));

                              flag = false;
                          }
                          else
                          {
                              if(states.equals("yellow"))
                              {
                                  if(flag)
                                  {
                                    states = "green";
                                      tfLight.setBackgroundColor(getResources().getColor(R.color.green));

                                  }
                                  else
                                  {
                                      states = "red";
                                      tfLight.setBackgroundColor(getResources().getColor(R.color.red));

                                  }

                              }
                          }

                      }
                  }



                }
            }
        });






    }



}
