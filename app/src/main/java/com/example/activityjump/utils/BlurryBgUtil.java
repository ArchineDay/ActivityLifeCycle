package com.example.activityjump.utils;

import static com.blankj.utilcode.util.ViewUtils.runOnUiThread;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class BlurryBgUtil {
    private static int originalW;
    private static int originalH;
 
    private static Bitmap captureScreen(Activity activity) {
        activity.getWindow().getDecorView().destroyDrawingCache();  //先清理屏幕绘制缓存(重要)
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        //获取原图尺寸
        originalW = bmp.getWidth()+10;
        originalH = bmp.getHeight();
        //对原图进行缩小，提高下一步高斯模糊的效率
        bmp = Bitmap.createScaledBitmap(bmp, originalW / 2, originalH / 2, false);
        return bmp;
    }
 
    public static void setScreenBgLight(Activity context) {
        Window window = context.getWindow();
        WindowManager.LayoutParams lp;
        if (window != null) {
            lp = window.getAttributes();
            lp.dimAmount = 0.1f;
            window.setAttributes(lp);
        }
    }
 
    public static void handleBlur(Activity context, ImageView dialogBg, Handler mHandler) {
        Bitmap bp = captureScreen(context);
        bp = blur(bp,context);                      //对屏幕截图模糊处理
        //将模糊处理后的图恢复到原图尺寸并显示出来
        bp = Bitmap.createScaledBitmap(bp, originalW, originalH, false);
        dialogBg.setImageBitmap(bp);
        dialogBg.setVisibility(View.VISIBLE);
        //防止UI线程阻塞，在子线程中让背景实现淡入效果
        asyncRefresh(true,dialogBg,mHandler);
    }
    public static void asyncRefresh(boolean in,ImageView dialogBg,Handler mHandler) {
        //淡出淡入效果的实现
        if(in) {    //淡入效果
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 256; i += 5) {
                        refreshUI(i,dialogBg);//在UI线程刷新视图
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {    //淡出效果
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 255; i >= 0; i -= 5) {
                        refreshUI(i,dialogBg);//在UI线程刷新视图
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //当淡出效果完毕后发送消息给mHandler把对话框背景设为不可见
                    mHandler.sendEmptyMessage(0);
                }
            }).start();
        }
    }
 
    public static void refreshUI(final int i, ImageView dialogBg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialogBg.setImageAlpha(i);
            }
        });
    }
 
    public static void hideBlur(ImageView dialogBg,Handler mHandler) {
        //把对话框背景隐藏
        asyncRefresh(false,dialogBg,mHandler);
        System.gc();
    }
 
    public static Bitmap blur(Bitmap bitmap,Activity activity) {
        //使用RenderScript对图片进行高斯模糊处理
        Bitmap output = Bitmap.createBitmap(bitmap); // 创建输出图片
        RenderScript rs = RenderScript.create(activity); // 构建一个RenderScript对象
        ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)); //
        // 创建高斯模糊脚本
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap); // 开辟输入内存
        Allocation allOut = Allocation.createFromBitmap(rs, output); // 开辟输出内存
        float radius = 10f;     //设置模糊半径
        gaussianBlue.setRadius(radius); // 设置模糊半径，范围0f<radius<=25f
        gaussianBlue.setInput(allIn); // 设置输入内存
        gaussianBlue.forEach(allOut); // 模糊编码，并将内存填入输出内存
        allOut.copyTo(output); // 将输出内存编码为Bitmap，图片大小必须注意
        rs.destroy();
        //rs.releaseAllContexts(); // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        return output;
    }
}