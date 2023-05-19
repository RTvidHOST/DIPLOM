package com.example.diplom;

import static com.example.diplom.DBHelper.BALANCE_MONEY;
import static com.example.diplom.DBHelper.BALANCE_USER;
import static com.example.diplom.DBHelper.BASKET_ID;
import static com.example.diplom.DBHelper.BASKET_MUSIC;
import static com.example.diplom.DBHelper.BASKET_PRICE;
import static com.example.diplom.DBHelper.BASKET_USER;
import static com.example.diplom.DBHelper.MUSIC_AVATAR;
import static com.example.diplom.DBHelper.MUSIC_FIRM;
import static com.example.diplom.DBHelper.MUSIC_ID;
import static com.example.diplom.DBHelper.MUSIC_MODEL;
import static com.example.diplom.DBHelper.MUSIC_NAME;
import static com.example.diplom.DBHelper.MUSIC_PRICE;
import static com.example.diplom.DBHelper.MUSIC_TYPE;
import static com.example.diplom.DBHelper.TABLE_BASKET;
import static com.example.diplom.DBHelper.TABLE_MUSIC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class BasketUser extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    TextView balance;
    ImageButton add, refresh, back;
    MyAdapterBasket myAdapter;

    public static String balance1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_user);

        dbHelper = new DBHelper(this);
        findID();
        displayData();
        displayBalance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        balance1 = balance.getText().toString();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addBalance.class);
                startActivity(intent);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketUser.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("Range")
    private void displayBalance() {
        DBHelper dbHelper = new DBHelper(this);
        sqLiteDatabase = dbHelper.getReadableDatabase();;
        String query = "select balanceMoney from balance where balanceUser = " + "'" +loginActivity.user5 + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            balance.setText(String.valueOf(count));
            cursor.close();
        }
    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select basketId, basketMusic, basketPrice from basket where basketUser = " + "'" + loginActivity.user5 + "'", null);
        ArrayList<Modelbasket>modelbaskets = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String basketMusic = cursor.getString(1);
            String basketPrice = cursor.getString(2);
            modelbaskets.add(new Modelbasket(id, basketMusic, basketPrice));
        }
        cursor.close();
        myAdapter = new MyAdapterBasket(R.layout.singledatabasket, this, modelbaskets, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findID() {
        recyclerView = findViewById(R.id.rv10);
        balance = findViewById(R.id.balanceText);
        add = findViewById(R.id.balanceBTN);
        refresh = findViewById(R.id.refreshBTN);
        back = findViewById(R.id.btn_backBacket);
    }
}