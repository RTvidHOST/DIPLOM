package com.example.diplom;

import static com.example.diplom.DBHelper.BASKET_MUSIC;
import static com.example.diplom.DBHelper.BASKET_PRICE;
import static com.example.diplom.DBHelper.BASKET_USER;
import static com.example.diplom.DBHelper.TABLE_BASKET;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyadapterUser extends RecyclerView.Adapter<MyadapterUser.ViewHolder> {
    int singledata3;
    Context context;
    ArrayList<Modeluser>modelArrayList1;
    SQLiteDatabase sqLiteDatabase;

    public MyadapterUser(int singledata3, Context context, ArrayList<Modeluser> modelArrayList1, SQLiteDatabase sqLiteDatabase) {
        this.singledata3 = singledata3;
        this.context = context;
        this.modelArrayList1 = modelArrayList1;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public MyadapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata3, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyadapterUser.ViewHolder holder, int position) {
        final Modeluser modeluser = modelArrayList1.get(position);
        byte[]image = modeluser.getProavatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        holder.imageavatar1.setImageBitmap(bitmap);
        holder.txtname1.setText(modeluser.getUsername());
        holder.txtprice1.setText(modeluser.getPrice());
        holder.txtfirm1.setText(modeluser.getFirm());
        holder.txtmodel1.setText(modeluser.getModel());
        holder.txttype1.setText(modeluser.getType());

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add = "insert into " + TABLE_BASKET + " (" + BASKET_USER + ", " + BASKET_MUSIC + ", " + BASKET_PRICE + ")" +
                        " values (" + "'" + loginActivity.user5 + "'" + ", " + "'" + holder.txtname1.getText().toString() + "'" + ", " + holder.txtprice1.getText().toString() + ")";
                sqLiteDatabase.execSQL(add);
                Toast.makeText(context, "Product add", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageavatar1;
        TextView txtname1, txtprice1, txtfirm1, txtmodel1, txttype1;
        ImageButton buy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageavatar1 = (ImageView)itemView.findViewById(R.id.viewavatar1);
            txtname1 = (TextView)itemView.findViewById(R.id.txt_name1);
            txtprice1 = (TextView)itemView.findViewById(R.id.txt_price1);
            txtfirm1 = (TextView)itemView.findViewById(R.id.txt_firm1);
            txtmodel1 = (TextView)itemView.findViewById(R.id.txt_model1);
            txttype1 = (TextView)itemView.findViewById(R.id.txt_type1);
            buy = (ImageButton)itemView.findViewById(R.id.btn_buy);
        }
    }
}
