package com.schoters.newsapp.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.schoters.core.domain.model.News
import com.schoters.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    fun getSavedNewsFromDB(): LiveData<List<News>> {
        return newsUseCase.getSavedNewsFromDB().asLiveData()
    }

    fun deleteSavedNews(news: News) = newsUseCase.deleteSavedNews(news)
}