package com.krivochkov.tinkoff_lab.domain.use_cases

import com.krivochkov.tinkoff_lab.domain.model.Post
import com.krivochkov.tinkoff_lab.domain.repository.PostRepository
import com.krivochkov.tinkoff_lab.util.Resource
import kotlinx.coroutines.flow.Flow

class GetPostUseCase(
    private val repository: PostRepository
) {

    operator fun invoke(category: String, numPost: Int): Flow<Resource<Post>> {
        return repository.getPost(category, numPost)
    }
}