package com.alexii.books.search.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alexii.books.R;
import com.alexii.books.common.databinding.viewmodel.BookViewModel;
import com.alexii.books.common.domain.Book;
import com.alexii.books.common.rest.dto.ShortEBookInfo;
import com.alexii.books.databinding.ListItemBookBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays {@link ShortEBookInfo} in the "list".
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.EBookHolder> {

    private List<Book> books = new ArrayList<>();

    public BookAdapter() {
    }

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public EBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBookBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_book, parent, false);
        return new EBookHolder(binding);
    }

    @Override
    public void onBindViewHolder(EBookHolder holder, int position) {
        Book eBook = books.get(position);
        holder.bindBook(eBook);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    /**
     * Displays books for new "search input" from scratch.
     *
     * @param books
     */
    public void displayNewBooks(List<? extends Book> books) {
        this.books.clear();

        this.books.addAll(books);

        notifyDataSetChanged();
    }

    public static class EBookHolder extends RecyclerView.ViewHolder {
        /**
         * Binds book (model) to UI.
         */
        private final BookViewModel bookViewModel;

        /**
         * Required for forcing UI update.
         * Otherwise rebinding does not happen immediately, which possibility could cause visible flicker.
         */
        private final ListItemBookBinding binding;


        public EBookHolder(ListItemBookBinding binding) {
            super(binding.getRoot());

            bookViewModel = new BookViewModel(binding.getRoot().getContext());
            binding.setViewModel(bookViewModel);

            this.binding = binding;
        }

        public void bindBook(Book book) {
            bookViewModel.setBook(book);

            binding.executePendingBindings();
        }
    }

}
