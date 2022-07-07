package com.schoters.core.domain.usecase

import com.schoters.core.data.source.Resource
import com.schoters.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    fun getNews(isOnline: Boolean): Flow<Resource<List<News>>>

    fun getHeadlinesNews(isOnline: Boolean): Flow<Resource<List<News>>>

    fun getSavedNewsFromDB(): Flow<List<News>>

    fun insertSavedNews(news: News)

    fun deleteSavedNews(news: News)

}