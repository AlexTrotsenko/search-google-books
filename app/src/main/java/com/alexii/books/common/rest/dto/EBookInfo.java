package com.alexii.books.common.rest.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Data about book as well as digital "id" in google books, etc.
 */
public abstract class EBookInfo implements Serializable {
    private static final long serialVersionUID = -7091980368063980046L;

    @SerializedName(DtoSchema.EBook.ID)
    String id;

    public String getId() {
        return id;
    }

    abstract public BookInfo getBookInfo();

    @Override
    public String toString() {
        return "EBookInfo{" +
                "id='" + getId() + '\'' +
                ", bookInfo=" + getBookInfo() +
                '}';
    }
}
