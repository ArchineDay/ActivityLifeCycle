package com.example.activityjump;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressbarActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private int mProgress = 0;

    private Handler mHandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        progressBar = findViewById(R.id.progressbar);
        mHandler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x111) {
                    progressBar.setProgress(mProgress);
                } else {
                    Toast.makeText(ProgressbarActivity.this, "耗时操作已完成", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        };

        new Thread(new Runnable() {   //通过匿名内部类指定参数，实例化线程对象模拟耗时操作
            @Override
            public void run() {
                while (true) {   //实时获取耗时操作完成的百分比，直到耗时操作结束
                    mProgress = doWork();  //使当前进度等于一个耗时操作，耗时操作的返回值就是完成的进度
                    Message m = new Message();//实例化一个消息对象，用于更新进度条进度
                    if (mProgress < 100) { //判断当前进度是否完成
                        m.what = 0x111;  //设置消息代码，自定义的消息代码，识别不同的消息，一般以0x开头
                        mHandler.sendMessage(m);//通过handler发送消息
                    } else {
                        m.what = 0x110;  //进度完成的消息代码
                        mHandler.sendMessage(m);//发送消息
                        break;//耗时操作完成后退出
                    }
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
        }).start();//开启线程
    }
}