package com.example.activityjump;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MatisseActivity extends AppCompatActivity {

    private static final int SDK_PERMISSION_REQUEST = 1;
    private LinearLayout ll_addimages;

    private RelativeLayout ll_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matisse);

        ll_addimages = findViewById(R.id.ll_addimages);
        ll_images = findViewById(R.id.ll_images);

        ll_addimages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(MatisseActivity.this)
                        .choose(MimeType.ofImage())
                        .countable(true)
                        .maxSelectable(6)
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .showSingleMediaType(true)
                        .maxOriginalSize(5)
                        .theme(R.style.Matisse_Dracula)
                        .forResult(1);
            }
        });
    }


}