package com.example.tweetsy.repository

import com.example.tweetsy.api.TweetApi
import com.example.tweetsy.models.TweetResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetApi: TweetApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())

    val categories : StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetResponseItem>>(emptyList())

    val tweets : StateFlow<List<TweetResponseItem>>
        get() = _tweets


    suspend fun getCategory(){
        val respone = tweetApi.getCategory()
        if(respone.isSuccessful && respone.body() != null){
            _categories.emit(respone.body()!!.distinct())
        }
    }

    suspend fun getTweets(category:String){
        val respone = tweetApi.getTweets("tweets[?(@.category==\"$category\")]")
        if(respone.isSuccessful && respone.body() != null){
            _tweets.emit(respone.body()!!)
        }
    }
}