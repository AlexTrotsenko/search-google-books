package com.alexii.books.common.rest;

/**
 * Url-related constants holder.
 */
public interface MetaData {
    String GOOGLE_BOOKS_BASE_URL = "https://www.googleapis.com";

    interface VolumesList {
        String PATH = "books/v1/volumes";
        String SEARCH_QUERY_PARAM = "q";
    }

    interface VolumeDetails {
        String ID_PATH_PARAM = "volumeId";
        String PATH = "books/v1/volumes/{" + ID_PATH_PARAM + "}";
    }
}
