package com.example.otchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.otchallenge.MyApplication
import com.example.otchallenge.data.api.usecase.GetHardcoverFictionBooksUseCase
import com.example.otchallenge.databinding.FragmentBooklistBinding
import com.example.otchallenge.mvp.MvpContract
import com.example.otchallenge.mvp.MvpPresenter
import com.example.otchallenge.mvp.MvpView
import javax.inject.Inject

class BookListFragment : Fragment() {

    private var _binding: FragmentBooklistBinding? = null
    private val binding get() = requireNotNull(_binding)


    @Inject
    lateinit var mvpModel: GetHardcoverFictionBooksUseCase

    private val mvpView: MvpContract.View by lazy {
        MvpView(binding) {
            mvpPresenter.fetchBookList()
        }
    }

    private val mvpPresenter: MvpContract.Presenter by lazy {
        MvpPresenter(lifecycleScope, mvpModel, mvpView)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) =
        FragmentBooklistBinding.inflate(inflater).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        mvpView.onViewCreated()
        mvpPresenter.fetchBookList()
    }
}

