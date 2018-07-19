package com.alexii.books.di;

import com.alexii.books.common.repository.BooksRepository;
import com.alexii.books.search.BookSearchViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Defines BookSearchActivity-specific dependencies here.
 */
@Module
public class BookSearchActivityModule {

    @Provides
    BookSearchViewModelFactory provideTasksViewModelFactory(BooksRepository booksRepo) {
        return new BookSearchViewModelFactory(booksRepo);
    }
}
