package com.example.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragments.Entertainment
import com.example.fragments.Health
import com.example.fragments.Science
import com.example.fragments.Sports
import com.example.fragments.Technology
import java.lang.IllegalArgumentException

class BasicAdapter(fragment:FragmentActivity):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 5
    }
    override fun createFragment(position: Int): Fragment {
       return when(position){
           0->Technology()
           1-> Sports()
           2-> Health()
           3-> Science()
           4->Entertainment()
           else ->throw IllegalArgumentException("Invalid position $position")
       }
    }
}