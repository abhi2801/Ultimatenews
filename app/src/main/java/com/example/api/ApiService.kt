package com.example.api

import com.example.model.NewsResponse
import com.example.utils.Constants.api_key
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun breakingNews(
        @Query("country") country: String = "in",
        @Query("page") page: Int=1,
        @Query("apiKey") key: String = api_key
    ): Response<NewsResponse>
}