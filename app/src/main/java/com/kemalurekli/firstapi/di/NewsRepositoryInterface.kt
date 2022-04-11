package com.kemalurekli.firstapi.di

import androidx.lifecycle.LiveData
import com.kemalurekli.firstapi.model.NewsResponse
import com.kemalurekli.firstapi.roomdb.FavoriteNews
import com.kemalurekli.firstapi.util.Resource
import javax.annotation.Nullable

interface NewsRepositoryInterface {

    suspend fun insertNews(favoriteNews: FavoriteNews)

    suspend fun deleteNews (favoriteNews: FavoriteNews)

    fun getFavNewsDetails (favoriteNewsID : Int) : LiveData<FavoriteNews>

    fun getNews() : LiveData<List<FavoriteNews>>

    suspend fun newsDownload() : Resource<NewsResponse>

    fun saveOrNot(url : String) : LiveData<FavoriteNews?>
}