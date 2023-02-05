package com.krivochkov.krivochkov.data.remote.api

import com.krivochkov.krivochkov.data.remote.responses.FilmDescriptionResponse
import com.krivochkov.krivochkov.data.remote.responses.TopFilmsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsApi {
    @GET("films/top")
    fun fetchPopularFilms(
        @Query("page") pageNum: Int,
        @Query("type") type: String = DEFAULT_TYPE
    ): Single<TopFilmsResponse>

    @GET("films/{id}")
    fun fetchFilmDescription(@Path("id") filmId: Int): Single<FilmDescriptionResponse>

    companion object {
        private const val DEFAULT_TYPE = "TOP_100_POPULAR_FILMS"
    }
}