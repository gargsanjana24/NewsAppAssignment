package com.assignment.newsapp.news.api

import com.assignment.newsapp.core.utils.Constants.Companion.API_KEY
import com.assignment.newsapp.core.utils.Constants.Companion.Category
import com.assignment.newsapp.core.utils.Constants.Companion.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Describes endpoints to fetch the news from NewsAPI.
 *
 * Read the documentation [here](https://newsapi.org/docs/v2)
 */
interface NewsService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("category")
        category: String = Category,
        @Query("country")
        country: String = Country
    ): Response<NewsResponse>

}

