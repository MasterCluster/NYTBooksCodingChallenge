package com.example.otchallenge.data.api.usecase

import com.example.otchallenge.data.api.NYTBooksRepository
import com.example.otchallenge.data.api.response.Book
import com.example.otchallenge.mvp.BookList
import com.example.otchallenge.mvp.MvpContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHardcoverFictionBooksUseCase(
    private val repository: NYTBooksRepository
) : MvpContract.Model<BookList> {

    override suspend fun invoke(): Result<BookList> {
        return withContext(Dispatchers.IO) {
            try {
                val response = repository.api.getHardcoverFictionBooks()

                if (repository.isApiError(response.status)) {
                    throw IllegalStateException("API call returned ${response.status}")
                }

                Result.success(response.results.books)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}