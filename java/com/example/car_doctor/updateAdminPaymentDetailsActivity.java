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

public class updateAdminPaymentDetailsActivity extends AppCompatActivity {
    TextView id,type,year,month;
    EditText amt;
    FloatingActionButton btnUpdate,btnDelete;
    String p_id,p_user,p_year,p_month,p_amount;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin_payment_details);
        id = findViewById(R.id.txtAid);
        type = findViewById(R.id.txtAuser);
        year = findViewById(R.id.txtAmonth);
        month = findViewById(R.id.txtAyear);
        amt = findViewById(R.id.txtAAmount);
        btnUpdate = findViewById(R.id.btnAdminUpdate);
        btnDelete = findViewById(R.id.btnAdminDelete);
        getAndSetIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePayment();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("type") && getIntent().hasExtra("year")
                && getIntent().hasExtra("month") && getIntent().hasExtra("amount")) {
            //getting data
            p_id = getIntent().getStringExtra("id");
            p_user = getIntent().getStringExtra("type");
            p_year = getIntent().getStringExtra("year");
            p_month = getIntent().getStringExtra("month");
            p_amount = getIntent().getStringExtra("amount");

            //setting data
            id.setText(p_id);
            type.setText(p_user);
            year.setText(p_year);
            month.setText(p_month);
            amt.setText(p_amount);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    public void updatePayment() {
        DBHelper myDB = new DBHelper(updateAdminPaymentDetailsActivity.this);

        p_id = id.getText().toString().trim();
        p_amount = amt.getText().toString().trim();
        long res = myDB.updatePayment(p_id,p_amount);
        if (res > 0) {
            Toast.makeText(this,"Successfully Updated!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(updateAdminPaymentDetailsActivity.this, AdminHomeActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Unsuccessful!", Toast.LENGTH_LONG).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + p_id + " ?");
        builder.setMessage("Are you want to delete " + p_id + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper db = new DBHelper(updateAdminPaymentDetailsActivity.this);
                long val = db.deletePayment(p_id);
                if (val == -1) {
                    Toast.makeText(context, "Faild to Delete!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(updateAdminPaymentDetailsActivity.this, AdminHomeActivity.class);
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