package com.krivochkov.tinkoff_lab.data.remote.dto

data class Result(
    val result: List<PostDto>,
    val totalCount: Int
)