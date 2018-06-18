package com.alexii.books.common.rest.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Contains detailed data to display on separate screen.
 */
public class DetailedEBookInfo extends EBookInfo {
    private static final long serialVersionUID = 4898389022715589990L;

    @SerializedName(DtoSchema.EBook.BOOK_INFO)
    DetailedBookInfo bookInfo;

    @Override
    public DetailedBookInfo getBookInfo() {
        return bookInfo;
    }

}
