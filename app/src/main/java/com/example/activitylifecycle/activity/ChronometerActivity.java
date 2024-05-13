package com.example.activitylifecycle.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitylifecycle.R;
import com.example.activitylifecycle.utils.ToastSnackUtil;

public class ChronometerActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    private long duration = 0;

    private long liveSec = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        chronometer = findViewById(R.id.chronometer);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.resume).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);


//        chronometer.setOnChronometerTickListener(chronometer -> {
//            //设置倒计时
//            if (liveSec > 0) {
//                liveSec--;
//                Log.d("liveSec", String.valueOf(liveSec));
//            }
//        });


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.start) {
            chronometer.setOnChronometerTickListener(chronometer -> {
            });
            chronometer.setBase(SystemClock.elapsedRealtime());


            chronometer.setOnChronometerTickListener(chronometer -> {
                //设置倒计时
                if (liveSec > 0) {
                    liveSec--;
                    Log.d("liveSecStart", String.valueOf(liveSec));
                }
            });
            chronometer.start();

        } else if (v.getId() == R.id.pause) {
            duration = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
        } else if (v.getId() == R.id.resume) {
            chronometer.setOnChronometerTickListener(chronometer -> {
            });

            chronometer.setBase(SystemClock.elapsedRealtime() - duration);
            chronometer.start();

            chronometer.setOnChronometerTickListener(chronometer -> {
                //设置倒计时
                if (liveSec > 0) {
                    liveSec--;
                    Log.d("liveSecResume", String.valueOf(liveSec));
                }
            });
        } else if (v.getId() == R.id.reset) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.stop();
            liveSec =2000;
        }

    }
}