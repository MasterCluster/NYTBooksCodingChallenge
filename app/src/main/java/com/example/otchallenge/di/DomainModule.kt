package com.example.otchallenge.di

import com.example.otchallenge.data.api.NYTBooksRepository
import com.example.otchallenge.data.api.usecase.GetHardcoverFictionBooksUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesGetHardcoverFictionBooksUseCase(
        repository: NYTBooksRepository
    ) =
        GetHardcoverFictionBooksUseCase(repository)
}