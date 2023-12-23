package com.example.activityjump.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activityjump.R;
import com.example.activityjump.utils.ToastSnackUtil;

public class ChronometerActivity extends AppCompatActivity implements View.OnClickListener{

    Chronometer chronometer;
    Button start,pause,resume,reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        chronometer =findViewById(R.id.chronometer);
        start =findViewById(R.id.start);
        pause =findViewById(R.id.pause);
        resume =findViewById(R.id.resume);
        reset =findViewById(R.id.reset);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            chronometer.start();
        }else if (v.getId() == R.id.pause) {
            chronometer.stop();
        }else if (v.getId() == R.id.resume) {
            chronometer.start();
        }else if (v.getId() == R.id.reset) {
            chronometer.setBase(System.currentTimeMillis());
        }

    }
}