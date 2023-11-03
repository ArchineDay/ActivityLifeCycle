package com.example.activityjump.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activityjump.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.InnerHolder> {

    private Context context;
    private List list;

    public RecyclerViewAdapter(Context context, List list) {
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
        RecyclerViewBean bean = (RecyclerViewBean) list.get(position);
        holder.name.setText(bean.name);
        holder.content.setText(bean.content);
//        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView content;
        int position;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            content = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
