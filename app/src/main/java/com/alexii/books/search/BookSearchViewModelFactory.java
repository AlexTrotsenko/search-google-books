package com.alexii.books.search;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.alexii.books.di.scope.PerActivity;

import javax.inject.Inject;
import javax.inject.Provider;

@PerActivity
public class BookSearchViewModelFactory implements ViewModelProvider.Factory {

    private final Provider<BookSearchViewModel> viewModelProvider;

    @Inject
    public BookSearchViewModelFactory(Provider<BookSearchViewModel> viewModelProvider) {
        this.viewModelProvider = viewModelProvider;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookSearchViewModel.class)) {
            return (T) viewModelProvider.get();
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}
