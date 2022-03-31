package com.kemalurekli.firstapi.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.api.RetrofitAPI
import com.kemalurekli.firstapi.repository.NewsRepository
import com.kemalurekli.firstapi.roomdb.FavoriteNewsDao
import com.kemalurekli.firstapi.roomdb.FavoriteNewsDatabase
import com.kemalurekli.firstapi.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context) = Room.databaseBuilder(
        context,FavoriteNewsDatabase::class.java,"FavoriteNewsDB"
        ).build()


    @Singleton
    @Provides
    fun injectDao (database: FavoriteNewsDatabase) = database.favoriteNewsDao()


    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectDefaultRepo (dao : FavoriteNewsDao, api: RetrofitAPI) = NewsRepository(dao, api) as NewsRepositoryInterface



    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide
        .with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_error_24)
        )

}