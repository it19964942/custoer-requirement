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
import android.view.View;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    DBHelper myDB = new DBHelper(ItemActivity.this);
    customAdapter customAdapter;
    ArrayList<String> item_name,item_brand,item_size,item_weight,item_uprice,item_idn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        recyclerView =findViewById(R.id.res1);
        add_btn = findViewById(R.id.fbtAdd);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemActivity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });
        item_idn  = new ArrayList<>();
        item_name = new ArrayList<>();
        item_brand = new ArrayList<>();
        item_size = new ArrayList<>();
        item_weight = new ArrayList<>();
        item_uprice = new ArrayList<>();
        storeDataInArray();



        customAdapter = new customAdapter(ItemActivity.this,this,item_idn,item_name,item_brand,item_size,item_weight,item_uprice);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ItemActivity.this));

        BottomNavigationView bntView = findViewById(R.id.itm_navA);
        bntView.setOnNavigationItemReselectedListener(navlistner);

    }
    private BottomNavigationView.OnNavigationItemReselectedListener navlistner =
            new BottomNavigationView.OnNavigationItemReselectedListener() {

                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_item:
                            Intent i2 = new Intent(ItemActivity.this,ItemCalculationActivity.class);
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
    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                item_idn.add(cursor.getString(0) );
                item_name.add(cursor.getString(1));
                item_brand.add(cursor.getString(2));
                item_size.add(cursor.getString(3));
                item_weight.add(cursor.getString(4));
                item_uprice.add(cursor.getString(5));
            }
        }
    }
}