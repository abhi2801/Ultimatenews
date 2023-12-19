package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.NewsViewModel.NewsViewModel
import com.example.adapter.BasicAdapter
import com.example.adapter.MainAdapter
import com.example.news.R
import com.example.news.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var basic:BasicAdapter
    private lateinit var mainAdapter:MainAdapter
    private val viewmodel:NewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentMainBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsCategories= listOf<String>("Technology","Sports","Health","Science","entertainment")
        basic=BasicAdapter(requireActivity())
        binding.pager.adapter=basic
        TabLayoutMediator(binding.tlayout,binding.pager) { tab, pos ->
            tab.text=newsCategories[pos]
        }.attach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}