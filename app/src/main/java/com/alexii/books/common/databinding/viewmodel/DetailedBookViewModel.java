package com.alexii.books.common.databinding.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.text.Html;

/**
 * contains binding for detailed information, which is present in DetailedEBookInfo.
 */
public class DetailedBookViewModel extends BookViewModel {

    public DetailedBookViewModel(Context context) {
        super(context);
    }

    @Bindable
    public CharSequence getDescription() {
        final String bookDescription = eBook.getBookInfo().getDescription();

        if (bookDescription == null) return null;

        return Html.fromHtml(bookDescription);
    }
}
