package com.example.otchallenge.data.api

import com.example.otchallenge.data.api.response.NYTBooksResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val API_KEY = "KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB"
private const val BASE_URL = "https://api.nytimes.com/"

interface NYTBooksApiService {
    @GET("svc/books/v3/lists/current/hardcover-fiction.json")
    suspend fun getHardcoverFictionBooks(
        @Query("api-key") apiKey: String = API_KEY,
        @Query("offset") offset: Int = 0,
    ): NYTBooksResponse
}


object NYTBooksRepository {

    private val http = OkHttpClient().newBuilder()
        .connectTimeout(25, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val api: NYTBooksApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(http)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NYTBooksApiService::class.java)

    fun isApiError(jobStatus: String) =
        jobStatus != "OK"
}