package com.alexii.books.di;


import com.alexii.books.details.BookDetailsActivity;
import com.alexii.books.search.BookSearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {BookSearchActivityModule.class})
    abstract BookSearchActivity bindBookSearchActivity();

    @ContributesAndroidInjector(modules = {BookDetailsActivityModule.class})
    abstract BookDetailsActivity bindBookDetailsActivity();

}
