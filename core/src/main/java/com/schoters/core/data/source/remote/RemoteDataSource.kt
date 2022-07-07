package com.schoters.core.data.source.remote

import android.util.Log
import com.schoters.core.BuildConfig
import com.schoters.core.data.source.remote.network.ApiResponse
import com.schoters.core.data.source.remote.network.ApiService
import com.schoters.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    val apiKey = BuildConfig.API_KEY

    suspend fun getNews(): Flow<ApiResponse<List<NewsResponse>>> {
        return flow {
            try {
                val response = apiService.getNews(apiKey = apiKey)
                val listNews = response.listNews
                Log.d("Remote Data Source", listNews.toString())
                if (listNews.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listNews))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getHeadlinesNews(): Flow<ApiResponse<List<NewsResponse>>> {
        return flow {
            try {
                val response = apiService.getHeadlinesNews(apiKey = apiKey)
                val listNews = response.listNews
                Log.d("Remote Data Source", listNews.toString())
                if (listNews.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listNews))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}