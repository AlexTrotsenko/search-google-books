package com.alexii.books.di;

import com.alexii.books.common.rest.services.GoogleBookService;
import com.alexii.books.search.BookSearchViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Defines BookSearchActivity-specific dependencies here.
 */
@Module
public class BookSearchActivityModule {

    @Provides
    BookSearchViewModelFactory provideTasksViewModelFactory(GoogleBookService googleBookService) {
        return new BookSearchViewModelFactory(googleBookService);
    }
}
