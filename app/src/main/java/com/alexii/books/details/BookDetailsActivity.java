package com.alexii.books.details;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexii.books.R;
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

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookDetailsViewModel.class);

        ActivityBookDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        final Book initialBookData = getInitialBookData();
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
