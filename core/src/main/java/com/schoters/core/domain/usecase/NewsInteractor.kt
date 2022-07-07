package com.schoters.core.domain.usecase

import com.schoters.core.data.source.NewsRepository
import com.schoters.core.data.source.Resource
import com.schoters.core.domain.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: NewsRepository) : NewsUseCase {

    override fun getNews(isOnline: Boolean): Flow<Resource<List<News>>> =
        newsRepository.getNews(isOnline)

    override fun getHeadlinesNews(isOnline: Boolean): Flow<Resource<List<News>>> = newsRepository.getHeadlinesNews(isOnline)

    override fun getSavedNewsFromDB(): Flow<List<News>> =
        newsRepository.getSavedNewsFromDB()

    override fun insertSavedNews(news: News) = newsRepository.insertSavedNews(news)

    override fun deleteSavedNews(news: News) = newsRepository.deleteSavedNews(news)

}