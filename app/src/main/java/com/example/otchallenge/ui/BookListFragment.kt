package com.example.otchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.otchallenge.data.api.usecase.GetHardcoverFictionBooksUseCase
import com.example.otchallenge.databinding.FragmentBooklistBinding
import com.example.otchallenge.mvp.MvpContract
import com.example.otchallenge.mvp.MvpPresenter
import com.example.otchallenge.mvp.MvpView

class BookListFragment : Fragment() {

    private var _binding: FragmentBooklistBinding? = null
    private val binding get() = requireNotNull(_binding)


    private var mvpModel = GetHardcoverFictionBooksUseCase()

    private var _mvpView: MvpContract.View? = null
    private val mvpView get() = requireNotNull(_mvpView)

    private var _mvpPresenter: MvpContract.Presenter? = null
    private val mvpPresenter get() = requireNotNull(_mvpPresenter)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) =
        FragmentBooklistBinding.inflate(inflater).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _mvpPresenter = null
        _mvpView = null
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _mvpView = MvpView(binding) { mvpPresenter.fetchBookList() }
        _mvpPresenter = MvpPresenter(lifecycleScope, mvpModel, mvpView)

        mvpView.onViewCreated()
        mvpPresenter.fetchBookList()
    }
}

