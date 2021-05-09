package com.example.car_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddItemActivity extends AppCompatActivity {
    EditText txtItemName, txtBrandName,txtItemWeight, txtItemSize,txtUnitPrice;
    FloatingActionButton btnInsertItem;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        txtItemName = findViewById(R.id.etItemName);
        txtBrandName = findViewById(R.id.etBrandName);
        txtItemWeight = findViewById(R.id.weight);
        txtItemSize = findViewById(R.id.etSize);
        txtUnitPrice = findViewById(R.id.etUnitPrice);
        btnInsertItem = findViewById(R.id.insertitems);
        dbHelper = new DBHelper(this);
        btnInsertItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem();
            }
        });
    }
    private void insertItem(){
        long result = dbHelper.addItems(txtItemName.getText().toString().trim(),txtBrandName.getText().toString().trim(),
                txtItemSize.getText().toString().trim(),txtItemWeight.getText().toString().trim(),
                txtUnitPrice.getText().toString().trim());

        if(result == -1){
            Toast.makeText(this, "Sorry, "+txtItemName.getText().toString()+" cannot add at this moment!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, ""+txtItemName.getText().toString()+" Successfully added!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddItemActivity.this,ItemActivity.class);
            startActivity(intent);
        }
    }
}