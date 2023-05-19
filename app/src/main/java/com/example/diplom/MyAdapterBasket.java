package com.example.diplom;

import static android.content.Intent.getIntent;
import static com.example.diplom.DBHelper.SALE_PRODUCT;
import static com.example.diplom.DBHelper.SALE_USER;
import static com.example.diplom.DBHelper.TABLE_BASKET;
import static com.example.diplom.DBHelper.TABLE_MUSIC;
import static com.example.diplom.DBHelper.TABLE_SALE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterBasket extends RecyclerView.Adapter<MyAdapterBasket.PostViewHolder> {
    int singledatabasket;
    Context context;
    ArrayList<Modelbasket>modelbaskets;
    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;
    RecyclerView recyclerView;


    public static int pr;
    public MyAdapterBasket(int singledatabasket, Context context, ArrayList<Modelbasket> modelbaskets, SQLiteDatabase sqLiteDatabase) {
        this.singledatabasket = singledatabasket;
        this.context = context;
        this.modelbaskets = modelbaskets;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public MyAdapterBasket.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledatabasket, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterBasket.PostViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Modelbasket modelbasket = modelbaskets.get(position);

        holder.txtname.setText(modelbasket.getNameBasket());
        holder.txtprice.setText(modelbasket.getPriceBasket());

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr = Integer.parseInt(holder.txtprice.getText().toString());
                int bl = Integer.parseInt(BasketUser.balance1);
                Cursor cursor = sqLiteDatabase.rawQuery("select balanceMoney from balance where balanceUser = " + "'" + loginActivity.user5 + "'", null);
                int count = 0;
                int count1 = 0;
                if (cursor.moveToFirst()){
                    count = cursor.getInt(0);
                }
                Cursor cursor1 = sqLiteDatabase.rawQuery("select price from music where id = " + AddProduct.id, null);
                if (cursor1.moveToFirst()){
                    count1 = cursor1.getInt(0);
                }
                cursor.close();
                if (pr <= bl){
                    DBHelper dbHelper = new DBHelper(context);
                    sqLiteDatabase = dbHelper.getReadableDatabase();
                    long recdelete = sqLiteDatabase.delete(TABLE_BASKET, "basketId = " + modelbasket.getId(), null);
                    if (recdelete != 1){
                        modelbaskets.remove(position);
                        notifyDataSetChanged();
                    }
                    String buyBasket = "insert into " + TABLE_SALE + " (" + SALE_USER + ", " + SALE_PRODUCT + ")" +
                            " values (" + "'" + loginActivity.user5 + "'" + ", " + "'" + holder.txtname.getText().toString() + "'" + ")";
                    sqLiteDatabase.execSQL(buyBasket);
                    String moneyMinus = "update balance set balanceMoney = balanceMoney - " + pr +
                            " where balanceUser = " + "'" + loginActivity.user5 + "'";
                    sqLiteDatabase.execSQL(moneyMinus);
                    Toast.makeText(context, "Товар куплен", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Пополните баланс!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context);
                sqLiteDatabase = dbHelper.getReadableDatabase();
                long recdelete = sqLiteDatabase.delete(TABLE_BASKET, "basketId = " + modelbasket.getId(), null);
                if (recdelete != 1){
                    Toast.makeText(context, "date deleted", Toast.LENGTH_SHORT).show();
                    modelbaskets.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelbaskets.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtprice;
        ImageButton buy, delete;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = (TextView)itemView.findViewById(R.id.txt_name10);
            txtprice = (TextView)itemView.findViewById(R.id.txt_price10);
            buy = (ImageButton)itemView.findViewById(R.id.btn_buyBasket);
            delete = (ImageButton)itemView.findViewById(R.id.btn_delBasket);
        }
    }
}
