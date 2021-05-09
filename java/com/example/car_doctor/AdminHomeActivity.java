package com.example.car_doctor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminHomeActivity extends AppCompatActivity {
    FloatingActionButton btnAdd;
    RecyclerView recyclerView;
    DBHelper myDB = new DBHelper(AdminHomeActivity.this);
    ArrayList<String> p_id,p_month,p_year,p_amount,p_type;
    AdminAdapter adminAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        recyclerView = findViewById(R.id.resAdmin);
        p_id = new ArrayList<>();
        p_month = new ArrayList<>();
        p_year = new ArrayList<>();
        p_amount = new ArrayList<>();
        p_type = new ArrayList<>();
        storeDataInArray();
        adminAdapter = new AdminAdapter(AdminHomeActivity.this,this,p_id,p_type,p_year,p_month,p_amount);
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminHomeActivity.this));

        btnAdd = findViewById(R.id.addUserPayments);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHomeActivity.this,AddPayment.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
    void storeDataInArray() {
        Cursor cursor = myDB.readAllPayment();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                p_id.add(cursor.getString(0));
                p_type.add(cursor.getString(1));
                p_year.add(cursor.getString(2));
                p_month.add(cursor.getString(3));
                p_amount.add(cursor.getString(4));
            }
        }
    }
}