package com.alexii.books.di;


import com.alexii.books.details.BookDetailsActivity;
import com.alexii.books.di.scope.PerActivity;
import com.alexii.books.search.BookSearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {BookSearchActivityModule.class})
    abstract BookSearchActivity bindBookSearchActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {BookDetailsActivityModule.class})
    abstract BookDetailsActivity bindBookDetailsActivity();

}
