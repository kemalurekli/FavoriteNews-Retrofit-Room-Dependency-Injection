package com.kemalurekli.firstapi.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteNews (favoriteNews: FavoriteNews)

    @Delete
    suspend fun deleteFavoriteNews(favoriteNews: FavoriteNews)

    @Query("SELECT * FROM favoritenews")
    fun observeFavoriteNews() : LiveData<List<FavoriteNews>>

    @Query("SELECT * FROM favoritenews WHERE id = :NewsId")
    fun getAFavNews (NewsId : Int) : LiveData<FavoriteNews>

    @Query("SELECT * FROM favoritenews WHERE newsUrl = :Newsurl")
    fun saveOrNotNews(Newsurl : String) : LiveData<FavoriteNews?>
}