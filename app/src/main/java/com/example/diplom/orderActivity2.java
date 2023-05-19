package com.example.diplom;

import static com.example.diplom.DBHelper.SALE_ID;
import static com.example.diplom.DBHelper.SALE_PRODUCT;
import static com.example.diplom.DBHelper.SALE_USER;
import static com.example.diplom.DBHelper.TABLE_SALE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class orderActivity2 extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    AdapterOrder2 myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);

        dbHelper = new DBHelper(this);
        findID();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + SALE_ID + ", " + SALE_PRODUCT + " from " + TABLE_SALE + " where " + SALE_USER + " = " + "'" + loginActivity.user5 + "'" + " ", null);
        ArrayList<ModelOrder2>modelOrders2 = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            modelOrders2.add(new ModelOrder2(id, name));
        }
        cursor.close();
        myAdapter = new AdapterOrder2(R.layout.singleorderuser, this, modelOrders2, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findID() {
        recyclerView = findViewById(R.id.rv222);
    }
}