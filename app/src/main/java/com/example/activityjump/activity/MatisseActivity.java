package com.example.activityjump.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.activityjump.R;
import com.example.activityjump.recyclerview.RecyclerViewAdapter;
import com.example.activityjump.recyclerview.RecyclerViewBean;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatisseActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHOOSE_IMAGES = 1;
    private LinearLayout ll_addimages;

    private RecyclerView recyclerView;

    ActivityResultLauncher<String[]> launcherStoragePermission;
    ActivityResultLauncher<String[]> launcherStoragePermission13;
    private static final int MAX_IMAGE_COUNT = 6;
    private static final int MAX_VIDEO_COUNT = 1;


    private RecyclerViewAdapter recyclerViewAdapter;

    List<RecyclerViewBean> beanList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matisse);

        ll_addimages = findViewById(R.id.ll_addimages);
        recyclerView = findViewById(R.id.recycler_view);
        for (int i = 0; i < 50; i++) {
            RecyclerViewBean bean = new RecyclerViewBean();
            bean.name = "name" + i;
            bean.content= "content" + i;
            beanList.add(bean);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(beanList);
        recyclerView.setAdapter(recyclerViewAdapter);

        launcherStoragePermission13 = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                permissions -> {

                    Boolean readGranted = permissions.getOrDefault(Manifest.permission.READ_MEDIA_IMAGES, false);
                    Boolean writeGranted = permissions.getOrDefault(Manifest.permission.READ_MEDIA_VIDEO, false);
                    if (readGranted != null && writeGranted != null && readGranted && writeGranted) {
                        // Both read and write permissions are granted.
                        try {
//                            runMatisse();
                        } catch (Exception ex) {
                            ToastUtils.showLong(ex.getMessage());
                        }
                    } else {
                        // Permissions are denied.
                        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES) ||
                                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_VIDEO)) {
                            showExplainDialog(REQUEST_CODE_PERMISSIONS_SELECT);
                        } else {
                            showExplainDialog(REQUEST_CODE_PERMISSIONS_SELECT);
                        }
                    }

                });


        launcherStoragePermission = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    if (result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null
                            && result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null) {
                        if (Objects.requireNonNull(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE)).equals(true)
                                && Objects.requireNonNull(result.get(Manifest.permission.READ_EXTERNAL_STORAGE)).equals(true)) {
                            //权限全部获取到之后的动作
                            try {
//                                runMatisse();
                            } catch (Exception ex) {
                                ToastUtils.showLong(ex.getMessage());
                            }
                        } else {
                            //有权限没有获取到的动作
                            showExplainDialog(REQUEST_CODE_PERMISSIONS_SELECT);
                        }
                    }else {
                        //有权限没有获取到的动作
                        showExplainDialog(REQUEST_CODE_PERMISSIONS_SELECT);
                    }
                });

        ll_addimages.setOnClickListener(v -> {
            requestStoragePermissions();
        });
    }
    private final int REQUEST_CODE_PERMISSIONS_SELECT =11;
    private final int DIALOG_REQUEST_PERMISSIONS_EXPLAIN = 10;
    private void showExplainDialog(int code) {
        new AlertDialog.Builder(this)
                .setTitle("权限申请")
                .setMessage("需要获取存储权限")
                .setPositiveButton("去设置", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, code);
                })
                .setNegativeButton("取消", (dialog, which) -> {
                })
                .show();
    }

    private void requestStoragePermissions() {
        if (Build.VERSION.SDK_INT >= 33) {
            launcherStoragePermission13.launch(PERMISSIONS13);
        } else {
            launcherStoragePermission.launch(PERMISSIONS);
        }

    }

    private String[] PERMISSIONS = new String[]{

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private final String[] PERMISSIONS13 = new String[]{
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO
    };


//    private void  runMatisse(){
//        Matisse.from(this)
//                .choose(MimeType.ofAll())
//                .countable(true)
//                .maxSelectablePerMediaType(MAX_IMAGE_COUNT, MAX_VIDEO_COUNT)
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                .thumbnailScale(0.85f)
//                .imageEngine(new GlideEngine())
//                .showSingleMediaType(true)
//                .maxOriginalSize(5)
//                .theme(R.style.Matisse_Dracula)
//                .forResult(REQUEST_CODE_CHOOSE_IMAGES);
//
//    }



}