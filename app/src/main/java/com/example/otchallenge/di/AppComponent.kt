package com.example.otchallenge.di

import com.example.otchallenge.ui.BookListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
	fun inject(fragment: BookListFragment)
}
