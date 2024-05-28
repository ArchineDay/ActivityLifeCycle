package com.example.activitylifecycle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.activitylifecycle.R;
import com.example.activitylifecycle.download.MultiThreadDownloader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadActivity extends AppCompatActivity {
    private TextView tvDownloadUrl;

    private Button btnDownload;

    //    private String imageUrl = "https://filesamples.com/samples/image/bmp/sample_5184%C3%973456.bmp";
    private String imageUrl = "http://192.168.5.76:5500/sample_5184%C3%973456.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        tvDownloadUrl = findViewById(R.id.tv_download_url);
        btnDownload = findViewById(R.id.button_download);

        tvDownloadUrl.setText(imageUrl);

//        String dir = PathUtils.getExternalDcimPath();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        btnDownload.setOnClickListener(v -> {
            try {
                long fileSize = MultiThreadDownloader.getFileSize(imageUrl);
                if(fileSize > 0){
                    MultiThreadDownloader downloader = new MultiThreadDownloader(5);
                    downloader.downloadFile(imageUrl,fileSize);
                }else{
                    //单线程
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


//            FileDownloaderManager.download(imageUrl, PathUtils.getExternalDcimPath(), "image.jpg");
        });

    }


}