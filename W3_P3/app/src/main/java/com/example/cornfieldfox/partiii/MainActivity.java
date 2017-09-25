package com.example.cornfieldfox.partiii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        SeekBar celSB = (SeekBar)findViewById(R.id.celBar);
        celSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView celTx = (TextView) findViewById(R.id.celDeg);
                celTx.setText(String.format("%.2f",((double)i)/100 - 100));

                SeekBar fahSb = (SeekBar)findViewById(R.id.fahBar);
                double fah = CtoF(i);
                TextView fahTx = (TextView) findViewById(R.id.fahDeg);
                fahTx.setText(String.format("%.2f", fah));
                fahSb.setProgress((int)(fah * 100) + 14800);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar fahSB = (SeekBar) findViewById(R.id.fahBar);
        fahSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double fahDeg = ((double)i-14800)/100;
                TextView fahTx = (TextView) findViewById(R.id.fahDeg);
                fahTx.setText(String.format("%.2f", fahDeg));

                double celDeg = FtoC(i);
                TextView celTx = (TextView) findViewById(R.id.celDeg);
                celTx.setText(String.format("%.2f", celDeg));
                SeekBar celSB = (SeekBar) findViewById(R.id.celBar);
                celSB.setProgress((int)celDeg*100 +10000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initial()
    {
        TextView cel = (TextView) findViewById(R.id.celDeg);
        cel.setText(String.format("%.2f",0.00));

        TextView fah = (TextView) findViewById(R.id.fahDeg);
        fah.setText(String.format("%.2f", 32.00));

        SeekBar celbar = (SeekBar) findViewById(R.id.celBar);

        celbar.setMax(20000);
        celbar.setProgress(10000);

        SeekBar fahbar = (SeekBar) findViewById(R.id.fahBar);
        fahbar.setMax(36000);
        fahbar.setProgress(18000);

    }
    public double CtoF(int cel)
    {

        double celD = (double)cel / 100-100;
        return celD * 1.8 +32;

    }
    public double FtoC(int fah)
    {


        double fahD = (double)fah/100-148;
        return (fahD - 32)/1.8;
    }
}
