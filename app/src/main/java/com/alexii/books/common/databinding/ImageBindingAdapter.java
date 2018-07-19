package com.alexii.books.common.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class ImageBindingAdapter {
    private final Picasso picasso;

    public ImageBindingAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @BindingAdapter(value={"url", "placeholder"}, requireAll=false)
    public void resetViewAndLoadImage(@NonNull ImageView imageView, @Nullable String url, @Nullable Drawable placeholder) {
        final RequestCreator builder = picasso.load(url);

        if (placeholder != null) builder.placeholder(placeholder).error(placeholder);

        builder.fit().into(imageView);
    }
}
