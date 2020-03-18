package com.example.newsapp.api

import com.example.newsapp.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApi {         //to build singleton

    private val newsApiInterface: NewsApiInterface

    companion object{
        const val BASE_URL = "http://newsapi.org/v2/"
    }
    init {  //build  retrofit
           val retrofit = Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()

        newsApiInterface = retrofit.create(NewsApiInterface::class.java)
    }

    fun getTopHeadLine(category: String): Call<News>{
        return newsApiInterface.getTopHeadLine(category)
    }
}