package com.alexii.books.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alexii.books.common.domain.Book;
import com.alexii.books.common.repository.BooksRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

class BookSearchViewModel extends ViewModel {
    private final BooksRepository booksRepo;

    private final MutableLiveData<List<? extends Book>> books = new MutableLiveData<>();
    private Disposable pendingBookSearch;

    @Inject
    BookSearchViewModel(BooksRepository booksService) {
        this.booksRepo = booksService;
    }

    @Override
    protected void onCleared() {
        pendingBookSearch.dispose();
    }

    public void searchBooks(CharSequence testToSearch) {
        if (pendingBookSearch != null && !pendingBookSearch.isDisposed()) pendingBookSearch.dispose();

        pendingBookSearch = booksRepo.getBooks(testToSearch)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        books::postValue,
                        e -> Timber.e(e, "Unable to get books info from rest api.")
                );
    }

    public LiveData<List<? extends Book>> getBooks() {
        return books;
    }

}
