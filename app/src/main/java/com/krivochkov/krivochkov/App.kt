package com.krivochkov.krivochkov

import android.app.Application
import androidx.fragment.app.Fragment
import com.krivochkov.krivochkov.di.application.ApplicationComponent
import com.krivochkov.krivochkov.di.application.DaggerApplicationComponent

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}

fun Fragment.appComponent() = (requireActivity().application as App).applicationComponent