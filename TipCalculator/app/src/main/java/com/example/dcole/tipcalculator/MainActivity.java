package com.example.dcole.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.DecimalFormat;


//https://stackoverflow.com/questions/20762001/how-to-set-seekbar-min-and-max-value
//http://abhiandroid.com/ui/seekbar
//
//
public class MainActivity extends AppCompatActivity {
    private SeekBar sbRating;
    private TextView tvRating;
    private TextView tvTip;
    private EditText etCost;
    private Button btnCalculate;
    private double tip;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbRating = (SeekBar) findViewById(R.id.sb);
        tvRating = (TextView) findViewById(R.id.tv);
        tvTip = (TextView) findViewById(R.id.tip);
        etCost = (EditText) findViewById(R.id.cost);

        btnCalculate = (Button) findViewById(R.id.calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int rating = sbRating.getProgress();
                returnTip(rating);
                String sTotal = etCost.getText().toString();
                if (!sTotal.isEmpty())
                    try {
                        double dTotal = Double.parseDouble(sTotal);
                        String s = "Tip: $" + df2.format(tip * dTotal);
                        tvTip.setText(s);
                    } catch (Exception e1) {
                        // this means it is not double
                        e1.printStackTrace();
                    }
            }
        });

        sbRating.setMax(10);
        sbRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                returnTip(i);
                String s = "Rating: " + i + " (%" + tip * 100 + ")";
                tvRating.setText(s);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void returnTip(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3: {
                tip = .1;
                break;
            }
            case 4:
            case 5: {
                tip = .13;
                break;
            }
            case 6:
            case 7: {
                tip = .15;
                break;
            }
            case 8:
            case 9: {
                tip = .20;
                break;
            }
            case 10: {
                tip = .25;
                break;
            }
        }
    }
}
