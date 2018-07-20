package com.alexii.books.common.rest.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alexii.books.common.domain.Book;
import com.alexii.books.common.domain.DetailedBook;
import com.alexii.books.common.rest.dto.BookInfo;
import com.alexii.books.common.rest.dto.DetailedEBookInfo;
import com.alexii.books.common.rest.dto.EBookInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookMapper {

    @Inject
    public BookMapper() {
    }

    public @Nullable Book transform(@Nullable EBookInfo eBookInfo) {
        if (eBookInfo == null) return null;

        final Book book = new Book();

        transformBookData(eBookInfo, book);

        return book;
    }

    private void transformBookData(@NonNull EBookInfo eBookInfo, Book book) {
        book.setId(eBookInfo.getId());

        final BookInfo bookInfo = eBookInfo.getBookInfo();
        book.setTitle(bookInfo.getTitle());
        book.setThumbnailLink(bookInfo.getThumbnailLink());
    }

    public @NonNull List<Book> transform(@Nullable Collection<? extends EBookInfo> eBookInfos) {
        if (eBookInfos == null) return Collections.emptyList();

        List<Book> books = new ArrayList<>();
        for (EBookInfo eBookInfo : eBookInfos) {
            final Book book = transform(eBookInfo);
            if (book != null) books.add(book);
        }

        return books;
    }

    public DetailedBook transform(DetailedEBookInfo detailedBookInfo) {
        final DetailedBook detailedBook = new DetailedBook();

        transformBookData(detailedBookInfo, detailedBook);

        detailedBook.setDescription(detailedBookInfo.getBookInfo().getDescription());

        return detailedBook;
    }
}
