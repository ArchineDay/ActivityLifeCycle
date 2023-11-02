package com.example.activityjump;// ImageAdapter.java
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<Uri> uris;

    public ImageAdapter(Context context, List<Uri> uris) {
        this.context = context;
        this.uris = uris;
    }

    @Override
    public int getCount() {
        return uris.size();
    }

    @Override
    public Object getItem(int position) {
        return uris.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Uri uri = uris.get(position);
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .into(viewHolder.imageView);

        return convertView;
    }

    public void setUris(List<Uri> uris) {
        this.uris = uris;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}