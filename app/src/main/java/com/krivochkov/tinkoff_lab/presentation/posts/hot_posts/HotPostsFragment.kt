package com.krivochkov.tinkoff_lab.presentation.posts.hot_posts

import com.krivochkov.tinkoff_lab.presentation.posts.PostsFragment
import com.krivochkov.tinkoff_lab.util.HOT_CATEGORY

class HotPostsFragment : PostsFragment() {

    override val category: String
        get() = HOT_CATEGORY

    companion object {
        @JvmStatic
        fun newInstance() = HotPostsFragment()
    }
}