package com.example.activityjump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);

        button1.setOnClickListener(this);

        Toast.makeText(this, "activity 1 onCreate", Toast.LENGTH_SHORT).show();
        //Snackbar.make(button1,"activity 1 onCreate",Snackbar.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "activity 1 onStart", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "activity 1 onResume", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "activity 1 onStop", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "activity 1 onPause", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 1 onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"activity 1 onDestroy",Toast.LENGTH_SHORT).show();
        Log.d("activity 1","activity 1 onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "activity 1 onRestart", Toast.LENGTH_SHORT).show();
        Log.d("activity 1","activity 1 onRestart");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);
    }







}