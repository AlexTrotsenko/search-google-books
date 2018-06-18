package com.alexii.books.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alexii.books.App;
import com.alexii.books.common.rest.services.GoogleBookService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    @NonNull
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    @NonNull
    public GoogleBookService provideGoogleBookService(Retrofit retrofit) {
        return retrofit.create(GoogleBookService.class);
    }
}
