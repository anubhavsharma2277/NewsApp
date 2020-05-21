package com.anubhav.newsapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anubhav.newsapp.adapter.NewsListAdapter
import com.anubhav.newsapp.databinding.ActivityMainBinding
import com.anubhav.newsapp.model.NewsData
import com.anubhav.newsapp.viewmodel.NewsViewModel


class NewsListActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = com.anubhav.newsapp.R.layout.activity_main;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarText("News List")
        hideBack()
        getNewsApi()
    }

    private fun getNewsApi() {
        val newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.getNewsList().observe(this, Observer { newsData ->
            hideProgress()
            showData()
            setAdapter(newsData.articles);
        })
    }
    // setting adapter
    private fun setAdapter(newsList: ArrayList<NewsData>) {
        binding.recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            adapter = NewsListAdapter(this@NewsListActivity, newsList)
            addItemDecoration(
                DividerItemDecoration(
                    this@NewsListActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

    // onclick adapter
            (adapter as NewsListAdapter).onItemClick = { url ->
                val i: Intent = Intent(this@NewsListActivity, NewsDetailActivity::class.java)
                i.putExtra("url", url)
                startActivity(i)
            }
        }


    }

}
