package com.example.otchallenge.di

import com.example.otchallenge.data.api.NYTBooksRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesBooksRepository() =
        NYTBooksRepository

}