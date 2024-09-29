package com.example.otchallenge.mvp

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.launch

class MvpPresenter(
    private val lifecycleScope: LifecycleCoroutineScope,
    private val model: MvpContract.Model<BookList>,
    private val view: MvpContract.View,
) : MvpContract.Presenter {

    override fun fetchBookList() {
        view.showLoading()

        lifecycleScope.launch {
            val result = model.invoke()

            view.hideLoading(result.isSuccess)

            if (result.isSuccess) {
                view.showBookList(result.getOrThrow())
            }
        }
    }
}