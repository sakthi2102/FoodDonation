package com.example.fooddonation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<signup> dataList;

    public MyAdapter(Context context, List<signup> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.reconame.setText(dataList.get(position).getOname());
        holder.recname.setText(dataList.get(position).getName());
        holder.recnumber.setText(dataList.get(position).getNumber());

        holder.recCard.setOnClickListener(view -> {
            Intent intent = new Intent(context, OrphanageDataActivity.class);
            intent.putExtra("name", dataList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("oname", dataList.get(holder.getAdapterPosition()).getOname());
            intent.putExtra("member", dataList.get(holder.getAdapterPosition()).getMember());
            intent.putExtra("number", dataList.get(holder.getAdapterPosition()).getNumber());
            intent.putExtra("email",dataList.get(holder.getAdapterPosition()).getEmail());
            intent.putExtra("address", dataList.get(holder.getAdapterPosition()).getAddress());
            intent.putExtra("city", dataList.get(holder.getAdapterPosition()).getCity());
            intent.putExtra("district", dataList.get(holder.getAdapterPosition()).getDistrict());
            intent.putExtra("state", dataList.get(holder.getAdapterPosition()).getState());
            intent.putExtra("key", dataList.get(holder.getAdapterPosition()).getKey());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(ArrayList<signup> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView reconame, recname, recnumber;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);


        recCard = itemView.findViewById(R.id.recCard);
        recname = itemView.findViewById(R.id.recname);
        reconame = itemView.findViewById(R.id.reconame);
        recnumber = itemView.findViewById(R.id.recnumber);
    }
}