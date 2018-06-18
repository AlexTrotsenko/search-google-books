package com.alexii.books.common.databinding;

import android.databinding.DataBindingComponent;

import com.squareup.picasso.Picasso;

public class DefaultDataBindingComponent implements DataBindingComponent {
    private final Picasso picasso;

    public DefaultDataBindingComponent(Picasso picasso) {
        this.picasso = picasso;
    }

    public ImageBindingAdapter getImageBindingAdapter() {
        return new ImageBindingAdapter(picasso);
    }
}
