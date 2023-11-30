package com.example.activityjump.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.activityjump.R;
import com.example.activityjump.utils.ToastSnackUtil;

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

        findViewById(R.id.buttonShortSnack).setOnClickListener(v -> {
            toastSnackUtil.showShortSnack(findViewById(R.id.buttonShortSnack), "short snack");
        });

        findViewById(R.id.buttonLongSnack).setOnClickListener(v -> {
            toastSnackUtil.showLongSnack(findViewById(R.id.buttonLongSnack), "long snack");
        });
    }
}