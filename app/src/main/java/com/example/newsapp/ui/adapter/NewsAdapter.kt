package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(var articleList: List<Article> = ArrayList()) :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){
    inner class  ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindArticle(article: Article){

            Picasso.get().load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.articleImg)

            itemView.articleTitle.text = article.title
            itemView.articleDescription.text = article.description

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ArticleViewHolder(view)

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindArticle(articleList.get(position))
    }

    fun updateList(article: List<Article>){
        this.articleList = article
        notifyDataSetChanged()
    }
}