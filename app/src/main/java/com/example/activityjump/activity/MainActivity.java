package com.example.activityjump.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.activityjump.R;
import com.example.activityjump.recyclerview.RecyclerViewActivity;
import com.example.activityjump.utils.FastBlurUtility;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();

        Toast.makeText(this, "activity 1 onCreate", Toast.LENGTH_SHORT).show();

//        ToastSnackUtil toastSnackUtil = new ToastSnackUtil(this);
//        toastSnackUtil.showShortToast("short toast");
//        ToastSnackUtil.showShortSnack(findViewById(R.id.button1), "short snack");
        Log.d("activity 1", "activity 1 onCreate");
    }

    private void bindView() {
        Button button1 = findViewById(R.id.button1);
        Button buttonToDialog = findViewById(R.id.buttonToDialog);
        Button buttonToRecyclerView = findViewById(R.id.buttonToRecyclerview);
        Button buttonToProgressbar = findViewById(R.id.buttonToProgressbar);
//        Button buttonToMatisse = findViewById(R.id.buttonToMatisse);

        findViewById(R.id.buttonToMatisse).setOnClickListener(this);
        findViewById(R.id.buttonToToastAndSnack).setOnClickListener(this);
        findViewById(R.id.buttonToSwipe).setOnClickListener(this);
        findViewById(R.id.buttonToChronometer).setOnClickListener(this);

        button1.setOnClickListener(this);
        buttonToDialog.setOnClickListener(this);
        buttonToRecyclerView.setOnClickListener(this);
        buttonToProgressbar.setOnClickListener(this);
//        buttonToMatisse.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "activity 1 onStart", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "activity 1 onResume", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(this, "activity 1 onStop", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(this, "activity 1 onPause", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "activity 1 onDestroy", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(this, "activity 1 onRestart", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onRestart");
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button1) {
            //跳转到activity2
            Intent intent = new Intent();
            intent.setClass(this, SecondActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.buttonToDialog) {
            tipDialog();

            Bitmap blurBackgroundDrawer = FastBlurUtility.getBlurBackgroundDrawer(MainActivity.this);
            //blurBackgroundDrawer为模糊后的背景图片
            Window window = getWindow();
            window.setBackgroundDrawable(new BitmapDrawable(MainActivity.this.getResources(), blurBackgroundDrawer));
        } else if (v.getId() == R.id.buttonToRecyclerview) {
            Intent intent = new Intent();
            intent.setClass(this, RecyclerViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.buttonToMatisse) {
            Intent intent = new Intent();
            intent.setClass(this, MatisseActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.buttonToProgressbar) {
            Intent intent = new Intent();
            intent.setClass(this, ProgressbarActivity.class);
            startActivity(intent);
        }else if (v.getId() ==R.id.buttonToToastAndSnack){
            startActivity(new Intent(this,ToastAndSnackActivity.class));
        }else if (v.getId() ==R.id.buttonToSwipe){
            startActivity(new Intent(this,SwipeActivity.class));
        }else if (v.getId() ==R.id.buttonToChronometer){
            startActivity(new Intent(this,ChronometerActivity.class));
        }
    }

    /**
     * 提示对话框
     */
    public void tipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示：");
        builder.setMessage("这是一个普通对话框，");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失

        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置中立按钮
        builder.setNeutralButton("保密", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你选择了中立", Toast.LENGTH_SHORT).show();
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

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.e(TAG, "对话框销毁了");
                // 还原背景颜色
                Window window = getWindow();
                window.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_background_white_round));
            }
        });
        dialog.show();
    }


}