package com.alexii.books.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alexii.books.common.domain.Book;
import com.alexii.books.common.repository.BooksRepository;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

class BookDetailsViewModel extends ViewModel {
    private final BooksRepository booksRepo;

    private final MutableLiveData<Book> book = new MutableLiveData<>();
    private Disposable pendingBookInfoLoading;

    BookDetailsViewModel(BooksRepository booksRepo) {
        this.booksRepo = booksRepo;
    }

    @Override
    protected void onCleared() {
        pendingBookInfoLoading.dispose();
    }


    public void setInitialBookData(Book initialBookData) {
        final Book currentBookData = book.getValue();

        if (currentBookData != null
                && initialBookData.getId().equals(currentBookData.getId())) {
            //do not load book info on rotation
            return;
        }

        book.setValue(initialBookData);

        if (pendingBookInfoLoading != null && !pendingBookInfoLoading.isDisposed()) pendingBookInfoLoading.dispose();

        pendingBookInfoLoading = booksRepo.getBookDetails(initialBookData.getId())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        book::postValue,
                        e -> Timber.e(e, "Unable to get book's details info from rest api.")
                );

    }

    public LiveData<Book> getBook() {
        return book;
    }
}
