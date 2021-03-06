package com.alexii.books.common.rest.services;

import com.alexii.books.common.rest.MetaData;
import com.alexii.books.common.rest.dto.BooksInfo;
import com.alexii.books.common.rest.dto.DetailedEBookInfo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Server side rest API for getting books data.
 */
public interface GoogleBookService {
    @GET(MetaData.VolumesList.PATH)
    Single<BooksInfo> getBooks(@Query(MetaData.VolumesList.SEARCH_QUERY_PARAM) CharSequence bookName);

    @GET(MetaData.VolumeDetails.PATH)
    Single<DetailedEBookInfo> getBookDetails(@Path(MetaData.VolumeDetails.ID_PATH_PARAM) CharSequence bookName);
}
