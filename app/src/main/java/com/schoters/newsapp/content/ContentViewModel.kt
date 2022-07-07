package com.schoters.newsapp.content

import androidx.lifecycle.ViewModel
import com.schoters.core.domain.model.News
import com.schoters.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    fun insertSavedNews(news: News) {
        return newsUseCase.insertSavedNews(news)
    }

}