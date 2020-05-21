package com.anubhav.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anubhav.newsapp.Appcontroller
import com.anubhav.newsapp.backend.repository.NewsRepository
import com.anubhav.newsapp.model.BaseModel
import com.anubhav.newsapp.model.NewsData
import java.util.*
import javax.inject.Inject


class NewsViewModel : ViewModel() {

    var newsLiveData: MutableLiveData<BaseModel<ArrayList<NewsData>>>

    init {
        this.newsLiveData = MutableLiveData()
        Appcontroller.app.mApiComponent.inject(this)
    }

    @Inject
    lateinit var mRepository: NewsRepository

    fun getNewsList(): MutableLiveData<BaseModel<ArrayList<NewsData>>> {
        newsLiveData = mRepository.fetchNewsList()
        return newsLiveData
    }

}