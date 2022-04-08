package com.kemalurekli.firstapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kemalurekli.firstapi.di.NewsRepositoryInterface
import com.kemalurekli.firstapi.roomdb.FavoriteNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
) : ViewModel() {


    fun deleteArt(favnews: FavoriteNews) = viewModelScope.launch {
        repository.deleteNews(favnews)
    }

    fun insertArt(favnews: FavoriteNews) = viewModelScope.launch {
        repository.insertNews(favnews)
    }

    fun saveRoom(Title : String, Content : String, ImageUrl : String,
                 Source : String, Date : String, Url : String){

        val dataSave = FavoriteNews(Title,Content,ImageUrl,Source,Date,Url)
        insertArt(dataSave)
    }
}