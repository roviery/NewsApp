package com.schoters.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.schoters.core.R
import com.schoters.core.databinding.NewsCardBinding
import com.schoters.core.domain.model.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun getData(position: Int): News = listData[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = NewsCardBinding.bind(itemView)
        fun bind(data: News) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val date = dateFormat.parse(data.publishedAt)
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val dateStr = formatter.format(date)

            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(newsImage)
                binding.newsTitle.text = data.title
                binding.newsName.text = data.name
                binding.newsAuthor.text = data.author
                binding.newsPublishedAt.text = dateStr
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}