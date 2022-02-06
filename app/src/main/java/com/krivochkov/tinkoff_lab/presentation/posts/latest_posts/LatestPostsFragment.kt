package com.krivochkov.tinkoff_lab.presentation.posts.latest_posts

import com.krivochkov.tinkoff_lab.presentation.posts.PostsFragment
import com.krivochkov.tinkoff_lab.util.LATEST_CATEGORY

class LatestPostsFragment : PostsFragment() {

    override val category: String
        get() = LATEST_CATEGORY

    companion object {
        @JvmStatic
        fun newInstance() = LatestPostsFragment()
    }
}