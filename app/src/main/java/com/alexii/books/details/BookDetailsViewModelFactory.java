package com.alexii.books.details;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.alexii.books.common.rest.services.GoogleBookService;

public class BookDetailsViewModelFactory implements ViewModelProvider.Factory {

    private final GoogleBookService googleBookService;

    public BookDetailsViewModelFactory(GoogleBookService googleBookService) {
        this.googleBookService = googleBookService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookDetailsViewModel.class)) {
            return (T) new BookDetailsViewModel(googleBookService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}
