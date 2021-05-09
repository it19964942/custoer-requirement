package com.example.car_doctor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;

import java.util.ArrayList;

public class ViewOdersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper db = new DBHelper(ViewOdersActivity.this);
    OderAdapter oderAdapter;
    ArrayList<String> oderId,ItemId,Qty;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_oders);
        recyclerView =findViewById(R.id.resOder);
        oderId = new ArrayList<>();
        ItemId = new ArrayList<>();
        Qty = new ArrayList<>();
        storeOderDetails();
        oderAdapter = new OderAdapter(ViewOdersActivity.this,this,oderId,ItemId,Qty);
        recyclerView.setAdapter(oderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewOdersActivity.this));
    }
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