package com.kemalurekli.firstapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kemalurekli.firstapi.di.NewsRepositoryInterface
import com.kemalurekli.firstapi.repository.NewsRepository
import com.kemalurekli.firstapi.roomdb.FavoriteNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavNewsDetailsFragmentViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
) : ViewModel() {


    fun showSavedNewsDetails (newsID : Int) : LiveData<FavoriteNews>{
        return repository.getFavNewsDetails(newsID)
    }


}