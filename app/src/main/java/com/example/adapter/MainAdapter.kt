package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.model.Article
import com.example.news.databinding.NewsRowHeadingBinding

class MainAdapter :RecyclerView.Adapter<MainAdapter.MainVH>(){
        inner class MainVH(val binding:NewsRowHeadingBinding): RecyclerView.ViewHolder(binding.root)
        private val diffutil=object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title==newItem.title
            }
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem==newItem
            }
        }
        val differ= AsyncListDiffer(this,diffutil)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
            return MainVH(NewsRowHeadingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
        override fun getItemCount(): Int {
            return differ.currentList.size
        }

        override fun onBindViewHolder(holder: MainVH, position: Int) {
            val current=differ.currentList[position]
            holder.binding.apply {
                img.load(current.urlToImage){
                    crossfade(enable = true)
                    crossfade(2000)
                }
                title.text= current.title
                time.text=current.publishedAt
            }
        }

    }