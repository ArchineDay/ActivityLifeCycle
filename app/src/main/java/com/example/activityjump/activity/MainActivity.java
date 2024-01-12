package com.example.activityjump.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.activityjump.R;
import com.example.activityjump.recyclerview.RecyclerViewActivity;
import com.example.activityjump.utils.DialogUtil;
import com.example.activityjump.utils.FastBlurUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    View blurView;

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
        blurView = findViewById(R.id.realtimeBlurView);

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
            DialogUtil.tipDialog(MainActivity.this,getWindow());
//            blurView.setVisibility(View.VISIBLE);
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
}