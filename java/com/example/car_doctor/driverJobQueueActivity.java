package com.example.car_doctor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;

import java.util.ArrayList;

public class driverJobQueueActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper db = new DBHelper(driverJobQueueActivity.this);
    driverOderAdpter driverOderAdpter;
    ArrayList<String> j_id,odId,amt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_job_queue);

        recyclerView = findViewById(R.id.resJobQueue);
        j_id = new ArrayList<>();
        odId = new ArrayList<>();
        amt = new ArrayList<>();
        storeOderDetails();
        driverOderAdpter = new driverOderAdpter(driverJobQueueActivity.this,this,j_id,odId,amt);
        recyclerView.setAdapter(driverOderAdpter);
        recyclerView.setLayoutManager(new LinearLayoutManager(driverJobQueueActivity.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeOderDetails(){
        Cursor cursor= db.readAllDriverOders();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                j_id.add(cursor.getString(0));
                odId.add(cursor.getString(1));
                amt.add(cursor.getString(2));
            }
        }
    }
}