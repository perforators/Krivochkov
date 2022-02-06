package com.krivochkov.tinkoff_lab.data.remote

import retrofit2.http.GET
import com.krivochkov.tinkoff_lab.data.remote.dto.Result
import retrofit2.http.Path

interface PostApi {

    @GET("/{category}/{page}?json=true")
    suspend fun getPosts(
        @Path("category") category: String,
        @Path("page") page: Int
    ): Result

    companion object {
        const val BASE_URL = "http://developerslife.ru/"
    }
}