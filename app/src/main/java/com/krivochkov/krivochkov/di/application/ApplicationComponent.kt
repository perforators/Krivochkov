package com.krivochkov.krivochkov.di.application

import android.content.Context
import com.krivochkov.krivochkov.di.application.modules.CoreModule
import com.krivochkov.krivochkov.di.application.modules.NetworkModule
import com.krivochkov.krivochkov.di.application.modules.PersistenceModule
import com.krivochkov.krivochkov.di.application.modules.RepositoryModule
import com.krivochkov.krivochkov.di.films.FilmsScreenDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    PersistenceModule::class,
    RepositoryModule::class,
    CoreModule::class
])
interface ApplicationComponent : FilmsScreenDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}