package com.kemalurekli.firstapi.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favoritenews")
data class FavoriteNews(
    var newsTitle : String,
    var newsContent : String,
    var newsImageUrl : String,
    var newsSource : String,
    var newsDate : String,
    var newsUrl : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
)
