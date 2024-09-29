package com.example.otchallenge.mvp

import android.content.Intent
import android.net.Uri
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otchallenge.databinding.FragmentBooklistBinding
import com.example.otchallenge.ui.BookListRecyclerViewAdapter
import com.example.otchallenge.ui.WindowSizeClassUtil

class MvpView(
    private val binding: FragmentBooklistBinding,
    private val onTryAgainClick: OnClickListener,
): MvpContract.View {

    override fun onViewCreated() {
        val context = binding.root.context
        val windowSizeClass = WindowSizeClassUtil.computeHorizontalWindowSizeClass(context)

        binding.list.layoutManager = when (windowSizeClass) {
            WindowSizeClassUtil.WindowSizeClass.COMPACT ->
                LinearLayoutManager(context)

            WindowSizeClassUtil.WindowSizeClass.MEDIUM ->
                GridLayoutManager(context, 2)

            WindowSizeClassUtil.WindowSizeClass.EXPANDED ->
                GridLayoutManager(context, 3)
        }

        binding.list.adapter = BookListRecyclerViewAdapter {
            onOpenInStore(it)
        }

        binding.tryAgainButton.setOnClickListener(onTryAgainClick)
    }

    override fun showLoading() {
        loadingAnimationStart(binding.loadingIndicator)
        binding.list.isVisible = false
        binding.loadingErrorMessage.isVisible = false
        binding.tryAgainButton.isVisible = false
    }

    override fun showBookList(books: BookList) {
        (binding.list.adapter as BookListRecyclerViewAdapter).submitList(books)
    }

    override fun hideLoading(isSuccess: Boolean) {
        loadingAnimationEnd(binding.loadingIndicator)
        binding.list.isVisible = isSuccess
        binding.loadingErrorMessage.isVisible = !isSuccess
        binding.tryAgainButton.isVisible = !isSuccess
    }

    override fun onOpenInStore(url: String) {
        binding.root.context.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        )
    }

    private fun loadingAnimationStart(imageView: ImageView) {
        imageView.isVisible = false

        // Rotating loading indicator
        val rotate = RotateAnimation(
            0f, -360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).also { animation ->
            animation.duration = 2500L
            animation.repeatCount = Animation.INFINITE
        }

        imageView.isVisible = true
        imageView.startAnimation(rotate)
    }

    private fun loadingAnimationEnd(imageView: ImageView) {
        imageView.clearAnimation()
        imageView.isVisible = false
    }
}