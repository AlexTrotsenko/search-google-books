package com.alexii.books.common.rest.dto;

/**
 * Contains info about dto-json mapping.
 */
public interface DtoSchema {
    interface Books {
        String COUNT = "totalItems";
        String EBOOKS_LIST = "items";
    }

    interface EBook {
        String ID = "id";
        String BOOK_INFO = "volumeInfo";
    }
}
