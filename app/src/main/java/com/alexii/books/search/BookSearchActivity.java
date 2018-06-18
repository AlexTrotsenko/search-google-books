package com.alexii.books.search;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.alexii.books.R;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.alexii.books.search.ui.BookAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.Disposable;

import static com.uber.autodispose.AutoDispose.autoDisposable;

public class BookSearchActivity extends AppCompatActivity {

    @BindView(R.id.book_search_input)
    EditText bookSearchInput;

    @BindView(R.id.books)
    RecyclerView booksRecycler;

    @Inject
    BookSearchViewModelFactory viewModelFactory;

    private BookSearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_search);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookSearchViewModel.class);

        RxTextView.textChanges(bookSearchInput)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(inputText -> !TextUtils.isEmpty(inputText))
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(inputText -> viewModel.searchBooks(inputText));

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        booksRecycler.setLayoutManager(layoutManager);
        BookAdapter booksAdapter = new BookAdapter();
        booksRecycler.setAdapter(booksAdapter);

        viewModel.getBooks().observe(this, booksAdapter::displayNewBooks);
    }
}
