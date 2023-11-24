package com.example.activityjump.progress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activityjump.R;

public class ProgressbarActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ProgressBar progressBarHorizontal;
    private ProgressBar progressBarLarge;

    private TextView tvHorizontalProgressbar;
    private TextView tvLargeProgressbar;

    private int mProgress = 0;

    private Handler mHandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        progressBar = findViewById(R.id.progressbar);
        progressBarHorizontal =findViewById(R.id.progressbarHorizontal);
        tvHorizontalProgressbar=findViewById(R.id.tv_progress);
        tvLargeProgressbar=findViewById(R.id.tv_progress_large);
        progressBarLarge =findViewById(R.id.progressbarLarge);
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull android.os.Message msg) {
                super.handleMessage(msg);
                if (0x111 == msg.what) {
                    progressBar.setProgress(mProgress);
                    progressBarHorizontal.setProgress(mProgress);
                    tvHorizontalProgressbar.setText("当前进度为：" + mProgress + "%");
                    tvLargeProgressbar.setText( mProgress + "%");
                    progressBarLarge.setProgress(mProgress);
                } else {
//                    Toast.makeText(ProgressbarActivity.this, "耗时操作已完成", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //实时获取耗时操作完成的百分比，直到耗时操作结束
                    mProgress = doWork();
                    Message m = new Message();//实例化一个消息对象，用于更新进度条进度
                    m.what=0x111;//设置消息代码

                    if (mProgress > 100) {
//                        m.what = 0x110;//进度完成的消息代码
                        break;
                    }
                    mHandler.sendMessage(m);//发送消息
                }
            }

            private int doWork() {   //用来模拟一个耗时操作
                mProgress += Math.random() * 10;//确定当前进度，随机确定一个进度
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mProgress;
            }
        }).start();
    }
}