package com.example.projecttraining.books.entitys;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .centerCrop()
                .placeholder(R.mipmap.loading)
                .error(R.drawable.faliure)
                .fallback(R.drawable.faliure)
                .into(imageView);
    }
}
