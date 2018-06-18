package com.alexii.books.di;

import android.support.annotation.NonNull;

import com.alexii.books.common.rest.MetaData;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Contains API for getting data from network (Google Books): json data and images.
 */
@Module
public class NetworkModule {

    @Provides
    @NonNull
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(MetaData.GOOGLE_BOOKS_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public Picasso providePicasso() {
        return Picasso.get();
    }
}
