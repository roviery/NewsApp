package com.schoters.newsapp.headline

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.schoters.core.data.source.Resource
import com.schoters.core.domain.model.News
import com.schoters.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    fun getHeadlinesNews(query: String?, isOnline: Boolean): LiveData<Resource<List<News>>> {
        return newsUseCase.getHeadlinesNews(isOnline).asLiveData()
    }

}