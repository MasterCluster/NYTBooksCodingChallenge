package com.example.otchallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.otchallenge.R
import com.example.otchallenge.data.api.response.Book
import com.example.otchallenge.databinding.LayoutBooklistItemBinding
import com.example.otchallenge.mvp.BookList
import com.example.otchallenge.mvp.OnOpenInStoreListener

class BookListRecyclerViewAdapter(
    private val onOpenInStoreClick: OnOpenInStoreListener,
) : RecyclerView.Adapter<BookListViewHolder>() {

    private val values = ArrayList<Book>()

    fun submitList(values: BookList) {
        this.values.clear()
        this.values.addAll(values)
        notifyItemRangeInserted(0, values.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookListViewHolder(
            LayoutBooklistItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(values[position], onOpenInStoreClick)
    }

    override fun getItemCount() =
        values.size

}


class BookListViewHolder(
    private val binding: LayoutBooklistItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Book,
        onOpenInStoreClick: OnOpenInStoreListener,
    ) {
        binding.bookAuthor.text = item.author
        binding.bookTitle.text = item.title
        binding.bookDescription.text = item.description
        binding.bookOpenInStore.setOnClickListener {
            onOpenInStoreClick.onOpenInStore(item.amazon_product_url)
        }

        Glide.with(binding.root.context)
            .load(item.book_image)
            .placeholder(R.drawable.baseline_menu_book_24)
            .error(R.drawable.baseline_highlight_off_24)
            .into(binding.bookImage)
    }
}
