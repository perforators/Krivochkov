package com.krivochkov.krivochkov.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import com.krivochkov.krivochkov.R
import com.krivochkov.krivochkov.databinding.FragmentFilmsBinding
import com.krivochkov.krivochkov.presentation.FilmsViewModel.FilmsCategory.FAVOURITE
import com.krivochkov.krivochkov.presentation.FilmsViewModel.FilmsCategory.POPULAR
import com.krivochkov.krivochkov.presentation.filmsbycategory.FilmsSharedViewModel

class FilmsFragment : Fragment(R.layout.fragment_films) {

    private val binding: FragmentFilmsBinding by viewBinding(FragmentFilmsBinding::bind)
    private val viewModel: FilmsViewModel by viewModels()
    private val filmsSharedViewModel: FilmsSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPager()
        initTab()

        viewModel.selectedCategory.observe(viewLifecycleOwner) { category ->
            binding.apply {
                if (category == POPULAR) {
                    textTitle.text = requireContext().getText(R.string.popular)
                    showHighlight(popularFilms)
                    hideHighlight(favouriteFilms)
                } else {
                    textTitle.text = requireContext().getText(R.string.favourite)
                    showHighlight(favouriteFilms)
                    hideHighlight(popularFilms)
                }
            }
            binding.pager.setCurrentItem(category.position, true)
        }

        filmsSharedViewModel.showFilmInfoEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    FilmsFragmentDirections.actionFilmsFragmentToFilmDetailsFragment2(it)
                )
            }
        }

        binding.favouriteFilms.setOnClickListener {
            viewModel.selectCategory(FAVOURITE)
        }
        binding.popularFilms.setOnClickListener {
            viewModel.selectCategory(POPULAR)
        }
    }

    override fun onStart() {
        super.onStart()
        initSearchLayout()
    }

    private fun initSearchLayout() {
        binding.search.searchView.addTextChangedListener {
            filmsSharedViewModel.sendSearchQuery(it?.toString().orEmpty())
        }
    }

    private fun initTab() {
        binding.searchButton.setOnClickListener {
            binding.titleLayout.isVisible = false
            binding.search.searchLayout.isVisible = true
        }
        binding.search.cancelButton.setOnClickListener {
            binding.search.searchLayout.isVisible = false
            binding.titleLayout.isVisible = true
        }
    }

    private fun initPager() {
        binding.pager.adapter = PagerStateAdapter(childFragmentManager, lifecycle)
        binding.pager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.selectCategoryByPosition(position)
            }
        })
    }

    private fun showHighlight(button: MaterialButton) {
        button.setBackgroundColor(getColor(R.color.blue_200))
        button.setTextColor(getColor(R.color.white))
    }

    private fun hideHighlight(button: MaterialButton) {
        button.setBackgroundColor(getColor(R.color.blue_100))
        button.setTextColor(getColor(R.color.blue_200))
    }
}