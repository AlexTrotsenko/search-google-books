package com.alexii.books.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.text.Html;

import com.alexii.books.common.domain.Book;
import com.alexii.books.common.repository.BooksRepository;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class BookDetailsViewModel extends ViewModel {
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

    private LiveData<Book> getBook() {
        return book;
    }

    public LiveData<String> getTitle() {
        return Transformations.map(getBook(), Book::getTitle);
    }

    public LiveData<String> getThumbnailLink() {
        return Transformations.map(getBook(), Book::getThumbnailLink);
    }

    public LiveData<CharSequence> getDescription() {
        return Transformations.map(getBook(), this::toHtmlLessDescription);
    }

    @Nullable
    private CharSequence toHtmlLessDescription(Book book) {
        final String description = book.getDescription();

        if (description == null) return null;

        return Html.fromHtml(description);
    }

}
