package com.krivochkov.tinkoff_lab.presentation.posts.top_posts

import com.krivochkov.tinkoff_lab.presentation.posts.PostsFragment
import com.krivochkov.tinkoff_lab.util.TOP_CATEGORY

class TopPostsFragment : PostsFragment() {

    override val category: String
        get() = TOP_CATEGORY

    companion object {
        @JvmStatic
        fun newInstance() = TopPostsFragment()
    }
}