package com.example.activitylifecycle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.PathUtils;
import com.example.activitylifecycle.R;
import com.example.activitylifecycle.thread.MultiThreadDownload;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadActivity extends AppCompatActivity {
    private TextView tvDownloadUrl;

    private Button btnDownload;

    //    private String imageUrl = "https://filesamples.com/samples/image/bmp/sample_5184%C3%973456.bmp";
    private String imageUrl = "https://test-trailcamera-media.s3.us-west-2.amazonaws.com/3416/20240522143818/thumb3183.JPG?X-Amz-Security-Token=IQoJb3JpZ2luX2VjECMaCXVzLXdlc3QtMiJGMEQCIFEbOYl%2FyrtIGJ%2F6f%2FEwvJ1inxD35tsb%2Bt%2Fr3LNm5l0wAiAjBVqKLRaMgtk7NlNBPReiOkfMhZVnr5GEFRxEimzYxCr2Agic%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAEaDDY2NzQxMjMzNzIzMiIMgNjJt8uBQvi2BGSnKsoCsFaXAi%2FDofxZh9D5g%2B55pT4wmy0I0hd0oSkKcxzP8xed2SrM51n%2Bf5QC10WwkX6C8IyZbJobgjnA1CUTumRAsNrRex2DhuOojCTSvJig492fsZlous7Yes%2FjlytYmc0CvuhcSVNS36o7L8PHEbTFJbUSm9eSdeZXxqb3i3kK9P%2FfngwXSg49HMivNkTZjX4ImVxIrGqn4B3g4xbYzmx1nl6b2DGNz3kbtoEUikysVNr8gfx8voIPZWlHaTAZ2tfzy11k5HDvJLkxWBmd1h5r9FG7tv5X1a%2FFrXko5NVZVtX1JdDOVBKa8bcdAtdDqzjW3xy12WCHx%2F2Wier0l09qCUeSBhuAIMsfnNQfX281zt1mTGjJNUG99NNWoTZnvXx9M0oEhT4TfVXTl37E%2Fz%2FiJUuXigdjmqz7CsGzywWX49q3xaQugcy1slH3MKqcxbIGOpoB9As6boWQXuYHF1X6KWsLyxmmvXybn%2BwzD%2FfM317UbohC1kldLQCW2LIPyY8b3IQMQJksgsYyKcrLrti9tGjrPj3wUSsnxaGFG%2BLEXuD8mITq8D9Ei5AnQ07%2BmmCAJCp0QjwljZCUjgC3JoUEQIKtMRAiOdf1YNuR8GptHq0j69Nd62yw1HTaWURxmM5ANijcnYmABKJs9cfHEA%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240525T023420Z&X-Amz-SignedHeaders=host&X-Amz-Expires=57597&X-Amz-Credential=ASIAZWZHACJIAQYCKPXZ%2F20240525%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Signature=075d286b47bc4ae8be7a49bc2d8abe849d6c7b237f4d2168daadc3daf5a27581";

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
            //获取并打印链接的headers
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(imageUrl)
//                    .build();
//
//            try {
//                Response response = client.newCall(request).execute();
//                Headers headers = response.headers();
//
//                for (int i = 0; i < headers.size(); i++) {
//                    String name = headers.name(i);
//                    String value = headers.value(i);
//                    Log.d("Response Headers", name + ": " + value);
//                    if(name.contains("Content-Length")){
//                        MultiThreadDownload.downloadImage(imageUrl,value);
//                    }else{
//                        //单线程
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                long fileSize = getFileSize(imageUrl);
                if(fileSize > 0){
                    MultiThreadDownload.downloadImage(imageUrl,fileSize);
                }else{
                    //单线程
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    // 获取文件大小
    private  long getFileSize(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        Log.d("Response Headers", "Content-Length: " + response.body().contentLength());
        return response.body().contentLength();
    }
}