package com.alexii.books.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.alexii.books.common.rest.dto.EBookInfo;
import com.alexii.books.common.rest.services.GoogleBookService;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

class BookSearchViewModel extends ViewModel {
    private final GoogleBookService booksService;

    private final MutableLiveData<List<? extends EBookInfo>> books = new MutableLiveData<>();
    private Disposable pendingBookSearch;

    BookSearchViewModel(GoogleBookService booksService) {
        this.booksService = booksService;
    }

    @Override
    protected void onCleared() {
        pendingBookSearch.dispose();
    }


    public void searchBooks(CharSequence testToSearch) {
        if (pendingBookSearch != null && !pendingBookSearch.isDisposed()) pendingBookSearch.dispose();

        pendingBookSearch = booksService.getBooks(testToSearch)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        booksInfo -> books.postValue(booksInfo.getEBooks()),
                        error -> Timber.e(error, "Unable to get books info from rest api.")
                );
    }

    public LiveData<List<? extends EBookInfo>> getBooks() {
        return books;
    }

}
