package com.krivochkov.krivochkov.presentation.filmsbycategory.topfilms

import android.content.Context
import com.krivochkov.krivochkov.appComponent
import com.krivochkov.krivochkov.di.films.DaggerFilmsScreenComponent
import com.krivochkov.krivochkov.di.films.annotations.TopFilms
import com.krivochkov.krivochkov.presentation.filmsbycategory.FilmsByCategoryFragment
import com.krivochkov.krivochkov.presentation.filmsbycategory.FilmsByCategoryViewModelFactory
import javax.inject.Inject

class TopFilmsFragment : FilmsByCategoryFragment() {

    @Inject
    @TopFilms
    override lateinit var viewModelFactory: FilmsByCategoryViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFilmsScreenComponent.factory()
            .create(appComponent())
            .inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TopFilmsFragment()
    }
}