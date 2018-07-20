package com.alexii.books.common.repository;

import com.alexii.books.common.domain.Book;
import com.alexii.books.common.domain.DetailedBook;

import java.util.List;

import io.reactivex.Single;

public interface BooksRepository {
    Single<List<Book>> getBooks(CharSequence bookName);

    Single<DetailedBook> getBookDetails(CharSequence bookName);
}
