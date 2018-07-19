package com.alexii.books.search;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.alexii.books.common.repository.BooksRepository;

public class BookSearchViewModelFactory implements ViewModelProvider.Factory {

    private final BooksRepository booksRepo;

    public BookSearchViewModelFactory(BooksRepository booksRepo) {
        this.booksRepo = booksRepo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookSearchViewModel.class)) {
            return (T) new BookSearchViewModel(booksRepo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}
