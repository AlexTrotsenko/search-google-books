package com.alexii.books.details;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.alexii.books.common.repository.BooksRepository;

public class BookDetailsViewModelFactory implements ViewModelProvider.Factory {

    private final BooksRepository booksRepo;

    public BookDetailsViewModelFactory(BooksRepository booksRepo) {
        this.booksRepo = booksRepo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookDetailsViewModel.class)) {
            return (T) new BookDetailsViewModel(booksRepo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}
