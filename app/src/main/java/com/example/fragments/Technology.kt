package com.example.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.NewsViewModel.NewsViewModel
import com.example.adapter.MainAdapter
import com.example.click
import com.example.hide
import com.example.news.databinding.FragmentTechnologyBinding
import com.example.show
import com.example.toast
import com.example.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Technology : Fragment() {
private val viewModel:NewsViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter
    private lateinit var binding:FragmentTechnologyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentTechnologyBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRec()
        binding.fab.click {
            toast("Fab Clicked",Toast.LENGTH_SHORT)
        }
        viewModel.newsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    requireContext().toast("loading")
                  binding.pb.show()
                }
                is Resource.Success -> {
                   binding.pb.hide()
                    it.data?.let { newsResponse ->
                        mainAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Failed -> {
                   binding.pb.hide()
                    Log.e("error", " Error :${it.msg.toString()}")
                }
            }
        }
    }
    private fun setUpRec() {
        mainAdapter=MainAdapter()
        binding.techRec.apply {
            setHasFixedSize(true)
            adapter=mainAdapter
        }
    }
}