package com.alexii.books.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alexii.books.common.rest.dto.EBookInfo;
import com.alexii.books.common.rest.dto.ShortEBookInfo;
import com.alexii.books.common.rest.services.GoogleBookService;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

class BookDetailsViewModel extends ViewModel {
    private final GoogleBookService booksService;

    private final MutableLiveData<EBookInfo> bookInfo = new MutableLiveData<>();
    private Disposable pendingBookInfoLoading;

    BookDetailsViewModel(GoogleBookService booksService) {
        this.booksService = booksService;
    }

    @Override
    protected void onCleared() {
        pendingBookInfoLoading.dispose();
    }


    public void setInitialBookInfo(ShortEBookInfo initialEBookInfo) {
        final EBookInfo currentBookInfo = bookInfo.getValue();

        if (currentBookInfo != null
                && initialEBookInfo.getId().equals(currentBookInfo.getId())) {
            //do not load book info on rotation
            return;
        }

        bookInfo.setValue(initialEBookInfo);

        if (pendingBookInfoLoading != null && !pendingBookInfoLoading.isDisposed()) pendingBookInfoLoading.dispose();

        pendingBookInfoLoading = booksService.getBookDetails(initialEBookInfo.getId())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        eBookResult -> bookInfo.postValue(eBookResult),
                        error -> Timber.e(error, "Unable to get book's details info from rest api.")
                );

    }

    public LiveData<EBookInfo> getBookInfo() {
        return bookInfo;
    }




}
