package com.kemalurekli.firstapi.di

import androidx.lifecycle.LiveData
import com.kemalurekli.firstapi.model.NewsResponse
import com.kemalurekli.firstapi.roomdb.FavoriteNews
import com.kemalurekli.firstapi.util.Resource

interface NewsRepositoryInterface {

    suspend fun insertNews(favoriteNews: FavoriteNews)

    suspend fun deleteNews (favoriteNews: FavoriteNews)

    fun getNews() : LiveData<List<FavoriteNews>>

    suspend fun newsDownload() : Resource<NewsResponse>
}