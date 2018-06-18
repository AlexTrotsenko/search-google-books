package com.alexii.books.search.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alexii.books.R;
import com.alexii.books.common.rest.dto.EBookInfo;
import com.alexii.books.common.rest.dto.ShortEBookInfo;
import com.alexii.books.databinding.ListItemBookBinding;
import com.alexii.books.common.databinding.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays {@link ShortEBookInfo} in the "list".
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.EBookHolder> {

    private List<EBookInfo> eBooks = new ArrayList<>();

    public BookAdapter() {
    }

    public BookAdapter(List<EBookInfo> eBooks) {
        this.eBooks = eBooks;
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
        EBookInfo eBook = eBooks.get(position);
        holder.bindEBook(eBook);
    }

    @Override
    public int getItemCount() {
        return eBooks.size();
    }

    /**
     * Displays books for new "search input" from scratch.
     *
     * @param eBooks
     */
    public void displayNewBooks(List<? extends EBookInfo> eBooks) {
        this.eBooks.clear();

        this.eBooks.addAll(eBooks);

        notifyDataSetChanged();
    }

    public static class EBookHolder extends RecyclerView.ViewHolder {
        /**
         * Binds eBook (model) to UI.
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

        public void bindEBook(EBookInfo eBook) {
            bookViewModel.setEBook(eBook);

            binding.executePendingBindings();
        }
    }

}
