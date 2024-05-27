package com.example.activitylifecycle.download;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileDownloaderManager {

    private static final String TAG = "FileDownloaderManager";

    private static long startTime = 0L;

    public static void download(String url, String dir, String fileName) {
        startTime = System.currentTimeMillis();
        DownloadTask task = new DownloadTask.Builder(url,
                new File(dir))
                .setFilename(fileName)                      // 设置下载文件名，没提供的话先看 response header ，再看 url path(即启用下面那项配置)
                .setFilenameFromResponse(false)                 // 是否使用 response header or url path 作为文件名，此时会忽略指定的文件名，默认false
                .setPassIfAlreadyCompleted(false)                // 如果文件已经下载完成，再次下载时，是否忽略下载，默认为true(忽略)，设为false会从头下载
                .setConnectionCount(4)                          // 需要用几个线程来下载文件，默认根据文件大小确定；如果文件已经 split block，则设置后无效
                .setPreAllocateLength(true)                    // 在获取资源长度后，设置是否需要为文件预分配长度，默认false
                .setMinIntervalMillisCallbackProcess(100)       // 通知调用者的频率，避免anr，默认3000
                .setWifiRequired(false)                         // 是否只允许wifi下载，默认为false
                .setAutoCallbackToUIThread(true)                // 是否在主线程通知调用者，默认为true
                //.setHeaderMapFields(new HashMap<String, List<String>>()) // 设置请求头
                //.addHeader(String key, String value) // 追加请求头
                .setPriority(0)                                             // 设置优先级，默认值是0，值越大下载优先级越高
                .setReadBufferSize(4096)                                    // 设置读取缓存区大小，默认4096
                .setFlushBufferSize(16384)                                  // 设置写入缓存区大小，默认16384
                .setSyncBufferSize(65536)                                   // 写入到文件的缓冲区大小，默认65536
                .setSyncBufferIntervalMillis(2000)                          // 写入文件的最小时间间隔，默认2000
                .build();




        task.enqueue(new DownloadListener() {
            @Override
            public void taskStart(@NonNull DownloadTask task) {
                Log.d(TAG, "taskStart: " + task);
            }


            @Override
            public void connectTrialStart(@NonNull DownloadTask task, @NonNull Map<String, List<String>> requestHeaderFields) {
                Log.d(TAG, "connectTrialStart" + requestHeaderFields);
            }

            @Override
            public void connectTrialEnd(@NonNull DownloadTask task, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                Log.d(TAG, "connectTrialEnd" + responseCode);
            }


            @Override
            public void downloadFromBeginning(@NonNull DownloadTask task, @NonNull BreakpointInfo info, @NonNull ResumeFailedCause cause) {
                Log.d(TAG, "downloadFromBeginning cause:" + cause + " info:" + info);
            }

            @Override
            public void downloadFromBreakpoint(@NonNull DownloadTask task, @NonNull BreakpointInfo info) {
                Log.d(TAG, "downloadFromBreakpoint info:" + info);
            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
                Log.d(TAG, "connectStart");
            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                Log.d(TAG, "connectEnd");
            }

            @Override
            public void fetchStart(@NonNull DownloadTask task, int blockIndex, long contentLength) {
                Log.d(TAG, "fetchStart contentLength:" + contentLength + " blockIndex:" + blockIndex);
            }

            @Override
            public void fetchProgress(@NonNull DownloadTask task, int blockIndex, long increaseBytes) {
                Log.d(TAG, "fetchProgress increaseBytes:" + increaseBytes + " blockIndex:" + blockIndex);
            }

            @Override
            public void fetchEnd(@NonNull DownloadTask task, int blockIndex, long contentLength) {
                Log.d(TAG, "fetchEnd contentLength:" + contentLength + " blockIndex:" + blockIndex);
            }


            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {
                Log.d(TAG, "taskEnd cause:" + cause + " realCause:" + realCause);
            }
        });
    }
}
