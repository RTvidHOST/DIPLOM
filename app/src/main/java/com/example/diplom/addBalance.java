package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class addBalance extends AppCompatActivity {

    ImageButton addBalance, back;
    EditText balance;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public static String countToAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);

        addBalance = (ImageButton)findViewById(R.id.btn_addBalance);
        balance = (EditText)findViewById(R.id.txt_addBalance);
        back = (ImageButton)findViewById(R.id.btn_backBalance);

        addBalanceMethod();
        dbHelper = new DBHelper(this);
    }

    private void addBalanceMethod() {
        addBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countToAdd = balance.getText().toString();
                dbHelper.updateBalance(Integer.parseInt(countToAdd));
                Toast.makeText(addBalance.this, "Баланс пополнен на " + countToAdd, Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketUser.class);
                startActivity(intent);
            }
        });
    }
}