package com.alexii.books.common.databinding.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.alexii.books.common.domain.Book;
import com.alexii.books.common.rest.dto.EBookInfo;
import com.alexii.books.common.rest.dto.ShortEBookInfo;
import com.alexii.books.details.BookDetailsActivity;


/**
 * View model for EBookInfo
 */
public class BookViewModel extends BaseObservable {
    private Context context;
    protected Book book;

    public BookViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public String getTitle() {
        return book.getTitle();
    }

    @Bindable
    public String getThumbnailLink() {
        return book.getThumbnailLink();
    }

    public void setBook(Book book) {
        this.book = book;
        notifyChange();
    }

    public void openBookDetails() {
        //TODO: remove when from detailed screen refactored to use Book domain class instead of BookInfo dto: crashes app when details screen open.
        final EBookInfo eBookInfo = new ShortEBookInfo();

        Intent intent = BookDetailsActivity.newIntent(context, eBookInfo);
        context.startActivity(intent);
    }
}
