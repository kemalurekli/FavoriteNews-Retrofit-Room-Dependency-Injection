package com.kemalurekli.firstapi.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteNews::class], version = 1)
abstract class FavoriteNewsDatabase : RoomDatabase() {

    abstract fun favoriteNewsDao() : FavoriteNewsDao

}