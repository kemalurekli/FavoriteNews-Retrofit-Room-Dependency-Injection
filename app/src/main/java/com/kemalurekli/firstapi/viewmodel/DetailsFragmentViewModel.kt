package com.kemalurekli.firstapi.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.*
import com.kemalurekli.firstapi.di.NewsRepositoryInterface
import com.kemalurekli.firstapi.roomdb.FavoriteNews
import com.kemalurekli.firstapi.view.DetailsFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
) : ViewModel() {




    private fun insertNews(favnews: FavoriteNews) = viewModelScope.launch {
        repository.insertNews(favnews)
    }

    fun saveRoom(Title : String, Content : String, ImageUrl : String,
                 Source : String, Date : String, Url : String){

        val dataSave = FavoriteNews(Title,Content,ImageUrl,Source,Date,Url)
        insertNews(dataSave)
    }

    fun checkTheNewsSaveOrNot (url : String) : LiveData<FavoriteNews?> {
        return repository.saveOrNot(url)
    }








}