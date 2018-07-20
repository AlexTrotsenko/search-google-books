package com.alexii.books.details;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexii.books.R;
import com.alexii.books.common.databinding.viewmodel.DetailedBookViewModel;
import com.alexii.books.common.domain.Book;
import com.alexii.books.databinding.ActivityBookDetailsBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class BookDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_EBOOK = "com.alexii.books.ebook";

    @Inject
    BookDetailsViewModelFactory viewModelFactory;

    private BookDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        ActivityBookDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
        DetailedBookViewModel bindingViewModel = new DetailedBookViewModel(this);
        //todo: merge DetailedBookViewModel with BookDetailsViewModel for simplicity
        binding.setViewModel(bindingViewModel);

        final Book initialBookData = getInitialBookData();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookDetailsViewModel.class);
        //TODO remove transform from view layer when from detailed screen refactored to use Book domain class instead of BookInfo dto
        viewModel.getBook().observe(this, bindingViewModel::setBook);

        viewModel.setInitialBookData(initialBookData);
    }

    public static Intent newIntent(Context context, Book book) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra(EXTRA_EBOOK, book);
        return intent;
    }

    /**
     * @return initial minimal book data for displaying on activity start-up.
     */
    protected Book getInitialBookData() {
        return (Book) getIntent().getSerializableExtra(EXTRA_EBOOK);
    }
}
