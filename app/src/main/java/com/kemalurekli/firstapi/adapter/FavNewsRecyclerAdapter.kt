package com.kemalurekli.firstapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.model.NewsResult
import javax.inject.Inject

class FavNewsRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<FavNewsRecyclerAdapter.NewsViewHolder> () {

    class NewsViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<NewsResult>() {
        override fun areItemsTheSame(oldItem: NewsResult, newItem: NewsResult): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsResult, newItem: NewsResult): Boolean {
            return oldItem == newItem
        }


    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var news: List<NewsResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        return NewsViewHolder(view)    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = news.size
}