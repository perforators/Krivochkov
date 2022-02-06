package com.krivochkov.tinkoff_lab.di

import com.krivochkov.tinkoff_lab.data.local.cache.PostCache
import com.krivochkov.tinkoff_lab.data.local.cache.MemoryPostCache
import com.krivochkov.tinkoff_lab.data.remote.PostApi
import com.krivochkov.tinkoff_lab.data.repository.PostRepositoryImpl
import com.krivochkov.tinkoff_lab.domain.repository.PostRepository
import com.krivochkov.tinkoff_lab.domain.use_cases.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePostApi(): PostApi {
        return Retrofit.Builder()
            .baseUrl(PostApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApi,
        postCache: PostCache
    ): PostRepository {
        return PostRepositoryImpl(api, postCache)
    }

    @Provides
    @Singleton
    fun provideGetPostUseCase(repository: PostRepository): GetPostUseCase {
        return GetPostUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCache(): PostCache {
        return MemoryPostCache()
    }
}