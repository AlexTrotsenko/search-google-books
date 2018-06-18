package com.alexii.books.search;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.alexii.books.common.rest.services.GoogleBookService;

public class BookSearchViewModelFactory implements ViewModelProvider.Factory {

    private final GoogleBookService googleBookService;

    public BookSearchViewModelFactory(GoogleBookService googleBookService) {
        this.googleBookService = googleBookService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookSearchViewModel.class)) {
            return (T) new BookSearchViewModel(googleBookService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}
