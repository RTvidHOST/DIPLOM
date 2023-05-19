package com.example.diplom;

import static com.example.diplom.DBHelper.TABLE_SALE;

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

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.PostViewHolder> {

    int singledata;
    Context context;
    ArrayList<ModelOrder>modelOrders;
    SQLiteDatabase sqLiteDatabase;

    public AdapterOrder(int singledata, Context context, ArrayList<ModelOrder> modelOrders, SQLiteDatabase sqLiteDatabase) {
        this.singledata = singledata;
        this.context = context;
        this.modelOrders = modelOrders;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public AdapterOrder.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sngledataorder, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrder.PostViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ModelOrder modelOrder = modelOrders.get(position);
        holder.name.setText(modelOrder.getName());
        holder.username.setText(modelOrder.getUsername());

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context);
                sqLiteDatabase = dbHelper.getReadableDatabase();
                long delete = sqLiteDatabase.delete(TABLE_SALE, "saleId = " + modelOrder.getId(), null);
                if (delete !=1){
                    Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show();
                    modelOrders.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelOrders.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView name, username;
        ImageButton del;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txt_name111);
            username = (TextView)itemView.findViewById(R.id.txt_username111);
            del = (ImageButton)itemView.findViewById(R.id.btn_delOrder);
        }
    }
}
