package com.alexii.books.di;

import com.alexii.books.common.databinding.DefaultDataBindingComponent;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBindingModule {
    @Provides
    DefaultDataBindingComponent provideDataBindingComponent(Picasso picasso) {
        //creation left verbose for clarity
        return new DefaultDataBindingComponent(picasso);
    }
}
