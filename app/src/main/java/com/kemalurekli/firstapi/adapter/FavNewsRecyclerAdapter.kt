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
import com.kemalurekli.firstapi.roomdb.FavoriteNews
import com.kemalurekli.firstapi.view.FavNewsFragment
import com.kemalurekli.firstapi.view.FavNewsFragmentDirections
import com.kemalurekli.firstapi.view.HomeFragmentDirections
import javax.inject.Inject

class FavNewsRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<FavNewsRecyclerAdapter.NewsViewHolder> () {

    class NewsViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<FavoriteNews>() {
        override fun areItemsTheSame(oldItem: FavoriteNews, newItem: FavoriteNews): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteNews, newItem: FavoriteNews): Boolean {
            return oldItem == newItem
        }


    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var news: List<FavoriteNews>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        return NewsViewHolder(view)    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.iv_news)
        val titleText = holder.itemView.findViewById<TextView>(R.id.tv_News_title)
        val sourceText = holder.itemView.findViewById<TextView>(R.id.tv_source)
        val dateText = holder.itemView.findViewById<TextView>(R.id.tv_date)
        val newsP = news[position]
        holder.itemView.apply {
            glide.load(newsP.newsImageUrl).into(imageView)
            titleText.text = newsP.newsTitle
            sourceText.text = newsP.newsSource
            dateText.text = edittingDate(newsP.newsDate)

            setOnClickListener {
                Navigation.findNavController(it).navigate(FavNewsFragmentDirections.actionFavNewsFragmentToFavNewsDetailsFragment(newsP.id.toString()))
            }


        }
    }

    override fun getItemCount(): Int = news.size

    private fun edittingDate(date : String) : String {
        return "${date.subSequence(0,10)} - ${date.subSequence(11,16)}"
    }
}