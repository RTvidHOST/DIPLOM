package com.example.diplom;

import static com.example.diplom.DBHelper.TABLE_MUSIC;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PostViewHolder> {
    int singledata;
    Context context;
    ArrayList<Model>modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    public MyAdapter(int singledata, Context context, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.singledata = singledata;
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public MyAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.PostViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Model model = modelArrayList.get(position);
        byte[]image = model.getProavatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        holder.imageavatar.setImageBitmap(bitmap);
        holder.txtname.setText(model.getUsername());
        holder.txtprice.setText(model.getPrice());
        holder.txtfirm.setText(model.getFirm());
        holder.txtmodel.setText(model.getModel());
        holder.txttype.setText(model.getType());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", model.getId());
                bundle.putByteArray("avatar", model.getProavatar());
                bundle.putString("name", model.getUsername());
                bundle.putString("firm", model.getFirm());
                bundle.putString("model", model.getModel());
                bundle.putString("price", model.getPrice());
                bundle.putString("type", model.getType());
                Intent intent = new Intent(context, AddProduct.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context);
                sqLiteDatabase = dbHelper.getReadableDatabase();
                long recdelete = sqLiteDatabase.delete(TABLE_MUSIC, "id = " + model.getId(), null);
                if (recdelete != 1){
                    Toast.makeText(context, "date deleted", Toast.LENGTH_SHORT).show();
                    modelArrayList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageavatar;
        TextView txtname, txtprice, txtfirm, txtmodel, txttype;
        ImageButton edit, delete;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageavatar = (ImageView)itemView.findViewById(R.id.viewavatar);
            txtname = (TextView)itemView.findViewById(R.id.txt_name);
            txtprice = (TextView)itemView.findViewById(R.id.txt_price);
            txtfirm = (TextView)itemView.findViewById(R.id.txt_firm);
            txtmodel = (TextView)itemView.findViewById(R.id.txt_model);
            txttype = (TextView)itemView.findViewById(R.id.txt_type);
            edit = (ImageButton)itemView.findViewById(R.id.edit);
            delete = (ImageButton)itemView.findViewById(R.id.delete);
        }
    }
}
