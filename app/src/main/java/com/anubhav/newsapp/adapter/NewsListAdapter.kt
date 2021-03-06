package com.anubhav.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anubhav.newsapp.BR
import com.anubhav.newsapp.R
import com.anubhav.newsapp.databinding.ItemNewsBinding
import com.anubhav.newsapp.model.NewsData

class NewsListAdapter(var context: Context, var newsList: ArrayList<NewsData>) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    lateinit var onItemClick: ((String) -> Unit?)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemNewsBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_news, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList.get(position))
    }

    override fun getItemCount(): Int = newsList.size

    class ViewHolder(
        val binding: ItemNewsBinding, val onItemClick: (String) -> Unit?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.news, data)
            binding.executePendingBindings()

            binding.layoutRoot.setOnClickListener {
                onItemClick.invoke(binding.news?.url.toString())
            }
        }
    }
}