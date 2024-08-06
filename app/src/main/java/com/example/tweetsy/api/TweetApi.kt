package com.example.tweetsy.api

import com.example.tweetsy.models.TweetResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetApi {

    @GET("/v3/b/65433ed112a5d3765993bff3?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category:String): Response<List<TweetResponseItem>>

    @GET("/v3/b/65433ed112a5d3765993bff3?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategory():Response<List<String>>
}