package com.kemalurekli.firstapi.viewmodel

import androidx.lifecycle.ViewModel
import com.kemalurekli.firstapi.di.NewsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavNewsFragmentViewModel @Inject constructor(
    private val repository: NewsRepositoryInterface
): ViewModel() {


    // This is for get saved data from Room. it should be in FavNewsFragmentViewModel!
    val NewsListFromRoom = repository.getNews()
}