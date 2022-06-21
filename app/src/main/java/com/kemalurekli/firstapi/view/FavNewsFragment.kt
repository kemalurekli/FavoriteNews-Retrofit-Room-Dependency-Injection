package com.kemalurekli.firstapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.adapter.FavNewsRecyclerAdapter
import com.kemalurekli.firstapi.adapter.NewsRecyclerAdapter
import com.kemalurekli.firstapi.databinding.FragmentFavNewsBinding
import com.kemalurekli.firstapi.databinding.FragmentHomeBinding
import com.kemalurekli.firstapi.util.Status
import com.kemalurekli.firstapi.viewmodel.FavNewsFragmentViewModel
import com.kemalurekli.firstapi.viewmodel.HomeFragmentViewModel
import javax.inject.Inject

class FavNewsFragment @Inject constructor(
    private val glide : RequestManager,
    private val favnewsRecyclerAdapter: FavNewsRecyclerAdapter
) : Fragment() {

    private var _binding : FragmentFavNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FavNewsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavNewsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[FavNewsFragmentViewModel::class.java]

        binding.rvFavNews.adapter = favnewsRecyclerAdapter
        binding.rvFavNews.layoutManager = LinearLayoutManager(requireContext())


        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSavedNewsFromRoom()
    }




    private fun getSavedNewsFromRoom() {
        viewModel.NewsListFromRoom.observe(viewLifecycleOwner, Observer {
            val newsLists =  it
            if (newsLists != null) {
                favnewsRecyclerAdapter.news = newsLists
            }
        })
    }


}