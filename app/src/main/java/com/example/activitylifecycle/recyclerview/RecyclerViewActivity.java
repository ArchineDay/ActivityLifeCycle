package com.example.activitylifecycle.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.activitylifecycle.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    List<RecyclerViewBean> beanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);

        for (int i = 0; i < 50; i++) {
            RecyclerViewBean bean = new RecyclerViewBean();
            bean.name = "name" + i;
            bean.content= "content" + i;
            beanList.add(bean);
        }
//        ArrayList<String> arrayList = new ArrayList<>();
//
//        arrayList.add("1");
//        arrayList.add("2");
//        arrayList.add("3");

        recyclerViewAdapter = new RecyclerViewAdapter(beanList);
        recyclerView.setAdapter(recyclerViewAdapter);

        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }
}