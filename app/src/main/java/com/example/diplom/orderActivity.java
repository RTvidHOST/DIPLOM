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

public class orderActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    AdapterOrder myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        
        dbHelper = new DBHelper(this);
        findID();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + SALE_ID + ", " + SALE_USER + ", " + SALE_PRODUCT + " from " + TABLE_SALE + " ", null);
        ArrayList<ModelOrder>modelOrders = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String name = cursor.getString(2);
            modelOrders.add(new ModelOrder(id, username, name));
        }
        cursor.close();
        myAdapter = new AdapterOrder(R.layout.sngledataorder, this, modelOrders, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findID() {
        recyclerView = findViewById(R.id.rv111);
    }
}