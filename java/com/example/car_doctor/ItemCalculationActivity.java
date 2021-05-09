package com.example.car_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemCalculationActivity extends AppCompatActivity {
    EditText unitPrice,totunits,rate;
    TextView currntMonth,yearPayment;
    Button btnCal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_calculation);
        unitPrice = findViewById(R.id.payUnitPrice);
        totunits = findViewById(R.id.payTotUnits);
        rate = findViewById(R.id.payMonthlyRate);
        currntMonth = findViewById(R.id.lblCurMonth);
        yearPayment = findViewById(R.id.lblProYear);
        btnCal = findViewById(R.id.btnCalPayment);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePayment();
            }
        });


    }
    void calculatePayment(){
        String unprice,urate,tUnits;

        unprice = unitPrice.getText().toString().trim();
        urate = totunits.getText().toString().trim();
        tUnits = rate.getText().toString().trim();

        int u_price,u_rate,m_pay,y_pay;
        int unt;
        u_price = Integer.parseInt(unprice);
        u_rate = Integer.parseInt(urate);
        unt = Integer.parseInt(tUnits);

        m_pay = clacMonthPayment(u_price,unt,u_rate);
        y_pay = clacYearPayment(m_pay);

        currntMonth.setText(String.valueOf(m_pay));
        yearPayment.setText(String.valueOf(y_pay));
    }
    public int clacMonthPayment(int price,int unt, int rate){
        return (((price * unt) * rate) / 100);
    }
    public int clacYearPayment( int mon_pay){
        return mon_pay * 12;
    }
}