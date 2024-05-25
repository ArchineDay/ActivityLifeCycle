package com.example.activitylifecycle.thread;

import android.os.Environment;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadDownload {

    private static final String TAG = "MultiThreadDownload";
    private static final int THREAD_COUNT = 4; // 线程数量
    public static void downloadImage(String url, long length) throws IOException {
        Log.d(TAG, "文件大小" + length + "字节");
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        String dir = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM;
        Log.d("dir", dir);

        RandomAccessFile file = new RandomAccessFile(dir+"/"+"image.jpg", "rw");
        file.setLength(length);

        // 计算每个线程下载的字节数
        long eachThreadSize = length / THREAD_COUNT;
        Log.d("eachThreadSize", eachThreadSize + "字节");

        // 创建线程池并启动线程
        for (int i = 0; i < THREAD_COUNT; i++) {
            long startPos = i * eachThreadSize;
            long endPos = (i == THREAD_COUNT - 1) ? length : (i + 1) * eachThreadSize - 1;
            Log.d(TAG, "startPos: " + startPos + "字节" + "endPos: " + endPos + "字节");

            executorService.execute(new DownloadThread(url, file, startPos, endPos, i));
        }

        shutDownThreadPool(executorService,file,30);

    }
    public static void shutDownThreadPool(ExecutorService threadPool, RandomAccessFile file, long timeout) {

        threadPool.shutdown();

        try {
            if (!threadPool.awaitTermination(timeout, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }else{
                Log.d(TAG, "下载完成");
                file.close();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 下载线程
    private static class DownloadThread implements Runnable {
        private final String url;
        private final RandomAccessFile file;
        private final long startPos;
        private final long endPos;
        private final int threadId;
        private static final Lock lock = new ReentrantLock();

        public DownloadThread(String url, RandomAccessFile file, long startPos, long endPos, int threadId) {
            this.url = url;
            this.file = file;
            this.startPos = startPos;
            this.endPos = endPos;
            this.threadId = threadId;
        }

        public void run() {
            try {
                Log.d("线程", "线程" + threadId + "开始下载");

                // 创建 OkHttp 客户端
                OkHttpClient client = new OkHttpClient();

                // 创建 HTTP 请求，并设置下载范围的头部
                Request request = new Request.Builder()
                        .url(url) // 假设 url 是一个已经定义好的字符串变量
                        .addHeader("Range", "bytes=" + startPos + "-" + endPos) // 设置下载范围
                        .build();

                // 执行 HTTP 请求
                Response response = client.newCall(request).execute();

                // 获取响应体的 BufferedSource
                BufferedSource source = response.body().source();


                lock.lock();

                file.seek(startPos);

                file.write(source.readByteArray());

                lock.unlock();


                Log.d("线程", "线程: " + threadId +"已将 BufferedSource 写入文件的起始位置 " + startPos);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}