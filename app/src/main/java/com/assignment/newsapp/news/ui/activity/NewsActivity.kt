package com.assignment.newsapp.news.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.newsapp.core.ui.ViewState
import com.assignment.newsapp.core.ui.base.BaseActivity
import com.assignment.newsapp.core.utils.observeNotNull
import com.assignment.newsapp.core.utils.toast
import com.assignment.newsapp.databinding.ActivityMainBinding
import com.assignment.newsapp.news.ui.adapter.NewsArticlesAdapter
import com.assignment.newsapp.news.ui.viewmodel.NewsArticleViewModel


class NewsActivity : BaseActivity() {

    private val newsArticleViewModel: NewsArticleViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    /**
     * Starting point of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up RecyclerView and adapter
        binding.newsList.setEmptyView(binding.emptyLayout.emptyView)
        binding.newsList.setProgressView(binding.progressLayout.progressView)

        val adapter = NewsArticlesAdapter { toast("Clicked on item") }
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(this)
        // Update the UI on state change
        newsArticleViewModel.getNewsArticles().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> adapter.submitList(state.data)
                is ViewState.Loading -> binding.newsList.showLoading()
                is ViewState.Error -> toast("Something went wrong ¯\\_(ツ)_/¯ => ${state.message}")
            }
        }

    }
}
