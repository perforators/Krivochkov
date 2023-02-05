package com.krivochkov.krivochkov.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.krivochkov.krivochkov.presentation.filmsbycategory.favouritefilms.FavouriteFilmsFragment
import com.krivochkov.krivochkov.presentation.filmsbycategory.topfilms.TopFilmsFragment

class PagerStateAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = COUNT_FRAGMENTS

    override fun createFragment(position: Int) = when (position) {
        0 -> TopFilmsFragment.newInstance()
        1 -> FavouriteFilmsFragment.newInstance()
        else -> error("Incorrect position")
    }

    companion object {
        private const val COUNT_FRAGMENTS = 2
    }
}