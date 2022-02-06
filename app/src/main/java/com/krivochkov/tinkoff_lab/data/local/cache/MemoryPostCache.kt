package com.krivochkov.tinkoff_lab.data.local.cache

import com.krivochkov.tinkoff_lab.domain.model.Post
import java.util.concurrent.ConcurrentHashMap

class MemoryPostCache : PostCache {

    private val map = ConcurrentHashMap<String, Post>()

    override fun put(key: String, value: Post) {
        map[key] = value
    }

    override fun get(key: String): Post? {
        return if (map.containsKey(key)) {
            map[key]
        } else {
            null
        }
    }

    override fun size() = map.size
}