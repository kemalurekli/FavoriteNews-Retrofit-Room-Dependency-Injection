package com.kemalurekli.firstapi.repository

import androidx.lifecycle.LiveData
import com.kemalurekli.firstapi.api.RetrofitAPI
import com.kemalurekli.firstapi.di.NewsRepositoryInterface
import com.kemalurekli.firstapi.model.NewsResponse
import com.kemalurekli.firstapi.roomdb.FavoriteNews
import com.kemalurekli.firstapi.roomdb.FavoriteNewsDao
import com.kemalurekli.firstapi.util.Resource
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDao: FavoriteNewsDao,
    private val retrofitAPI: RetrofitAPI
) : NewsRepositoryInterface {
    override suspend fun insertNews(favoriteNews: FavoriteNews) {
        newsDao.insertFavoriteNews(favoriteNews)
    }

    override suspend fun deleteNews(favoriteNews: FavoriteNews) {
        newsDao.deleteFavoriteNews(favoriteNews)
    }

    override fun getFavNewsDetails(favoriteNewsID: Int): LiveData<FavoriteNews> {
        return newsDao.getAFavNews(favoriteNewsID)
    }

    override fun getNews(): LiveData<List<FavoriteNews>> {
        return newsDao.observeFavoriteNews()
    }

    override suspend fun newsDownload(): Resource<NewsResponse> {
        return try {
            val response = retrofitAPI.newsDownload()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            }else {
                Resource.error("Error",null)
            }
        }catch (e : Exception){
            Resource.error("No data!", null)
        }
    }
}