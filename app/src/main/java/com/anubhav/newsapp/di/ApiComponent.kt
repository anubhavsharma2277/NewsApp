package com.anubhav.newsapp.di

import com.anubhav.newsapp.backend.api.NewsApi
import com.anubhav.newsapp.backend.repository.NewsRepository
import com.anubhav.newsapp.viewmodel.NewsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiHelper::class, AppModule::class, DBModule::class])
interface ApiComponent {

    val newsApi: NewsApi

    fun inject(repo: NewsRepository)
    fun inject(newsVM: NewsViewModel)
}