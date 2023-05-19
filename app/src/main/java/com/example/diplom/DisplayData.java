package com.example.diplom;

import static com.example.diplom.DBHelper.MUSIC_FIRM;
import static com.example.diplom.DBHelper.MUSIC_AVATAR;
import static com.example.diplom.DBHelper.MUSIC_ID;
import static com.example.diplom.DBHelper.MUSIC_MODEL;
import static com.example.diplom.DBHelper.MUSIC_NAME;
import static com.example.diplom.DBHelper.MUSIC_PRICE;
import static com.example.diplom.DBHelper.MUSIC_TYPE;
import static com.example.diplom.DBHelper.TABLE_MUSIC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class DisplayData extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Button btn_back, btn_filt, btn_user, btn_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dbHelper = new DBHelper(this);
        findID();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        btn_filt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtData();
                btn_filt.setVisibility(View.GONE);
                btn_back.setVisibility(View.VISIBLE);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
                btn_back.setVisibility(View.GONE);
                btn_filt.setVisibility(View.VISIBLE);
            }
        });
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayUsersData.class);
                startActivity(intent);
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), orderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void filtData() {
        EditText filt = (EditText) findViewById(R.id.filt);
        filt.setInputType(InputType.TYPE_CLASS_TEXT);
        String fl = filt.getText().toString();

        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + MUSIC_ID + ", " + MUSIC_AVATAR + ", " + MUSIC_NAME + ", " + MUSIC_PRICE + ", " + MUSIC_FIRM + ", " + MUSIC_MODEL + ", " + MUSIC_TYPE + " from "+ TABLE_MUSIC + " where " + MUSIC_TYPE + " = " + "'"  + fl + "'" +" ", null);
        ArrayList<Model>models = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            byte[]avatar = cursor.getBlob(1);
            String name = cursor.getString(2);
            String price = cursor.getString(3);
            String firm = cursor.getString(4);
            String model = cursor.getString(5);
            String type = cursor.getString(6);
            models.add(new Model(id, avatar, name, price, firm, model, type));
        }
        cursor.close();
        myAdapter = new MyAdapter(R.layout.singledata, this, models, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + MUSIC_ID + ", " + MUSIC_AVATAR + ", " + MUSIC_NAME + ", " + MUSIC_PRICE + ", " + MUSIC_FIRM + ", " + MUSIC_MODEL + ", " + MUSIC_TYPE + " from "+ TABLE_MUSIC + " ", null);
        ArrayList<Model>models = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            byte[]avatar = cursor.getBlob(1);
            String name = cursor.getString(2);
            String price = cursor.getString(3);
            String firm = cursor.getString(4);
            String model = cursor.getString(5);
            String type = cursor.getString(6);
            models.add(new Model(id, avatar, name, price, firm, model, type));
        }
        cursor.close();
        myAdapter = new MyAdapter(R.layout.singledata, this, models, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findID() {
        recyclerView = findViewById(R.id.rv);
        btn_user = (Button)findViewById(R.id.btn_user);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_filt = (Button)findViewById(R.id.btn_filt);
        btn_order = (Button)findViewById(R.id.btn_order);
    }
}