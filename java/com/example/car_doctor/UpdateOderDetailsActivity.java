package com.example.car_doctor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_doctor.DATABASE.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateOderDetailsActivity extends AppCompatActivity {
    EditText qty;
    TextView itid, odid;
    Context context;
    FloatingActionButton btnUpdate, btnDelete;
    String odrId,ItemId,itmQty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_oder_details);

        odid = findViewById(R.id.viewOderId);
        itid = findViewById(R.id.viewItemId);
        qty = findViewById(R.id.etQtyOd);
        btnUpdate = findViewById(R.id.btnOderUpdate);
        btnDelete = findViewById(R.id.btnoderdelete);

        getIntentOderDetails();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOder();
            }
        });

    }

    void getIntentOderDetails() {
        if (getIntent().hasExtra("odrId")&&getIntent().hasExtra("itemId")&&getIntent().hasExtra("qty")){
            odrId = getIntent().getStringExtra("odrId");
            ItemId =getIntent().getStringExtra("itemId");
            itmQty =getIntent().getStringExtra("qty");
            odid.setText(odrId);
            itid.setText(ItemId);
            qty.setText(itmQty);
        }else {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateOder(){
        DBHelper db = new DBHelper(UpdateOderDetailsActivity.this);
        itmQty = qty.getText().toString().trim();
        odrId = odid.getText().toString().trim();
        long res = db.updateOderDetails(odrId,itmQty);
        if(res > 0){
            Toast.makeText(this,"Successfully updated!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateOderDetailsActivity.this,ViewOdersActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Sorry, Try again!!",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + odrId + " ?");
        builder.setMessage("Are you want to delete " + odrId + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper db = new DBHelper(UpdateOderDetailsActivity.this);
                long val = db.deleteOder(odrId);
                if (val == -1) {
                    Toast.makeText(context, "Faild to Delete!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateOderDetailsActivity.this, ViewOdersActivity.class);
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