package com.alexii.books.di;

import com.alexii.books.common.rest.services.GoogleBookService;
import com.alexii.books.details.BookDetailsViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Defines BookSearchActivity-specific dependencies here.
 */
@Module
public class BookDetailsActivityModule {

    @Provides
    BookDetailsViewModelFactory provideTasksViewModelFactory(GoogleBookService googleBookService) {
        return new BookDetailsViewModelFactory(googleBookService);
    }
}
