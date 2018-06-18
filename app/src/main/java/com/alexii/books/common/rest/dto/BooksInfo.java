package com.alexii.books.common.rest.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Contains list of books, that matches search request and total books count.
 */
public class BooksInfo {
    @SerializedName(DtoSchema.Books.COUNT)
    Integer matchingBooksCount;

    @SerializedName(DtoSchema.Books.EBOOKS_LIST)
    List<ShortEBookInfo> eBooks;

    public Integer getMatchingBooksCount() {
        return matchingBooksCount;
    }

    public List<? extends EBookInfo> getEBooks() {
        return eBooks;
    }

    @Override
    public String toString() {
        return "BooksInfo{" +
                "matchingBooksCount=" + matchingBooksCount +
                ", eBooks=" + eBooks +
                '}';
    }
}
