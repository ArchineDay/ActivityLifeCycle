package com.example.activitylifecycle.download;

import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.activitylifecycle.sharedpreferences.DataManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadDownloader {

    private static final String TAG = "MultiThreadDownload";
    private static int threadCount;// 线程数量

    public MultiThreadDownloader(int threadCount) {
        MultiThreadDownloader.threadCount = threadCount;
    }

    public void downloadFile(String url, long length) throws IOException {
        Log.d(TAG, "文件大小" + length + "字节");
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        String dir = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM;
        Log.d("dir", dir);

        String imageName = "image.mov";
        RandomAccessFile file = new RandomAccessFile(dir + "/" + imageName, "rw");
//        file.setLength(length);
        if (file.length() < length && file.length() > 0) {
            //文件不存在或不完整
            long eachThreadSize = length / threadCount;
            for (int i = 0; i < threadCount; i++) {
                long endPoint = DataManager.getInstance().getSourceThread(imageName, i);
                if (endPoint < length) {
                    Log.d(TAG, "线程" + i + "处于断点续传状态" + "endPoint:" + endPoint);
                    long startPos = i * eachThreadSize;
                    long endPos = (i == threadCount - 1) ? length : (i + 1) * eachThreadSize - 1;

                    if (endPoint < endPos) {
                        startPos = endPoint;
                    }
                    Log.d(TAG, "续传线程：" + i + " startPos: " + startPos + "字节" + "endPos: " + endPos + "字节");

                    executorService.execute(new DownloadThread(url, imageName, startPos, endPos, i));
                }
            }
            shutDownThreadPool(executorService, file, 30);
            return;
        }
        long filePointer = file.getFilePointer();
        Log.d("filePointer", String.valueOf(filePointer));

        // 计算每个线程下载的字节数
        long eachThreadSize = length / threadCount;
        Log.d("eachThreadSize", eachThreadSize + "字节");

        // 创建线程池并启动线程
        for (int i = 0; i < threadCount; i++) {
            long startPos = i * eachThreadSize;
            long endPos = (i == threadCount - 1) ? length : (i + 1) * eachThreadSize - 1;
            Log.d(TAG, "startPos: " + startPos + "字节" + "endPos: " + endPos + "字节");

            executorService.execute(new DownloadThread(url, imageName, startPos, endPos, i));
        }

        shutDownThreadPool(executorService, file, 30);

    }

    // 获取url文件大小
    public static long getFileSize(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        Log.d("Response Headers", "Content-Length: " + response.body().contentLength());
        return response.body().contentLength();
    }

    private void shutDownThreadPool(ExecutorService threadPool, RandomAccessFile file, long timeout) {

        threadPool.shutdown();

        try {
            if (!threadPool.awaitTermination(timeout, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            } else {
                Log.d(TAG, "下载完成");
                DataManager.getInstance().clearSourceThread();
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
    private class DownloadThread implements Runnable {
        private final String url;
        private final String imageName;
        private final long startPos;
        private final long endPos;
        private final int threadId;
        private final Lock lock = new ReentrantLock();
        private final CompletableFuture<Void> future = new CompletableFuture<>();

        public DownloadThread(String url, String imageName, long startPos, long endPos, int threadId) {
            this.url = url;
            this.startPos = startPos;
            this.endPos = endPos;
            this.threadId = threadId;
            this.imageName = imageName;
        }

        public void run() {
            Log.d("线程", "线程" + threadId + "开始下载");

            // 创建 OkHttp 客户端
            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new ProgressInterceptor(new ProgressListener() {
                @Override
                public void onProgress(long bytesRead, long contentLength, boolean done) {

//                    Log.d("线程", "线程" + threadId + "正在下载"+"下载进度:" + bytesRead + "/" + contentLength);
                }
            }, startPos, imageName)).build();

            // 创建 HTTP 请求，并设置下载范围的头部
            Request request = new Request.Builder()
                    .url(url) // 假设 url 是一个已经定义好的字符串变量
                    .addHeader("Range", "bytes=" + startPos + "-" + endPos) // 设置下载范围
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    Log.d("线程", "线程" + threadId + "下载失败");
                    future.completeExceptionally(e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                    // 获取响应体的 BufferedSource
                    BufferedSource source = response.body().source();

                    lock.lock();
                    String dir = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM;

                    RandomAccessFile file = new RandomAccessFile(dir + "/" + imageName, "rw");

                    byte[] buffer = new byte[8192]; // 8KB缓冲区
                    int read;
                    long pos = startPos;

                    long lastLogPos = startPos;
                    long logInterval = 500 * 1024; // 500KB

                    while ((read = source.read(buffer)) != -1) {
//                        Log.d("线程", "线程: " + threadId + "正在写入文件");
                        file.seek(pos);
                        file.write(buffer, 0, read);
                        pos += read;

                        // 每 500KB 记录一次下载进度
                        if (pos - lastLogPos >= logInterval) {
                            Log.d("线程", "线程: " + threadId + "已下载: " + pos + " bytes");
                            DataManager.getInstance().saveSourceThread(imageName, threadId, pos);
                            lastLogPos = pos;
                        }
                    }
                    lock.unlock();

                    Log.d("线程", "线程: " + threadId + "已将 BufferedSource 写入文件位置 " + startPos);
                    future.complete(null);
                }
            });

            // 等待当前任务完成
            future.join();
        }
    }

    public class ProgressInterceptor implements Interceptor {

        private ProgressListener progressListener;
        private long startPos;
        private String imageName;

        public ProgressInterceptor(ProgressListener progressListener, long startPos, String imageName) {
            this.progressListener = progressListener;
            this.startPos = startPos;
            this.imageName = imageName;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();

//            Request originalRequest = chain.request();
//            Request newRequest = originalRequest.newBuilder()
//                    .header("Range", "bytes=" + startPos + "-")
//                    .build();
//
//            Response response = chain.proceed(newRequest);
//            response = response.newBuilder().body(new ProgressResponseBody(response.body(), progressListener)).build();
//            ResponseBody responseBody = response.body();
//            if (responseBody != null) {
//                BufferedSource source = responseBody.source();
//                String dir = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM;
//
//                RandomAccessFile raf = new RandomAccessFile(dir + "/" + imageName, "rw");
//                raf.seek(startPos);
//                byte[] buffer = new byte[8192];
//                int read;
//                while ((read = source.read(buffer)) != -1) {
////                    Log.d("下载", "正在写入文件");
//                    raf.write(buffer, 0, read);
//                }
//                Log.d("intercept", "写入文件位置 " + startPos);
//                raf.close();
//            }
//            return response;
        }

    }

    public interface ProgressListener {
        void onProgress(long bytesRead, long contentLength, boolean done);
    }

    private static class ProgressResponseBody extends ResponseBody {
        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(new ProgressSource(responseBody.source()));
            }
            return bufferedSource;
        }

        private class ProgressSource extends ForwardingSource {
//                private long totalBytesRead = 0L;

            ProgressSource(Source source) {
                super(source);
            }

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
//                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                progressListener.onProgress(bytesRead, responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        }
    }
}