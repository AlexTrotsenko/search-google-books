package com.alexii.books.common.rest.dto;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Short list of data about the book to display in "books list".
 */
public class BookInfo implements Serializable {
    private static final long serialVersionUID = 8320625464585649346L;

    String title;
    ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    @Nullable
    public String getThumbnailLink() {
        if (imageLinks == null) return null;

        return imageLinks.thumbnail;
    }

    /**
     * @return null as base info do not have description data.
     */
    public String getDescription() {
        return null;
    }

    public static class ImageLinks implements Serializable {
        private static final long serialVersionUID = -5555371262366112331L;

        String thumbnail;

        @Override
        public String toString() {
            return "ImageLinks{" +
                    "thumbnail='" + thumbnail + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", imageLinks=" + imageLinks +
                '}';
    }
}
