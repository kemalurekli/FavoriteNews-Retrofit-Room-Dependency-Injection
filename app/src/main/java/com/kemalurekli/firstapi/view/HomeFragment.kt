package com.kemalurekli.firstapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.adapter.FavNewsRecyclerAdapter
import com.kemalurekli.firstapi.adapter.NewsRecyclerAdapter
import com.kemalurekli.firstapi.databinding.FragmentHomeBinding
import com.kemalurekli.firstapi.model.NewsResult
import com.kemalurekli.firstapi.util.Status
import com.kemalurekli.firstapi.viewmodel.HomeFragmentViewModel
import javax.inject.Inject


class HomeFragment @Inject constructor(
    private val glide : RequestManager,
    private val newsRecyclerAdapter: NewsRecyclerAdapter
) : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeFragmentViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[HomeFragmentViewModel::class.java]

        subscribeToObservers()
        binding.rvNews.adapter = newsRecyclerAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())


        val view = binding.root
        return view
    }


    private fun subscribeToObservers() {
        viewModel.getDataFromApi()
        viewModel.newsListFromApi.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireActivity(),"Success",Toast.LENGTH_LONG).show()
                   val newsLists =  it.data?.result
                    if (newsLists != null) {
                        newsRecyclerAdapter.news = newsLists
                    }
                    binding.progressBar.visibility = View.GONE
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE

                }

                Status.LOADING -> {
                    Toast.makeText(requireContext(),it.message ?: "Loading",Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.VISIBLE


                }
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}