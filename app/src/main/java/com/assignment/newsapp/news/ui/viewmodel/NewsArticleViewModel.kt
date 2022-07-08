package com.assignment.newsapp.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.assignment.newsapp.core.ui.ViewState
import com.assignment.newsapp.news.domain.NewsRepository
import com.assignment.newsapp.news.storage.entity.NewsArticleDb
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A container for [NewsArticleDb] related data to show on the UI.
 */
@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel() {

    private val newsArticleDb: LiveData<ViewState<List<NewsArticleDb>>> =
        newsRepository.getNewsArticles().asLiveData()

    /**
     * Return news articles to observeNotNull on the UI.
     */
    fun getNewsArticles(): LiveData<ViewState<List<NewsArticleDb>>> = newsArticleDb
}