package com.kemalurekli.firstapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.databinding.FragmentDetailsBinding
import javax.inject.Inject


class DetailsFragment @Inject constructor(
    private val glide : RequestManager
) : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitleDetails.text = arguments?.get("title").toString()
        binding.tvContentDetails.text = arguments?.get("content").toString()
        binding.tvSourceDetails.text = "Kaynak : ${arguments?.get("source").toString()}"
        binding.tvDateDetails.text = arguments?.get("date").toString()
        val imageUrl = arguments?.get("imageurl").toString()
        val url = arguments?.get("url").toString()

        glide.load(imageUrl).into(binding.ivDetail).view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}