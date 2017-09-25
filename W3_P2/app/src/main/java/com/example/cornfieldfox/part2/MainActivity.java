package com.example.cornfieldfox.part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView AmountRaw =(TextView) findViewById(R.id.AmountRaw);
        TextView tipPer = (TextView) findViewById(R.id.tipAmount);
        TextView tipNum = (TextView) findViewById(R.id.TipNum);
        TextView AmountTotal = (TextView)findViewById(R.id.totalNum);
        SeekBar tipPerSb = (SeekBar)findViewById(R.id.tipPerSB);
        // initialize
        Initial(AmountRaw, tipPer, tipNum, AmountTotal);




        //Amount Listener:

        View.OnClickListener AmountLsn = new View.OnClickListener() {
            @Override
            public void onClick(View AmountTxt) {
                String AmountStr = ((TextView)AmountTxt).getText().toString();
                System.out.println(AmountStr);
                if(AmountStr.equals("Enter Amount")||AmountStr.equals(""))
                {
                    ((EditText)AmountTxt).setTextKeepState("$");
                    ((EditText)AmountTxt).setSelection(1);
                }
            }
        };

        AmountRaw.setOnClickListener(AmountLsn);
        AmountRaw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("Editable::"+s);

                TextView tipPER = (TextView)findViewById(R.id.tipAmount);
                String tipPer = tipPER.getText().toString();

                String tip = calTip(tipPer);
                calTotal(tip);


                if(!s.toString().startsWith("$"))
                {
                    TextView AmountTxt = (TextView) findViewById(R.id.AmountRaw);
                    ((EditText)AmountTxt).setTextKeepState("$");
                    ((EditText)AmountTxt).setSelection(1);
                }
            }
        });

        //seekbar Listener
        tipPerSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int tipPerNum = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView tipPer = (TextView) findViewById(R.id.tipAmount);
                tipPer.setText(i+"%");
                tipPerNum = i;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("TipPerNum"+ tipPerNum);
                String tip = calTip(Integer.toString(tipPerNum));
                calTotal(tip);
            }
        });

    }

    void Initial(TextView AmountRaw, TextView tipPer, TextView tipNum, TextView AmountTotal)
    {
        AmountRaw.setText("Enter Amount");
        tipPer.setText("0%");
        tipNum.setText("");
        AmountTotal.setText("");
    }
    private void calTotal(String tip)
    {
        TextView AmountRaw = (TextView) findViewById(R.id.AmountRaw);
        String AmountRawStr = AmountRaw.getText().toString();
        AmountRawStr = AmountRawStr.replace("$","");

        TextView totalNum = (TextView) findViewById(R.id.totalNum);
        if(tip == null ||tip == "" || AmountRawStr == "" || AmountRawStr== null || AmountRawStr.equals("Enter Amount"))
        {
            totalNum.setText("");
            return;
        }

        double AmountRawDB = Double.parseDouble(AmountRawStr);

        double tipDB = Double.parseDouble(tip);
        double resDB = tipDB+AmountRawDB;
        String res = String.format("%.2f", resDB);
        totalNum.setText("$"+res);
    }
    private String calTip(String tipRaw)
    {
        TextView AmountRaw = (TextView)findViewById(R.id.AmountRaw);
        String AmountRawStr = AmountRaw.getText().toString();
        TextView tipNum = (TextView) findViewById(R.id.TipNum);

        AmountRawStr = AmountRawStr.replace("$","");
        System.out.println(AmountRawStr);
        if(AmountRawStr.equals("Enter Amount")||AmountRawStr==null|| AmountRawStr.equals(""))
        {
            tipNum.setText("");
            return "";
        }
        tipRaw = tipRaw.replace("%","");

        if(tipRaw.equals("0")||tipRaw.equals(""))
        {
            tipNum.setText("");
            return "";
        }
        int tipPer = Integer.parseInt(tipRaw);

        Double AmountRawDB = Double.parseDouble(AmountRawStr);

        double resDB = (double)tipPer / 100 * AmountRawDB;
        String res = String.format("%.2f",resDB);

        tipNum.setText("$"+res);

        return res;
    }
}
