package com.example.newsapp.viewmoel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.NewsApi
import com.example.newsapp.model.News
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ArticleViewModel : ViewModel(){

    var results: MutableLiveData<News> = MutableLiveData()       //MutableLiveData = can change data

    fun getResult(): LiveData<News> = results           //call this fun in fragment or main_activity

    private val newsApi: NewsApi = NewsApi()        //build obj so init block is running in 'NewsApi Class'
    fun loadResult(category: String){

        val apiCall = newsApi.getTopHeadLine((category))
        apiCall.enqueue(object : retrofit2.Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Failure>>>>>",t.toString())

            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                response.isSuccessful.let {         // isSuccessful to check error
                    val resultList = News(              //if the data is null in article class , we must be check the condition
                        response.body()?.articles ?: emptyList()    //?: elvis operator is like ternery operator , emptyList() there is no data in it
                    )

                    Log.d("Response>>>>>>>",resultList.toString())
                    results.value = resultList      //because of mutable we use value

                }
            }

        })

    }

}