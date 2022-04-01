package com.kemalurekli.firstapi.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.adapter.FavNewsRecyclerAdapter
import com.kemalurekli.firstapi.adapter.NewsRecyclerAdapter
import javax.inject.Inject

class NewsFragmentFactory@Inject constructor(
    private val glide : RequestManager,
    private val newsRecyclerAdapter: NewsRecyclerAdapter,
    private val favRecyclerAdapter: FavNewsRecyclerAdapter,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            HomeFragment::class.java.name -> HomeFragment(glide,newsRecyclerAdapter)
            DetailsFragment::class.java.name -> DetailsFragment(glide)
            FavNewsFragment::class.java.name -> FavNewsFragment(glide, favRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}