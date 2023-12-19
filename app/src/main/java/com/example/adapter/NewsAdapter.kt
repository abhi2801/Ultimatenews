package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.currentRecomposeScope
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.model.Article
import com.example.news.databinding.RowItemBinding


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsVH>(){
    inner class NewsVH(val binding:RowItemBinding):RecyclerView.ViewHolder(binding.root)
    private val diffutil=object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title==newItem.title
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }
    val differ=AsyncListDiffer(this,diffutil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        return NewsVH(RowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
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