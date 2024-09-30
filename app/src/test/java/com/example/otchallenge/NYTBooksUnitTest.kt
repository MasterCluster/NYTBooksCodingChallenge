package com.example.otchallenge

import com.example.otchallenge.data.api.response.Book
import com.example.otchallenge.mvp.BookList
import com.example.otchallenge.mvp.MvpContract
import com.example.otchallenge.mvp.MvpPresenter
import com.example.otchallenge.mvp.MvpView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class NYTBooksUnitTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val testResultBookList = listOf<Book>()
    private val testResultException = IllegalArgumentException()

    private val apiRequestSuccess = object : MvpContract.Model<BookList> {
        override suspend fun invoke(): Result<BookList> {
            return Result.success(testResultBookList)
        }
    }

    private val apiRequestFailure = object : MvpContract.Model<BookList> {
        override suspend fun invoke(): Result<BookList> {
            return Result.failure(testResultException)
        }
    }

    private val mvpView: MvpContract.View = mock<MvpView>()


    @Test
    fun useCaseSuccess() = runTest {
        val result = apiRequestSuccess.invoke()
        assertTrue(result.getOrThrow() == testResultBookList)
    }

    @Test
    fun useCaseFailure() = runTest {
        val result = apiRequestFailure.invoke()
        assertTrue(result.exceptionOrNull() == testResultException)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun presenterSuccess() = runTest(testDispatcher) {
        val mvpPresenterSuccess = MvpPresenter(this, apiRequestSuccess, mvpView)
        mvpPresenterSuccess.fetchBookList()
        verify(mvpView, times(1)).showLoading()
        verify(mvpView, times(1)).hideLoading(true)
        verify(mvpView, times(1)).showBookList(testResultBookList)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun presenterFailure() = runTest(testDispatcher) {
        val mvpPresenterFailure = MvpPresenter(this, apiRequestFailure, mvpView)
        mvpPresenterFailure.fetchBookList()
        verify(mvpView, times(1)).showLoading()
        verify(mvpView, times(1)).hideLoading(false)
        verify(mvpView, times(0)).showBookList(testResultBookList)
    }
}