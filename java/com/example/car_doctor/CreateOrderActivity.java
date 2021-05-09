package com.example.car_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.example.car_doctor.DATABASE.DBHelperCus;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateOrderActivity extends AppCompatActivity {
    EditText id, iname, ibrand, isize, iweight, iprice, iqty, itot;
    Button btnCal;
    FloatingActionButton btnAddOder;
    String iid, itname, itbrand, itsize, itweight, itprice, itqty, ittot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        id = findViewById(R.id.titlenolabel);
        iname = findViewById(R.id.titlelabel2);
        ibrand = findViewById(R.id.brandlabel);
        isize = findViewById(R.id.sizelable);
        iweight = findViewById(R.id.widthlabel);
        iprice = findViewById(R.id.pricelabel);
        iqty = findViewById(R.id.lblQty);
        itot = findViewById(R.id.finalprice);
        btnCal = findViewById(R.id.btnCalTotAmount);
        btnAddOder = findViewById(R.id.btnAddOder);
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = iprice.getText().toString().trim();
                String b = iqty.getText().toString().trim();
                int amt;
                int qty;
                amt = Integer.parseInt(a);
                qty = Integer.parseInt(b);
                int tot = calcAmount(amt,qty);
                String vals = String.valueOf(tot);
                itot.setText(vals);
            }
        });
        btnAddOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addOrder();
            }
        });
        getAndSetIntentItemData();

    }

    public int calcAmount(int a, int b){
        return a * b;
    }

    public void getAndSetIntentItemData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("brand")
                && getIntent().hasExtra("size") && getIntent().hasExtra("weight") && getIntent().hasExtra("uprice")) {
            //getting data
            iid = getIntent().getStringExtra("id");
            itname = getIntent().getStringExtra("name");
            itbrand = getIntent().getStringExtra("brand");
            itsize = getIntent().getStringExtra("size");
            itweight = getIntent().getStringExtra("weight");
            itprice = getIntent().getStringExtra("uprice");

            //setting data
            id.setText(iid);
            iname.setText(itname);
            ibrand.setText(itbrand);
            isize.setText(itsize);
            iweight.setText(itweight);
            iprice.setText(itprice);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void addOrder() {
        DBHelper dbHelper = new DBHelper(this);
        long val = dbHelper.addOrderDet(id.getText().toString().trim(), iqty.getText().toString().trim());

        if (val > 0) {
            Toast.makeText(this, "Data Successfully Inserted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CreateOrderActivity.this,CustomerHomeActivity.class);
            startActivity(intent);
        }else if (val < 0) {
            Toast.makeText(this, "Data Not Inserted" + id.getText().toString() + "  "+ iqty.getText().toString(), Toast.LENGTH_LONG).show();
        }
    }
}