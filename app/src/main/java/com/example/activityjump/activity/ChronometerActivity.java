package com.example.activityjump.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activityjump.R;
import com.example.activityjump.utils.ToastSnackUtil;

public class ChronometerActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    private long duration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        chronometer = findViewById(R.id.chronometer);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.resume).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.start) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        } else if (v.getId() == R.id.pause) {
            duration = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
        } else if (v.getId() == R.id.resume) {
            chronometer.setBase(SystemClock.elapsedRealtime() - duration);
            chronometer.start();
        } else if (v.getId() == R.id.reset) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.stop();
        }

    }
}