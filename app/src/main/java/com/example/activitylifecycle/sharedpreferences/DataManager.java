package com.example.activitylifecycle.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.activitylifecycle.base.BaseApplication;

public class DataManager {
    private final Context context;
    private static String TAG = "DataManager";

    private DataManager(Context context) {
        this.context = context;
    }

    @SuppressLint("StaticFieldLeak")
    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager(BaseApplication.getInstance());
                }
            }
        }
        return instance;
    }

    private final String SOURCE_THREAD = "SOURCE_THREAD";

    public void saveSourceThread(String key, int threadId, long endPos) {
        Log.d(TAG, "saveSourceThread threadId:" + threadId + " endPos:" + endPos);
        SharedPreferences sdf = context.getSharedPreferences(SOURCE_THREAD, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sdf.edit();
        key = SOURCE_THREAD + key + threadId;
        editor.remove(key).putLong(key, endPos);
        editor.commit();
    }

    public long getSourceThread(String key, int threadId) {
        key = SOURCE_THREAD + key + threadId;
        SharedPreferences sdf = context.getSharedPreferences(SOURCE_THREAD, Context.MODE_PRIVATE);
        if (sdf.contains(key)) {
            long endPos = sdf.getLong(key, 0);
            Log.d(TAG, "getSourceThread threadId:" + threadId);
            return endPos;
        } else {
            return -1;
        }
    }

    public void clearSourceThread() {
        SharedPreferences.Editor editor = context.getSharedPreferences(SOURCE_THREAD, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

}
