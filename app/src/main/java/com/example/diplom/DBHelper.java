package com.example.diplom;

import static com.example.diplom.addBalance.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "diplom";
    public static final String TABLE_USERS = "usersTableData";
    public static final String KEY_ID= "IdUser";
    public static final String KEY_LOGIN = "loginUser";
    public static final String KEY_PASSWORD = "passwordUser";

    public static final String TABLE_MUSIC = "music";
    public static final String MUSIC_ID = "id";
    public static final String MUSIC_AVATAR = "avatar";
    public static final String MUSIC_TYPE = "type";
    public static final String MUSIC_NAME = "name";
    public static final String MUSIC_MODEL = "model";
    public static final String MUSIC_FIRM = "firm";
    public static final String MUSIC_PRICE = "price";

    public static final String TABLE_ADMIN = "admin";
    public static final String ADMIN_ID = "adminId";
    public static final String ADMIN_LOGIN = "login";
    public static final String ADMIN_PASSWORD = "password";

    public static final String TABLE_BALANCE = "balance";
    public static final String BALANCE_ID = "balanceID";
    public static final String BALANCE_USER = "balanceUser";
    public static final String BALANCE_MONEY = "balanceMoney";

    public static final String TABLE_BASKET = "basket";
    public static final String BASKET_ID = "basketId";
    public static final String BASKET_USER = "basketUser";
    public static final String BASKET_MUSIC = "basketMusic";
    public static final String BASKET_PRICE = "basketPrice";

    public static final String TABLE_SALE = "sale";
    public static final String SALE_ID = "saleId";
    public static final String SALE_USER = "saleUser";
    public static final String SALE_PRODUCT = "saleProduct";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + "(" + KEY_ID + "integer primary key," + KEY_LOGIN + " text," + KEY_PASSWORD + " text" + ")");

        db.execSQL("create table " + TABLE_MUSIC + "(" + MUSIC_ID
                + " integer primary key," + MUSIC_AVATAR + " blob," + MUSIC_TYPE + " text," + MUSIC_NAME + " text,"
                + MUSIC_MODEL + " text," + MUSIC_FIRM + " text," + MUSIC_PRICE + " int" + ")");

        db.execSQL("create table " + TABLE_ADMIN + "(" + ADMIN_ID + "ineger primary key," + ADMIN_LOGIN + " text," +
                ADMIN_PASSWORD + " text" + ")");
        db.execSQL("insert into " + TABLE_ADMIN + "(" +  ADMIN_LOGIN + ", " + ADMIN_PASSWORD + ")" + " values ('admin', 'admin')");

        db.execSQL("create table " + TABLE_BALANCE + "(" + BALANCE_ID + "ineger primary key," + BALANCE_USER + " text," +
                BALANCE_MONEY + " int" + ")");

        db.execSQL("create table " + TABLE_BASKET + "(" + BASKET_ID + " integer primary key," +
                BASKET_USER + " text," + BASKET_MUSIC + " text," + BASKET_PRICE + " int" + ")");

        db.execSQL("create table " + TABLE_SALE + "(" + SALE_ID + " integer primary key," +
                SALE_USER + " text," + SALE_PRODUCT + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USERS);
        db.execSQL("drop table if exists " + TABLE_ADMIN);
        db.execSQL("drop table if exists " + TABLE_MUSIC);
        db.execSQL("drop table if exists " + TABLE_BALANCE);
        db.execSQL("drop table if exists " + TABLE_BASKET);
        db.execSQL("drop table if exists " + TABLE_SALE);

        onCreate(db);
    }

    public void updateBalance(int countToAdd) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String moneyMinus = "update balance set balanceMoney = balanceMoney + " + addBalance.countToAdd +
                " where balanceUser = " + "'" + loginActivity.user5 + "'";
        sqLiteDatabase.execSQL(moneyMinus);;
    }

    public Boolean insertUser(String loginUser, String passwordUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("loginUser", loginUser);
        contentValues.put("passwordUser", passwordUser);
        long result = sqLiteDatabase.insert("usersTableData", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean addBalance(String balanceUser, String balanceMoney){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("balanceUser", balanceUser);
        contentValues.put("balanceMoney", balanceMoney);
        long result = sqLiteDatabase.insert("balance", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }
    public boolean checkusername(String loginUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from usersTableData where loginUser = ?", new String[]{loginUser});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String loginUser, String passwordUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from usersTableData where loginUser = ? and passwordUser = ?", new String[] {loginUser, passwordUser});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkadminpassword(String admin, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from admin where login = ? and password = ?", new String[] {admin, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
