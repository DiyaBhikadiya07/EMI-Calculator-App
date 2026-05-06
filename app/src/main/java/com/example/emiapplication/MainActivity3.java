package com.example.emiapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    EditText loan, intrest, year, month;
    Button cal;
    String pamount1, yr, mon, intrs;
    TextView emi, intr, pay;
    int fy, totalint, totalpay, totalemi, total, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toast.makeText(getApplicationContext(), "Succesfully Start", Toast.LENGTH_SHORT).show();

        loan = (EditText) findViewById(R.id.loan);
        intrest = (EditText) findViewById(R.id.intrest);
        year = (EditText) findViewById(R.id.year);
        month = (EditText) findViewById(R.id.month);
        cal = (Button) findViewById(R.id.cal);
        emi =  findViewById(R.id.temi);
        intr = (TextView) findViewById(R.id.tint);
        pay = (TextView) findViewById(R.id.tpay);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    pamount1 = loan.getText().toString();
                    int pamount = Integer.parseInt(pamount1);
                    
                    intrs = intrest.getText().toString();
                    double r = Double.parseDouble(intrs) / 12 / 100;
                    
                    yr = year.getText().toString();
                    int val1 = yr.isEmpty() ? 0 : Integer.parseInt(yr);
                    
                    mon = month.getText().toString();
                    int val4 = mon.isEmpty() ? 0 : Integer.parseInt(mon);
                    
                    int totalMonths = (val1 * 12) + val4;

                    if (totalMonths > 0 && r > 0) {
                        // Standard EMI Formula: [P x R x (1+R)^N]/[(1+R)^N-1]
                        double emiValue = (pamount * r * Math.pow(1 + r, totalMonths)) / (Math.pow(1 + r, totalMonths) - 1);
                        double totalPaymentValue = emiValue * totalMonths;
                        double totalInterestValue = totalPaymentValue - pamount;

                        emi.setText(String.format("%.2f", emiValue));
                        intr.setText(String.format("%.2f", totalInterestValue));
                        pay.setText(String.format("%.2f", totalPaymentValue));
                    } else if (totalMonths > 0 && r == 0) {
                        double emiValue = (double) pamount / totalMonths;
                        emi.setText(String.format("%.2f", emiValue));
                        intr.setText("0.00");
                        pay.setText(String.valueOf(pamount));
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity3.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
