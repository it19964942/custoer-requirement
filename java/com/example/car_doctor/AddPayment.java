package com.example.car_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddPayment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView u_types, totAmt;
    EditText year, month, qprice, rate;
    Button clac;
    FloatingActionButton addDetails;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        Spinner spinner = findViewById(R.id.spinner1);
        u_types = findViewById(R.id.txtUserType);
        totAmt = findViewById(R.id.txtTotAmt);
        year = findViewById(R.id.txtYear);
        month = findViewById(R.id.txtMonth);
        qprice = findViewById(R.id.txxQPrice);
        rate = findViewById(R.id.txtPresent);
        clac = findViewById(R.id.btnCalAd);
        addDetails = findViewById(R.id.btnAdminAdd);
        dbHelper = new DBHelper(this);

        clac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc();
            }
        });

        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetails();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.UserTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        /*ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);*/
    }

    void calc() {
        String a, b;
        a = qprice.getText().toString().trim();
        b = rate.getText().toString().trim();

        int c, d, tot;
        c = Integer.parseInt(a);
        d = Integer.parseInt(b);

        totAmt.setText(String.valueOf(clacTot(c,d)));
    }
    public int clacTot(int a, int b){
        return ((a * b) / 100) * 12;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        u_types.setText(parent.getItemAtPosition(position).toString());
        //month.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void addDetails() {
        long result = dbHelper.addpayment(u_types.getText().toString().trim(), year.getText().toString().trim(),
                month.getText().toString().trim(), totAmt.getText().toString().trim());

        if (result == -1) {
            Toast.makeText(this, "Sorry, " + u_types.getText().toString() + " cannot add at this moment!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, u_types.getText().toString() + " Successfully added!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddPayment.this, AdminHomeActivity.class);
            startActivity(intent);
        }
        // Toast.makeText(this, "Sorry, " + year.getText().toString() +" cannot add at this moment!", Toast.LENGTH_SHORT).show();
    }
}