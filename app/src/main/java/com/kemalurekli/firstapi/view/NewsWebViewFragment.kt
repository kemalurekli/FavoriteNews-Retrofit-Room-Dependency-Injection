package com.kemalurekli.firstapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.databinding.FragmentNewsWebViewBinding

class NewsWebViewFragment : Fragment() {
    private var _binding: FragmentNewsWebViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsWebViewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.get("newsUrlForWeb").toString()
        binding.wvNews.loadUrl(url)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


