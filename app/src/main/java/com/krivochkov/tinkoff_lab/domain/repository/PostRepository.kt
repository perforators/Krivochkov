package com.krivochkov.tinkoff_lab.domain.repository

import com.krivochkov.tinkoff_lab.domain.model.Post
import com.krivochkov.tinkoff_lab.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPost(category: String, numPost: Int): Flow<Resource<Post>>
}