package com.example.activityjump;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MatisseActivity extends AppCompatActivity {

    private LinearLayout ll_addimages;

    //    private RelativeLayout ll_images;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private static final int GRID_COLUMN_COUNT = 3;
    private GridView gridView;
    private List<Uri> selectedUris;
    private ImageAdapter imageAdapter;

    public MatisseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matisse);

        ll_addimages = findViewById(R.id.ll_addimages);
//        ll_images = findViewById(R.id.ll_images);
        gridView = findViewById(R.id.grid_view);

        ll_addimages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
//                Matisse.from(MatisseActivity.this)
//                        .choose(MimeType.ofImage())
//                        .countable(true)
//                        .maxSelectable(6)
//                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                        .thumbnailScale(0.85f)
//                        .imageEngine(new GlideEngine())
//                        .showSingleMediaType(true)
//                        .maxOriginalSize(5)
//                        .theme(R.style.Matisse_Dracula)
//                        .forResult(1);
            }
        });

        selectedUris = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, selectedUris);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteImage(position);
            }
        });
    }

    private void openGallery() {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(9)
                .capture(false)
                .captureStrategy(new CaptureStrategy(true, "com.example.android.fileprovider"))
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    private void deleteImage(int position) {
        selectedUris.remove(position);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            selectedUris = Matisse.obtainResult(data);
            imageAdapter.setUris(selectedUris);
        }
    }
}



