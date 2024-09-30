package com.example.otchallenge.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MvpPresenter(
    private val scope: CoroutineScope,
    private val model: MvpContract.Model<BookList>,
    private val view: MvpContract.View,
) : MvpContract.Presenter {

    override fun fetchBookList() {
        view.showLoading()

        scope.launch {
            val result = model.invoke()

            view.hideLoading(result.isSuccess)

            if (result.isSuccess) {
                view.showBookList(result.getOrThrow())
            }
        }
    }
}