package com.example.diplom;

import static com.example.diplom.DBHelper.MUSIC_AVATAR;
import static com.example.diplom.DBHelper.MUSIC_FIRM;
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
import android.widget.ImageButton;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyadapterUser myAdapterUser;
    Button btn_backUser, order;
    ImageButton btn_buyUser, btn_filt_user, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        dbHelper = new DBHelper(this);
        findID();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        btn_buyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketUser.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), orderActivity2.class);
                startActivity(intent);
            }
        });
        btn_filt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtData();
                btn_filt_user.setVisibility(View.GONE);
                btn_backUser.setVisibility(View.VISIBLE);
            }
        });
        btn_backUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
                btn_backUser.setVisibility(View.GONE);
                btn_filt_user.setVisibility(View.VISIBLE);
            }
        });
    }

    private void filtData() {
        EditText filt = (EditText) findViewById(R.id.filt);
        filt.setInputType(InputType.TYPE_CLASS_TEXT);
        String fl = filt.getText().toString();

        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + MUSIC_ID + ", " + MUSIC_AVATAR + ", " + MUSIC_NAME + ", " + MUSIC_PRICE + ", " + MUSIC_FIRM + ", " + MUSIC_MODEL + ", " + MUSIC_TYPE + " from "+ TABLE_MUSIC + " where " + MUSIC_TYPE + " = " + "'"  + fl + "'" +" ", null);
        ArrayList<Modeluser>modelusers = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            byte[]avatar = cursor.getBlob(1);
            String name = cursor.getString(2);
            String price = cursor.getString(3);
            String firm = cursor.getString(4);
            String model = cursor.getString(5);
            String type = cursor.getString(6);
            modelusers.add(new Modeluser(id, avatar, name, price, firm, model, type));
        }
        cursor.close();
        myAdapterUser = new MyadapterUser(R.layout.singledata3, this, modelusers, sqLiteDatabase);
        recyclerView.setAdapter(myAdapterUser);
    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " + MUSIC_ID + ", " + MUSIC_AVATAR + ", " + MUSIC_NAME + ", " + MUSIC_PRICE + ", " + MUSIC_FIRM + ", " + MUSIC_MODEL + ", " + MUSIC_TYPE + " from "+ TABLE_MUSIC + " ", null);
        ArrayList<Modeluser> modelusers = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            byte[]avatar = cursor.getBlob(1);
            String name = cursor.getString(2);
            String price = cursor.getString(3);
            String firm = cursor.getString(4);
            String model = cursor.getString(5);
            String type = cursor.getString(6);
            modelusers.add(new Modeluser(id, avatar, name, price, firm, model, type));
        }
        cursor.close();
        myAdapterUser = new MyadapterUser(R.layout.singledata3, this, modelusers, sqLiteDatabase);
        recyclerView.setAdapter(myAdapterUser);
    }

    private void findID() {
        recyclerView = findViewById(R.id.rv3);
        btn_filt_user = (ImageButton) findViewById(R.id.btn_filt_user);
        btn_backUser = (Button)findViewById(R.id.btn_backUser);
        btn_buyUser = (ImageButton)findViewById(R.id.btn_buyUser);
        exit = (ImageButton)findViewById(R.id.btn_exit);
        order = (Button)findViewById(R.id.btn_order222);
    }
}