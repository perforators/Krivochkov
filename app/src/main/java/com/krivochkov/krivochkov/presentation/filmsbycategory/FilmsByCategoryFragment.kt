package com.krivochkov.krivochkov.presentation.filmsbycategory

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krivochkov.krivochkov.R
import com.krivochkov.krivochkov.databinding.FragmentFilmsByCategoryBinding
import com.krivochkov.krivochkov.domain.model.Film
import com.krivochkov.krivochkov.presentation.filmsbycategory.adapters.FilmsAdapter
import com.krivochkov.krivochkov.presentation.getColor
import javax.inject.Inject

abstract class FilmsByCategoryFragment : Fragment(R.layout.fragment_films_by_category) {

    private val binding: FragmentFilmsByCategoryBinding by viewBinding(
        FragmentFilmsByCategoryBinding::bind
    )
    private val viewModel: FilmsByCategoryViewModel by viewModels { viewModelFactory }
    private val filmsSharedViewModel: FilmsSharedViewModel by activityViewModels()

    internal abstract var viewModelFactory: FilmsByCategoryViewModelFactory
    @Inject
    internal lateinit var adapter: FilmsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::render)
        binding.error.errorButton.setOnClickListener {
            viewModel.repeatPreviousQuery()
        }
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.repeatPreviousQuery()
        }
        binding.swipeToRefresh.setColorSchemeColors(getColor(R.color.blue_200))
        adapter.onFilmClick = {
            filmsSharedViewModel.showFilmInfo(it)
        }
        adapter.onLongClick = {
            viewModel.addFilmToFavourites(it)
        }
        binding.films.adapter = adapter
        binding.films.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        filmsSharedViewModel.searchQuery.observe(viewLifecycleOwner) { singleEvent ->
            singleEvent.getContentIfNotHandled()?.let { query ->
                viewModel.searchFilms(query)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        filmsSharedViewModel.searchQuery.removeObservers(viewLifecycleOwner)
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
            is ScreenState.FilmsSucceedLoaded -> {
                hideLoading()
                showFilms(state.films)
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
        binding.swipeToRefresh.isRefreshing = false
    }

    private fun showError() {
        binding.error.errorLayout.isVisible = true
    }

    private fun hideError() {
        binding.error.errorLayout.isVisible = false
    }

    private fun showFilms(films: List<Film>) {
        if (films.isEmpty()) {
            binding.emptyData.isVisible = true
        } else {
            binding.films.isVisible = true
            adapter.submitFilms(films)
        }
    }

    private fun hideFilms() {
        binding.films.isVisible = false
        binding.emptyData.isVisible = false
    }
}