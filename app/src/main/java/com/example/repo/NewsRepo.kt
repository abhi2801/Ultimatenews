package com.example.repo

import com.example.api.ApiService
import javax.inject.Inject

class NewsRepo @Inject constructor(private val api:ApiService) {

    suspend fun getBreakingNews(country:String)= api.breakingNews(country)

}