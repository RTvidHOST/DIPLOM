package com.example.diplom;

import static com.example.diplom.DBHelper.KEY_LOGIN;
import static com.example.diplom.DBHelper.KEY_PASSWORD;
import static com.example.diplom.DBHelper.MUSIC_AVATAR;
import static com.example.diplom.DBHelper.MUSIC_FIRM;
import static com.example.diplom.DBHelper.MUSIC_ID;
import static com.example.diplom.DBHelper.MUSIC_MODEL;
import static com.example.diplom.DBHelper.MUSIC_NAME;
import static com.example.diplom.DBHelper.MUSIC_PRICE;
import static com.example.diplom.DBHelper.MUSIC_TYPE;
import static com.example.diplom.DBHelper.TABLE_MUSIC;
import static com.example.diplom.DBHelper.TABLE_USERS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class DisplayUsersData extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapterAdmin myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users_data);

        dbHelper = new DBHelper(this);
        findID();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + KEY_LOGIN + ", " + KEY_PASSWORD  + " from "+ TABLE_USERS + " ", null);
        ArrayList<Model1> models1 = new ArrayList<>();
        while (cursor.moveToNext()){
            String login = cursor.getString(0);
            String password = cursor.getString(1);
            models1.add(new Model1(login, password));
        }
        cursor.close();
        myAdapter = new MyAdapterAdmin(R.layout.singledata1, this, models1, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findID() {
        recyclerView = findViewById(R.id.rv1);
    }
}