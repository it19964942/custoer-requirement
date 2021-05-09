package com.example.car_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DriverTakeOderActivity extends AppCompatActivity {
TextView odId,ItId,ItQty,totAmt;
EditText uPrice;
Button btnClac;
FloatingActionButton btnAddDetails;
String oderId,ItemId,itemQty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_take_oder);
        odId = findViewById(R.id.DriOderId);
        ItId = findViewById(R.id.DrItemId);
        ItQty = findViewById(R.id.DriQty);
        totAmt = findViewById(R.id.DriFullAmount);
        uPrice = findViewById(R.id.DriUnitPrice);
        btnClac = findViewById(R.id.btnDriverCalPayment);
        btnAddDetails = findViewById(R.id.btnDriverAddDetails);

        getIntentData();

        btnClac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalCost();
            }
        });
        btnAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });

    }
    void getIntentData(){
        if(getIntent().hasExtra("odrId")&&getIntent().hasExtra("itemId")&&getIntent().hasExtra("qty")){
            oderId =getIntent().getStringExtra("odrId");
            ItemId = getIntent().getStringExtra("itemId");
            itemQty = getIntent().getStringExtra("qty");

            odId.setText(oderId);
            ItId.setText(ItemId);
            ItQty.setText(itemQty);
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }

    }
    void calculateTotalCost(){
        String txtQty,txtPrice;
        txtQty =ItQty.getText().toString().trim();
        txtPrice =uPrice.getText().toString().trim();
        int qty;
        int price,Famt;
        qty =Integer.parseInt(txtQty);
        price =Integer.parseInt(txtPrice);
        Famt = clacAmount(price,qty);
        totAmt.setText(String.valueOf(Famt));
    }
    public int clacAmount(int a,int b){
        return a *b ;
    }
    public void addOrder() {
        DBHelper dbHelper = new DBHelper(this);
        long val = dbHelper.addDriverOders(odId.getText().toString().trim(), uPrice.getText().toString().trim());

        if (val > 0) {
            Toast.makeText(this, "Data Successfully Inserted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DriverTakeOderActivity.this,driverHomeActivity.class);
            startActivity(intent);
        }else if (val < 0) {
            Toast.makeText(this, "Data Not Inserted" + odId.getText().toString() + "  "+ uPrice.getText().toString(), Toast.LENGTH_LONG).show();
        }
    }
}