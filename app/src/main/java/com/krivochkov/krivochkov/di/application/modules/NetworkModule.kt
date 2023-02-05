package com.krivochkov.krivochkov.di.application.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.krivochkov.krivochkov.data.remote.api.FilmsApi
import com.krivochkov.krivochkov.data.remote.api.HeaderInterceptor
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideFilmsApi(retrofit: Retrofit): FilmsApi {
        return retrofit.create(FilmsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dispatcher: Dispatcher
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideOkHttpDispatcher(): Dispatcher {
        return Dispatcher().apply {
            maxRequestsPerHost = MAX_REQUEST_PER_HOST
        }
    }

    @Provides
    fun provideJsonConverterFactory(json: Json): Converter.Factory {
        return json.asConverterFactory(CONTENT_TYPE.toMediaType())
    }

    @Provides
    fun provideRxJavaFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor(API_KEY)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    companion object {
        private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
        private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"
        private const val CONTENT_TYPE = "application/json"
        private const val MAX_REQUEST_PER_HOST = 5
    }
}