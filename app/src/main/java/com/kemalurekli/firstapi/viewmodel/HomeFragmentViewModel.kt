package com.kemalurekli.firstapi.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.di.NewsRepositoryInterface
import com.kemalurekli.firstapi.model.NewsResponse
import com.kemalurekli.firstapi.util.Resource
import com.kemalurekli.firstapi.view.HomeFragment
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
) : ViewModel() {

    // These data from Retrofit API

    private val news = MutableLiveData<Resource<NewsResponse>>()
    val newsListFromApi : LiveData<Resource<NewsResponse>>
        get() = news



    fun getDataFromApi (){
        news.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.newsDownload()
            news.value = response
        }
    }



}

