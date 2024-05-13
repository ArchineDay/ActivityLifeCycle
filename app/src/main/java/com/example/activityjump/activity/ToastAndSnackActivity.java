package com.example.activitylifecycle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.activitylifecycle.R;
import com.example.activitylifecycle.utils.ToastSnackUtil;

public class ToastAndSnackActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_and_snack);


        ToastSnackUtil toastSnackUtil = new ToastSnackUtil(this);
        findViewById(R.id.buttonShortToast).setOnClickListener(v -> {
            toastSnackUtil.showShortToast("short toast");
        });

        findViewById(R.id.buttonLongToast).setOnClickListener(v -> {
            toastSnackUtil.showLongToast("long toast");
        });

        findViewById(R.id.shortSnackButton).setOnClickListener(v -> {
            toastSnackUtil.showShortSnack(findViewById(android.R.id.content), "short snack");
        });

        findViewById(R.id.longSnackButton).setOnClickListener(v -> {
            toastSnackUtil.showLongSnack(findViewById(android.R.id.content), "long snack");
        });


    }
}