package com.example.activityjump.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.activityjump.R;
import com.example.activityjump.utils.CacheDataManager;

public class SwipeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        cache = findViewById(R.id.cache);
        findViewById(R.id.buttonClearCache).setOnClickListener(this);

        try {
//            cache.setText(String.format(this.getResources().getString(R.string.swipe_cache), "20mb"));
            cache.setText(getString(R.string.url_wifi));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonClearCache) {
            new AlertDialog.Builder(this)
                    .setTitle("清理缓存")
                    .setMessage("确定要清理缓存吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        CacheDataManager.clearAllCache(this);
                        try {
                            cache.setText(String.format(this.getResources().getString(R.string.swipe_cache), CacheDataManager.getTotalCacheSize(this)));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        ToastUtils.showShort("取消清理缓存");
                    })
                    .show();
        }
    }


}