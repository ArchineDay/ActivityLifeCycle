package com.example.activityjump.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ToastSnackUtil {
    private Context context;

    public ToastSnackUtil(Context context) {
        this.context = context;
    }

    public ToastSnackUtil() {
    }

    public void showShortToast(int resId) {
        Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    public void showShortToast(String content) {
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    public void showLongToast(int resId) {
        Toast toast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    public void showLongToast(String content) {
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
//        toast.setGravity(Gravity.TOP, 0, ToolUtil.dip2px(context, 80));
        toast.show();
    }


    public  void showShortSnack(View snackView, String text) {
        Snackbar snackbar = Snackbar.make(snackView, text, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(snackbarView.getLayoutParams().width, snackbarView.getLayoutParams().height);
        params.gravity = Gravity.CENTER;
        snackbarView.setLayoutParams(params);
        snackbar.show();
    }

    public  void showLongSnack(View snackView, String text) {
        Snackbar snackbar = Snackbar.make(snackView, text, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(snackbarView.getLayoutParams().width, snackbarView.getLayoutParams().height);
        params.gravity = Gravity.CENTER;
        snackbarView.setLayoutParams(params);
        snackbar.show();
    }
}
