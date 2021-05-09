package com.example.car_doctor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateItemActivity extends AppCompatActivity {
    EditText name, brand, size, weight, uprice;
    FloatingActionButton update_button, delete_button;
    Context context;
    String itid, itname, itbrnd, itsize, itweight, ituprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        name = findViewById(R.id.etItemName1);
        brand = findViewById(R.id.etBrandName1);
        size = findViewById(R.id.etSize1);
        weight = findViewById(R.id.weight1);
        uprice = findViewById(R.id.etUnitPrice1);
        update_button = findViewById(R.id.updateItems);
        delete_button = findViewById(R.id.deleteItem);
        getAndSetIntentData();
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    public void updateItem(View view) {
        DBHelper myDB = new DBHelper(UpdateItemActivity.this);

        itname = name.getText().toString().trim();
        itbrnd = brand.getText().toString().trim();
        itsize = size.getText().toString().trim();
        itweight = weight.getText().toString().trim();
        ituprice = uprice.getText().toString().trim();
        long res = myDB.updateData(itid, itname, itbrnd, itsize, itweight, ituprice);
        if (res > 0) {
            Toast.makeText(this,"Successfully Updated!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(UpdateItemActivity.this, ItemActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Unsuccessful!", Toast.LENGTH_LONG).show();
        }
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("brand")
                && getIntent().hasExtra("size") && getIntent().hasExtra("weight") && getIntent().hasExtra("uprice")) {
            //getting data
            itid = getIntent().getStringExtra("id");
            itname = getIntent().getStringExtra("name");
            itbrnd = getIntent().getStringExtra("brand");
            itsize = getIntent().getStringExtra("size");
            itweight = getIntent().getStringExtra("weight");
            ituprice = getIntent().getStringExtra("uprice");

            //setting data
            name.setText(itname);
            brand.setText(itbrnd);
            size.setText(itsize);
            weight.setText(itweight);
            uprice.setText(ituprice);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }


    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + itname + " ?");
        builder.setMessage("Are you want to delete " + itname + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper db = new DBHelper(UpdateItemActivity.this);
                long val = db.deleteOneRow(itid);
                if (val == -1) {
                    Toast.makeText(context, "Faild to Delete!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateItemActivity.this, ItemActivity.class);
                    startActivity(i);

                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}