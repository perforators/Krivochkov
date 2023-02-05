package com.krivochkov.krivochkov.di.application.modules

import com.krivochkov.krivochkov.data.FilmsRepositoryImpl
import com.krivochkov.krivochkov.domain.FilmsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindFilmsRepository(impl: FilmsRepositoryImpl): FilmsRepository
}