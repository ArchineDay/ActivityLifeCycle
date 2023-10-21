package com.example.activityjump;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Button button2 = findViewById(R.id.button2);//回activity1的按钮

        button2.setOnClickListener(this);


        Button buttonToFragment1 = findViewById(R.id.buttonToFragment1);
        Button buttonToFragment2 = findViewById(R.id.buttonToFragment2);

        buttonToFragment1.setOnClickListener(this);
        buttonToFragment2.setOnClickListener(this);

        Toast.makeText(this, "activity 2 onCreate", Toast.LENGTH_SHORT).show();
        Log.d("activity 1", "activity 2 onCreate");

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
    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.button2) {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.buttonToFragment1) {
            FirstFragment firstFragment = new FirstFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
        }else if (v.getId()==R.id.buttonToFragment2){
            SecondFragment secondFragment = new SecondFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, secondFragment).commit();
        }

    }

}