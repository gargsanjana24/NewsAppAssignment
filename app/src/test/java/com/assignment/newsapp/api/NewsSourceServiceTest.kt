package com.assignment.newsapp.api

import com.assignment.newsapp.core.utils.create
import com.assignment.newsapp.news.api.NewsService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@RunWith(JUnit4::class)
class NewsSourceServiceTest : BaseServiceTest() {

    private lateinit var service: NewsService

    @Before
    @Throws(IOException::class)
    fun createService() {
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun getNewsSource() = runBlocking {
        enqueueResponse("news_source.json")
        val response = service.getTopHeadlines().body() ?: return@runBlocking

        // Dummy request
        mockWebServer.takeRequest()

        // Check news source
        assertThat(response, notNullValue())
        assertThat(response.totalResults, `is`(69))
        assertThat(response.status, `is`("ok"))

        // Check list
        val articles = response.articles
        assertThat(articles, notNullValue())

        // Check item 1
        val article1 = articles[0]
        assertThat(article1, notNullValue())
        assertThat(article1.author, `is`("Lydia DePillis"))
        assertThat(article1.title, `is`("June Jobs Report Shows Strong Growth: Latest News - The New York Times"))
        assertThat(article1.description, `is`("The hotter-than-expected boost to the labor market eases worries of an economic slowdown but complicates efforts to fight inflation"))
        assertThat(article1.source.name, `is`("New York Times"))
    }
}