package com.alexii.books.di;

import com.alexii.books.common.repository.BooksRepository;
import com.alexii.books.details.BookDetailsViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Defines BookSearchActivity-specific dependencies here.
 */
@Module
public class BookDetailsActivityModule {

    @Provides
    BookDetailsViewModelFactory provideTasksViewModelFactory(BooksRepository booksRepository) {
        return new BookDetailsViewModelFactory(booksRepository);
    }
}
