package com.example.activityjump;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bindView();

        Toast.makeText(this, "activity 2 onCreate", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 2 onCreate");

    }

    private void bindView() {
        Button button2 = findViewById(R.id.button2);//回activity1的按钮
        Button buttonToFragment1 = findViewById(R.id.buttonToFragment1);
        Button buttonToFragment2 = findViewById(R.id.buttonToFragment2);
        Button buttonToDialog = findViewById(R.id.buttonToDialog);
        Button buttonToRecyclerView = findViewById(R.id.buttonToRecyclerview);

        button2.setOnClickListener(this);
        buttonToFragment1.setOnClickListener(this);
        buttonToFragment2.setOnClickListener(this);
        buttonToDialog.setOnClickListener(this);
        buttonToRecyclerView.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "activity 2 onStart", Toast.LENGTH_SHORT).show();
        Log.d("activity 2", "activity 2 onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "activity 2 onResume", Toast.LENGTH_SHORT).show();
        Log.d("activity 2", "activity 2 onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "activity 2 onStop", Toast.LENGTH_SHORT).show();
        Log.d("activity 2", "activity 2 onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "activity 2 onPause", Toast.LENGTH_SHORT).show();
        Log.d("activity 2", "activity 2 onPause");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "activity 2 onDestroy", Toast.LENGTH_SHORT).show();
        Log.d("activity 2", "activity 2 onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "activity 2 onRestart", Toast.LENGTH_SHORT).show();
        Log.d("activity 2", "activity 2 onRestart");
    }

    //
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button2) {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.buttonToFragment1) {
            FirstFragment firstFragment = new FirstFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
        } else if (v.getId() == R.id.buttonToFragment2) {
            SecondFragment secondFragment = new SecondFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, secondFragment).commit();
        } else if (v.getId() == R.id.buttonToDialog) {
            tipDialog();
        }else if (v.getId()==R.id.buttonToRecyclerview){
            Intent intent = new Intent();
            intent.setClass(this, RecyclerViewActivity.class);
            startActivity(intent);
        }


    }

    /**
     * 提示对话框
     */
    public void tipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        builder.setTitle("提示：");
        builder.setMessage("这是一个普通对话框，");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失

        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SecondActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SecondActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置中立按钮
        builder.setNeutralButton("保密", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SecondActivity.this, "你选择了中立", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();      //创建AlertDialog对象
        //对话框显示的监听事件
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Log.e(TAG, "对话框显示了");
            }
        });
        //对话框消失的监听事件
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.e(TAG, "对话框消失了");
            }
        });
        dialog.show();                              //显示对话框
    }
}