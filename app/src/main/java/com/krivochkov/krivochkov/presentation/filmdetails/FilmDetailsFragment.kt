package com.krivochkov.krivochkov.presentation.filmdetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krivochkov.krivochkov.R
import com.krivochkov.krivochkov.appComponent
import com.krivochkov.krivochkov.databinding.FragmentFilmDetailsBinding
import com.krivochkov.krivochkov.di.films.DaggerFilmsScreenComponent
import com.krivochkov.krivochkov.domain.model.Film
import com.krivochkov.krivochkov.presentation.loadImage
import javax.inject.Inject

class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    @Inject
    internal lateinit var filmDetailsViewModelFactory: FilmDetailsViewModelFactory

    private val binding: FragmentFilmDetailsBinding by viewBinding(FragmentFilmDetailsBinding::bind)
    private val viewModel: FilmDetailsViewModel by viewModels { filmDetailsViewModelFactory }
    private val args by navArgs<FilmDetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFilmsScreenComponent.factory()
            .create(appComponent())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::render)
        viewModel.fetchFilmInfo(args.filmId)
    }

    private fun render(state: ScreenState) {
        when (state) {
            ScreenState.Failure -> {
                hideLoading()
                showError()
            }
            ScreenState.Loading -> {
                hideError()
                hideFilms()
                showLoading()
            }
            is ScreenState.FilmSucceedLoaded -> {
                hideLoading()
                showFilms(state.film)
            }
            is ScreenState.Init -> {
                hideLoading()
                hideFilms()
                hideError()
            }
        }
    }

    private fun showLoading() {
        binding.loading.isVisible = true
    }

    private fun hideLoading() {
        binding.loading.isVisible = false
    }

    private fun showError() {
        binding.error.errorLayout.isVisible = true
    }

    private fun hideError() {
        binding.error.errorLayout.isVisible = false
    }

    private fun showFilms(film: Film) {
        binding.content.isVisible = true
        binding.name.text = film.name
        binding.description.text = film.description
        binding.poster.loadImage(film.posterUrl)
        binding.genres.text = film.genres.joinToString(", ")
        binding.countries.text = film.countries.joinToString(", ")
    }

    private fun hideFilms() {
        binding.content.isVisible = false
    }
}