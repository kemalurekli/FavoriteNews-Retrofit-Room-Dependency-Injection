package com.kemalurekli.firstapi.roomdb

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["newsUrl"], unique = true)])
data class FavoriteNews(
    var newsTitle : String,
    var newsContent : String,
    var newsImageUrl : String,
    var newsSource : String,
    var newsDate : String,
    var newsUrl : String,
    val savedorNot : Boolean = true,
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
)
