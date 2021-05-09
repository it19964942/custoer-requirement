package com.example.car_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class driverHomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper db = new DBHelper(driverHomeActivity.this);
    driverViewOderAdapter driverViewOderAdapter;
    ArrayList<String> oderId,ItemId,Qty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        recyclerView =findViewById(R.id.resDriverOder);
        oderId = new ArrayList<>();
        ItemId = new ArrayList<>();
        Qty = new ArrayList<>();
        storeOderDetails();
        driverViewOderAdapter = new driverViewOderAdapter(driverHomeActivity.this,this,oderId,ItemId,Qty);
        recyclerView.setAdapter(driverViewOderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(driverHomeActivity.this));
        BottomNavigationView bntView = findViewById(R.id.drinav);
        bntView.setOnNavigationItemReselectedListener(navlistner);
    }
    private BottomNavigationView.OnNavigationItemReselectedListener navlistner =
            new BottomNavigationView.OnNavigationItemReselectedListener() {

                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_ordersd:
                            Intent i2 = new Intent(driverHomeActivity.this,driverJobQueueActivity.class);
                            startActivity(i2);
                            break;
                    }
                }
            };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeOderDetails(){
        Cursor cursor= db.readAllOders();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                oderId.add(cursor.getString(0));
                ItemId.add(cursor.getString(1));
                Qty.add(cursor.getString(2));
            }
        }
    }
}