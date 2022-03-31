package com.kemalurekli.firstapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.model.NewsResult
import com.kemalurekli.firstapi.view.HomeFragmentDirections
import javax.inject.Inject

class NewsRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> () {

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
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.iv_news)
        val titleText = holder.itemView.findViewById<TextView>(R.id.tv_News_title)
        val sourceText = holder.itemView.findViewById<TextView>(R.id.tv_source)
        val dateText = holder.itemView.findViewById<TextView>(R.id.tv_date)
        val newsP = news[position]
        holder.itemView.apply {
            glide.load(newsP.imageUrl).into(imageView)
            titleText.text = newsP.name
            sourceText.text = newsP.source
            dateText.text = edittingDate(newsP.date)

            setOnClickListener {
                val choosenNewsTitle = newsP.name
                val choosenNewsContent = newsP.description
                val choosenNewsSource = newsP.source
                val choosenNewsImageUrl = newsP.imageUrl
                val choosenNewsDate = edittingDate(newsP.date)
                val choosenNewsUrl = newsP.url
                Navigation.findNavController(it).navigate(HomeFragmentDirections.
                actionHomeFragmentToDetailsFragment(choosenNewsTitle,choosenNewsSource,
                    choosenNewsContent,choosenNewsImageUrl,choosenNewsDate,choosenNewsUrl))
            }


        }

    }

    override fun getItemCount(): Int = news.size

    private fun edittingDate(date : String) : String {
        return "${date.subSequence(0,10)} - ${date.subSequence(11,16)}"
    }
}