package com.example.diplom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterOrder2 extends RecyclerView.Adapter<AdapterOrder2.PostViewHolder>  {

    int singledata;
    Context context;
    ArrayList<ModelOrder2> modelOrders2;
    SQLiteDatabase sqLiteDatabase;

    public AdapterOrder2(int singledata, Context context, ArrayList<ModelOrder2> modelOrders2, SQLiteDatabase sqLiteDatabase) {
        this.singledata = singledata;
        this.context = context;
        this.modelOrders2 = modelOrders2;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public AdapterOrder2.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singleorderuser, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrder2.PostViewHolder holder, int position) {
        final ModelOrder2 modelOrder2 = modelOrders2.get(position);
        holder.name.setText(modelOrder2.getName());
    }

    @Override
    public int getItemCount() {
        return modelOrders2.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txt_name222);
        }
    }
}
