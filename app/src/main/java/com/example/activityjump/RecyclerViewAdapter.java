package com.example.activityjump;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.InnerHolder> {

    private Context context;
    private List list;

    public RecyclerViewAdapter(Context context,List list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建View,并且用LayoutInflater把xml布局转换成View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        InnerHolder holder = new InnerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.InnerHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText((String) list.get(position));
        holder.address.setText((String) list.get(position));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;
        int position;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.device_name);
            address = itemView.findViewById(R.id.device_address);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
