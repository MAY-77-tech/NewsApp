package com.example.newsapp.api

import com.example.newsapp.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiInterface {

    @Headers("X-Api-Key: c3e71d1ca036462089b9ce79b7a28414")
    @GET("top-headlines")
    fun getTopHeadLine(
        @Query("category") category: String
    ): Call<News>

}