package com.schoters.core.data.source.local

import com.schoters.core.data.source.local.entity.NewsEntity
import com.schoters.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {

    fun getSavedNewsFromDB(): Flow<List<NewsEntity>> = newsDao.getSavedNewsFromDB()

    fun insertSavedNews(news: NewsEntity) = newsDao.insertSavedNews(news)

    fun deleteSavedNews(news: NewsEntity) = newsDao.deleteSavedNews(news)
}