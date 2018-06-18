package com.alexii.books.common.databinding.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.alexii.books.details.BookDetailsActivity;
import com.alexii.books.common.rest.dto.EBookInfo;


/**
 * View model for EBookInfo
 */
public class BookViewModel extends BaseObservable {
    private Context context;
    protected EBookInfo eBook;

    public BookViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public String getTitle() {
        return eBook.getBookInfo().getTitle();
    }

    @Bindable
    public String getThumbnailLink() {
        return eBook.getBookInfo().getThumbnailLink();
    }

    public void setEBook(EBookInfo eBook) {
        this.eBook = eBook;
        notifyChange();
    }

    public void openBookDetails() {
        Intent intent = BookDetailsActivity.newIntent(context, eBook);
        context.startActivity(intent);
    }
}
