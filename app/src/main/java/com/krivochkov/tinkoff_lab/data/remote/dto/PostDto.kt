package com.krivochkov.tinkoff_lab.data.remote.dto

import com.krivochkov.tinkoff_lab.domain.model.Post

data class PostDto(
    val author: String,
    val canVote: Boolean,
    val commentsCount: Int,
    val date: String,
    val description: String,
    val fileSize: Int,
    val gifSize: Int,
    val gifURL: String,
    val height: String,
    val id: Int,
    val previewURL: String,
    val type: String,
    val videoPath: String,
    val videoSize: Int,
    val videoURL: String,
    val votes: Int,
    val width: String
) {

    fun toPost() = Post(
        id = id,
        description = description,
        gifURL = gifURL
    )
}