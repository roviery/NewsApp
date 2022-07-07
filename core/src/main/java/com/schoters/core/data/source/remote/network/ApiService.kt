package com.schoters.core.data.source.remote.network

import com.schoters.core.data.source.remote.response.ListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // everything
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") q: String = "tech",
        @Query("page") page: Int = 1,
        @Query("domains") domains: String = "techcrunch.com",
        @Query("apiKey") apiKey: String
    ): ListNewsResponse

    @GET("/v2/top-headlines")
    suspend fun  getHeadlinesNews(
        @Query("page") page: Int = 1,
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String
    ): ListNewsResponse

}