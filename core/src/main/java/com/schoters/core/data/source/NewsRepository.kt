package com.schoters.core.data.source

import com.schoters.core.data.source.local.LocalDataSource
import com.schoters.core.data.source.remote.RemoteDataSource
import com.schoters.core.data.source.remote.network.ApiResponse
import com.schoters.core.data.source.remote.response.NewsResponse
import com.schoters.core.domain.model.News
import com.schoters.core.domain.repository.INewsRepository
import com.schoters.core.utils.AppExecutors
import com.schoters.core.utils.DataMapperNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {

    override fun getNews(isOnline: Boolean): Flow<Resource<List<News>>> =
        object :
            NetworkBoundResource<List<News>, List<NewsResponse>>(isOnline) {
            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getNews()

            override suspend fun changeTypeResult(data: List<NewsResponse>): List<News> {
                return DataMapperNews.mapResponsesToDomain(data)
            }

        }.asFlow()

    override fun getHeadlinesNews(isOnline: Boolean): Flow<Resource<List<News>>> =
        object :
            NetworkBoundResource<List<News>, List<NewsResponse>>(isOnline) {
            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getHeadlinesNews()

            override suspend fun changeTypeResult(data: List<NewsResponse>): List<News> {
                return DataMapperNews.mapResponsesToDomain(data)
            }

        }.asFlow()

    override fun getSavedNewsFromDB(): Flow<List<News>> {
        return localDataSource.getSavedNewsFromDB().map {
            DataMapperNews.mapEntitiesToDomain(it)
        }
    }

    override fun insertSavedNews(news: News) {
        val newsEntity = DataMapperNews.mapDomainToEntity(news)
        appExecutors.diskIO()
            .execute { localDataSource.insertSavedNews(newsEntity) }
    }

    override fun deleteSavedNews(news: News) {
        val newsEntity = DataMapperNews.mapDomainToEntity(news)
        appExecutors.diskIO()
            .execute { localDataSource.deleteSavedNews(newsEntity) }
    }


}