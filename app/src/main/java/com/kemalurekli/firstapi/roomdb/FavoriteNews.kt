package com.kemalurekli.firstapi.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favoritenews")
data class FavoriteNews(
    var newsTitle : String,
    var newsContent : String,
    var newsImageUrl : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
)
