package com.alexii.books.details;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.alexii.books.di.scope.PerActivity;

import javax.inject.Inject;
import javax.inject.Provider;

@PerActivity
public class BookDetailsViewModelFactory implements ViewModelProvider.Factory {

    private final Provider<BookDetailsViewModel> viewModelProvider;

    @Inject
    public BookDetailsViewModelFactory(Provider<BookDetailsViewModel> viewModelProvider) {
        this.viewModelProvider = viewModelProvider;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookDetailsViewModel.class)) {
            return (T) viewModelProvider.get();
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}
