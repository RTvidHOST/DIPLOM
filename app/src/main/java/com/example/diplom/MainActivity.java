package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton reg, signIn;
    EditText username, password, repassword;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);
        reg = (ImageButton)findViewById(R.id.reg);
        signIn = (ImageButton)findViewById(R.id.btn_signin);
        dbHelper = new DBHelper(this);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = dbHelper.checkusername(user);
                        if (checkuser == false){
                            Boolean insert = dbHelper.insertUser(user, pass);
                            if (insert == true){
                                Toast.makeText(MainActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                dbHelper.addBalance(user, "0");
                                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(intent);
            }
        });
    }
}