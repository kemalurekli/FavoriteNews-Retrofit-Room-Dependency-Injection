package com.kemalurekli.firstapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.databinding.FragmentDetailsBinding
import com.kemalurekli.firstapi.databinding.FragmentFavNewsDetailsBinding
import com.kemalurekli.firstapi.viewmodel.FavNewsDetailsFragmentViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import javax.inject.Inject


class FavNewsDetailsFragment @Inject constructor(
    private val glide : RequestManager
): Fragment() {

    private var _binding : FragmentFavNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FavNewsDetailsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FavNewsDetailsFragmentViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavNewsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsArgument = arguments?.get("clickedItem").toString().toInt()
            viewModel.showSavedNewsDetails(newsArgument)
                .observe(viewLifecycleOwner, Observer { news->
                    binding.tvSavedTitleDetails.text = news.newsTitle
                    binding.tvSavedContentDetails.text = news.newsContent
                    binding.tvSavedSourceDetails.text = news.newsSource
                    binding.tvSavedDateDetails.text = news.newsDate
                    glide.load(news.newsImageUrl).into(binding.ivSavedDetail)
                    binding.btnWebSource.setOnClickListener {
                        Navigation.findNavController(it).navigate(FavNewsDetailsFragmentDirections.actionFavNewsDetailsFragmentToNewsWebViewFragment(news.newsUrl))
                    }
                    binding.btnDeleteNews.setOnClickListener {
                        viewModel.deleteNews(news)
                        Navigation.findNavController(it).navigate(FavNewsDetailsFragmentDirections.actionGlobalFavNewsFragment())
                        Toast.makeText(requireContext(), "Deleted the News", Toast.LENGTH_SHORT).show()

                    }
                })





    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}