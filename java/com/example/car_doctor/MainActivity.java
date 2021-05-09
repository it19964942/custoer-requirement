package com.example.car_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button btnMovetoSignUp, login, driver, sellerLogIn;
    EditText user, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMovetoSignUp = findViewById(R.id.cusSignUp);
        sellerLogIn = findViewById(R.id.sellerLogIn);
        login = findViewById(R.id.btnLogin);
        user = findViewById(R.id.txtuser);
        Password = findViewById(R.id.txtlpass);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().trim().equalsIgnoreCase("Prasa") && Password.getText().toString().trim().equalsIgnoreCase("123")) {
                    Intent i = new Intent(MainActivity.this, driverHomeActivity.class);
                    startActivity(i);
                }else if(user.getText().toString().trim().equalsIgnoreCase("Savindu") && Password.getText().toString().trim().equalsIgnoreCase("123")){
                    Intent i = new Intent(MainActivity.this, AdminHomeActivity.class);
                    startActivity(i);
                }else if(user.getText().toString().trim().equalsIgnoreCase("Jonty") && Password.getText().toString().trim().equalsIgnoreCase("123")){
                    Intent i = new Intent(MainActivity.this, CustomerHomeActivity.class);
                    startActivity(i);
                }else if(user.getText().toString().trim().equalsIgnoreCase("Maleesha") && Password.getText().toString().trim().equalsIgnoreCase("123")){
                    Intent i = new Intent(MainActivity.this, ItemActivity.class);
                    startActivity(i);
                }else{
                    displaymsg();
                }
            }
        });

        sellerLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AdminHomeActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ItemActivity.class);
                startActivity(intent);
            }
        });
        btnMovetoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerHomeActivity.class);
                startActivity(intent);
            }
        });

        driver =findViewById(R.id.driverLogIn);
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,driverHomeActivity.class);
                startActivity(i);
            }
        });
    }
    void displaymsg(){
        Toast.makeText(this,"Invalid Username or password!",Toast.LENGTH_SHORT).show();
    }
}