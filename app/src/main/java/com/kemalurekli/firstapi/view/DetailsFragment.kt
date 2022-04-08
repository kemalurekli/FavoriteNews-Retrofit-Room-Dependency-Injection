package com.kemalurekli.firstapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.RequestManager
import com.kemalurekli.firstapi.R
import com.kemalurekli.firstapi.databinding.FragmentDetailsBinding
import com.kemalurekli.firstapi.viewmodel.DetailsFragmentViewModel
import com.kemalurekli.firstapi.viewmodel.HomeFragmentViewModel
import javax.inject.Inject


class DetailsFragment @Inject constructor(
    private val glide : RequestManager
) : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : DetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[DetailsFragmentViewModel::class.java]

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title : String = arguments?.get("title").toString()
        val content : String = arguments?.get("content").toString()
        val source : String = arguments?.get("source").toString()
        val date : String = arguments?.get("date").toString()
        val imageUrl : String = arguments?.get("imageurl").toString()
        val url : String = arguments?.get("url").toString()

        binding.tvTitleDetails.text = title
        binding.tvContentDetails.text = content
        binding.tvSourceDetails.text = "Kaynak : $source"
        binding.tvDateDetails.text = date

        glide.load(imageUrl).into(binding.ivDetail).view


        binding.btnSaveDetails.setOnClickListener {
            viewModel.saveRoom(title,content,imageUrl,source,date,url)
            Toast.makeText(requireContext(), "News Saved!", Toast.LENGTH_LONG).show()
        }
        binding.btnWebDetail.setOnClickListener {
            Navigation.findNavController(it).navigate(DetailsFragmentDirections.actionDetailsFragmentToNewsWebViewFragment(url))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}