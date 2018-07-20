package com.alexii.books.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alexii.books.App;
import com.alexii.books.common.repository.BooksRepository;
import com.alexii.books.common.repository.impl.BooksRepositoryImpl;
import com.alexii.books.common.rest.services.GoogleBookService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module(includes = AppModule.Declarations.class)
public class AppModule {

    @Module
    public interface Declarations {
        @Binds
        @NonNull
        Context provideContext(App application);

        @Singleton
        @Binds
        @NonNull
        BooksRepository provideBookRepository(BooksRepositoryImpl booksRepositoryImpl);
    }

    @Singleton
    @Provides
    @NonNull
    public GoogleBookService provideGoogleBookService(Retrofit retrofit) {
        return retrofit.create(GoogleBookService.class);
    }
}
