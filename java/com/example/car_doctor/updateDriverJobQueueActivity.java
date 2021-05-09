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

public class updateDriverJobQueueActivity extends AppCompatActivity {
    TextView jId,oId;
    EditText amt;
    FloatingActionButton btnUpdate,btnDelete;
    Context context;
    String j_id,O_id,u_amt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_driver_job_queue);
        jId =findViewById(R.id.driverJid);
        oId =findViewById(R.id.driverOid);
        amt =findViewById(R.id.driverUpUprice);
        btnDelete =findViewById(R.id.btnDriverDelete);
        btnUpdate =findViewById(R.id.btnDriverUpdate);
        getAndSetIntentData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();
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
        if (getIntent().hasExtra("jobId") && getIntent().hasExtra("oderId") && getIntent().hasExtra("unitPrice")) {
            //getting data
            j_id = getIntent().getStringExtra("jobId");
            O_id = getIntent().getStringExtra("oderId");
            u_amt = getIntent().getStringExtra("unitPrice");

            //setting data
            jId.setText(j_id);
            oId.setText(O_id);
            amt.setText(u_amt);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateItem(){
        DBHelper myDB = new DBHelper(updateDriverJobQueueActivity.this);

        j_id = jId.getText().toString().trim();
        u_amt = amt.getText().toString().trim();
        long res = myDB.updateDriverOder(j_id,u_amt);
        if (res > 0) {
            Toast.makeText(this, "Successfully Updated!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(updateDriverJobQueueActivity.this, driverJobQueueActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Unsuccessful!", Toast.LENGTH_LONG).show();
        }
    }
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + j_id + " ?");
        builder.setMessage("Are you want to delete " + j_id + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper db = new DBHelper(updateDriverJobQueueActivity.this);
                long val = db.deleteDriverOrder(j_id);
                if (val == -1) {
                    Toast.makeText(context, "Faild to Delete!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(updateDriverJobQueueActivity.this, driverJobQueueActivity.class);
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