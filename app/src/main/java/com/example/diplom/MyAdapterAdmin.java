package com.example.diplom;

import static com.example.diplom.DBHelper.TABLE_MUSIC;
import static com.example.diplom.DBHelper.TABLE_USERS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterAdmin extends RecyclerView.Adapter<MyAdapterAdmin.PostViewHolder> {
    int singledata;
    Context context;
    ArrayList<Model1> modelArrayList;
    SQLiteDatabase sqLiteDatabase;

    public MyAdapterAdmin(int singledata, Context context, ArrayList<Model1> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.singledata = singledata;
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public MyAdapterAdmin.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata1, null);
        return new MyAdapterAdmin.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterAdmin.PostViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Model1 model1 = modelArrayList.get(position);

        holder.txtlogin.setText(model1.getLogin());
        holder.txtpassword.setText(model1.getPassword());

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context);
                sqLiteDatabase = dbHelper.getReadableDatabase();
                long recdelete = sqLiteDatabase.delete(TABLE_USERS, "loginUser = " + "'" + model1.getLogin() + "'", null);
                if (recdelete !=1){
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
        TextView txtlogin, txtpassword;
        ImageButton btn_delete;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txtlogin = (TextView)itemView.findViewById(R.id.txt_login);
            txtpassword = (TextView)itemView.findViewById(R.id.txt_password);
            btn_delete = (ImageButton)itemView.findViewById(R.id.btn_delete);
        }
    }
}
