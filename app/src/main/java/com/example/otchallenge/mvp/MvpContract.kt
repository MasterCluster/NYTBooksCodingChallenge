package com.example.otchallenge.mvp

import com.example.otchallenge.data.api.response.Book

typealias BookList = List<Book>

fun interface OnOpenInStoreListener {
    fun onOpenInStore(url: String)
}

interface MvpContract {

    interface Model<T> {
        suspend fun invoke(): Result<T>
    }

    interface View: OnOpenInStoreListener {
        fun onViewCreated()
        fun showLoading()
        fun showBookList(books: BookList)
        fun hideLoading(isSuccess: Boolean)
    }

    interface Presenter {
        fun fetchBookList()
    }
}
