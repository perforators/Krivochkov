package com.krivochkov.tinkoff_lab.data.local.cache

import com.krivochkov.tinkoff_lab.domain.model.Post

interface PostCache {

    fun put(key: String, value: Post)

    operator fun get(key: String): Post?

    fun size(): Int
}