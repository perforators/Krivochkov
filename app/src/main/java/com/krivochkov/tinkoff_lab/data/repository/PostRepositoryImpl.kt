package com.krivochkov.tinkoff_lab.data.repository

import com.krivochkov.tinkoff_lab.data.local.cache.PostCache
import com.krivochkov.tinkoff_lab.data.remote.PostApi
import com.krivochkov.tinkoff_lab.data.remote.dto.PostDto
import com.krivochkov.tinkoff_lab.domain.model.Post
import com.krivochkov.tinkoff_lab.domain.repository.PostRepository
import com.krivochkov.tinkoff_lab.util.HOT_CATEGORY
import com.krivochkov.tinkoff_lab.util.LATEST_CATEGORY
import com.krivochkov.tinkoff_lab.util.Resource
import com.krivochkov.tinkoff_lab.util.TOP_CATEGORY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl(
    private val api: PostApi,
    private val postCache: PostCache
) : PostRepository {

    private val currentPages = mutableMapOf(
        LATEST_CATEGORY to 0,
        TOP_CATEGORY to 0,
        HOT_CATEGORY to 0
    )

    override fun getPost(category: String, numPost: Int) = flow {
        emit(Resource.Loading())
        delay(100)
        try {
            val key = category + numPost
            val post: Post = if (postCache[key] != null) {
                postCache[key]!!
            } else {
                val currentPage = currentPages[category]!!
                val posts = api.getPosts(category, currentPage)
                cachePosts(posts.result, category, numPost)
                currentPages[category] = currentPage + 1
                postCache[key]!!
            }

            emit(Resource.Success(post))
        } catch(e: Exception) {
            emit(Resource.Error(
                message = e.toString()
            ))
        }
    }

    private fun cachePosts(posts: List<PostDto>, category: String, numPost: Int) {
        var postIndex = numPost

        for (post in posts) {
            val key = category + postIndex
            postCache.put(key, post.toPost())
            postIndex++
        }
    }
}