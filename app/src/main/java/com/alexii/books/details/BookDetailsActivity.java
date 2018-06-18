package com.alexii.books.details;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexii.books.R;
import com.alexii.books.common.rest.dto.EBookInfo;
import com.alexii.books.common.rest.dto.ShortEBookInfo;
import com.alexii.books.common.rest.services.GoogleBookService;
import com.alexii.books.databinding.ActivityBookDetailsBinding;
import com.alexii.books.common.databinding.viewmodel.DetailedBookViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class BookDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_EBOOK = "com.alexii.books.ebook";

    @Inject
    BookDetailsViewModelFactory viewModelFactory;

    private BookDetailsViewModel viewModel;

    @Inject
    GoogleBookService googleBookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        ActivityBookDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
        DetailedBookViewModel bindingViewModel = new DetailedBookViewModel(this);
        //todo: merge DetailedBookViewModel with BookDetailsViewModel for simplicity
        binding.setViewModel(bindingViewModel);

        final ShortEBookInfo initialEBookInfo = getInitialEBookInfo();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookDetailsViewModel.class);
        viewModel.getBookInfo().observe(this, bindingViewModel::setEBook);

        viewModel.setInitialBookInfo(initialEBookInfo);
    }

    public static Intent newIntent(Context context, EBookInfo eBook) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra(EXTRA_EBOOK, eBook);
        return intent;
    }

    /**
     * @return initial minimal book data for displaying on activity start-up.
     */
    protected ShortEBookInfo getInitialEBookInfo() {
        return (ShortEBookInfo) getIntent().getSerializableExtra(EXTRA_EBOOK);
    }
}
