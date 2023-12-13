package com.example.activityjump.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.example.activityjump.R;
import com.example.activityjump.utils.CacheDataManager;

public class SwipeActivity extends AppCompatActivity implements View.OnClickListener{
    TextView cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

         cache = findViewById(R.id.cache);
    }

    /**
     * 创建Handler
     * 接收消息
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(SwipeActivity.this, "清理完成", Toast.LENGTH_SHORT).show();

                    try {
                        cache.setText(CacheDataManager.getTotalCacheSize(SwipeActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buttonToSwipe){

        }
    }

    /**
     * 创建内部类，清除缓存
     */
    class clearCache implements Runnable {
        @Override
        public void run() {
            try {
                CacheDataManager.clearAllCache(SwipeActivity.this);

                Thread.sleep(1000);

                if (CacheDataManager.getTotalCacheSize(SwipeActivity.this).startsWith("0")) {

                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }
    }

}