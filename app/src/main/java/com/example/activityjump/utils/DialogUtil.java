package com.example.activityjump.utils;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.blankj.utilcode.util.ScreenUtils.getScreenWidth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.activityjump.R;
import com.example.activityjump.activity.MainActivity;

public class DialogUtil{
    public static void tipDialog(Context context,Window window) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示：");
        builder.setMessage("这是一个普通对话框，");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(true);

        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "你点击了确定", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "你点击了取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置中立按钮
        builder.setNeutralButton("中立", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "你选择了中立", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        //创建AlertDialog对象
        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Log.e(TAG, "对话框显示了");
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.e(TAG, "对话框消失了");
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.e(TAG, "对话框销毁了");
                // 还原背景颜色
                window.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_background_white_round));
            }
        });
        dialog.show();

        // 修改对话框宽度
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (getScreenWidth() * 0.8);
        dialog.getWindow().setAttributes(params);


        // 设置背景模糊
        Bitmap blurBackgroundDrawer = FastBlurUtil.getBlurBackgroundDrawer((Activity) context);
        window.setBackgroundDrawable(new BitmapDrawable(context.getResources(), blurBackgroundDrawer));


    }
}
