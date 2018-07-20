package com.alexii.books.common.repository.impl;

import com.alexii.books.common.domain.Book;
import com.alexii.books.common.domain.DetailedBook;
import com.alexii.books.common.repository.BooksRepository;
import com.alexii.books.common.rest.dto.BooksInfo;
import com.alexii.books.common.rest.mapper.BookMapper;
import com.alexii.books.common.rest.services.GoogleBookService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class BooksRepositoryImpl implements BooksRepository {
    final GoogleBookService bookService;
    final BookMapper mapper;

    @Inject
    public BooksRepositoryImpl(GoogleBookService bookService, BookMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Book>> getBooks(CharSequence bookName) {
        //xxx: add caching to the local storage
        return bookService.getBooks(bookName).map(BooksInfo::getEBooks).map(mapper::transform);
    }

    @Override
    public Single<DetailedBook> getBookDetails(CharSequence bookName) {
        //xxx: add caching to the local storage
        return bookService.getBookDetails(bookName).map(mapper::transform);
    }
}
