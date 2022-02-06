package com.krivochkov.tinkoff_lab.presentation.adapters

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.krivochkov.tinkoff_lab.presentation.posts.hot_posts.HotPostsFragment
import com.krivochkov.tinkoff_lab.presentation.posts.latest_posts.LatestPostsFragment
import com.krivochkov.tinkoff_lab.presentation.posts.top_posts.TopPostsFragment
import com.krivochkov.tinkoff_lab.util.COUNT_FRAGMENTS
import kotlin.IllegalStateException

class PagerStateAdapter(
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = COUNT_FRAGMENTS

    override fun createFragment(position: Int) = when (position) {
        0 -> LatestPostsFragment.newInstance()
        1 -> TopPostsFragment.newInstance()
        2 -> HotPostsFragment.newInstance()
        else -> throw IllegalStateException("Incorrect position")
    }
}