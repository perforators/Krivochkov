package com.krivochkov.krivochkov.data.local.cache

import com.krivochkov.krivochkov.domain.model.Film
import java.util.concurrent.ConcurrentHashMap

class FilmsCache : InMemoryCache<Int, Film> {

    private val films = ConcurrentHashMap<Int, Film>()

    override fun put(key: Int, value: Film) {
        films[key] = value
    }

    override fun getAllValues() = films.values.toList()

    override fun get(key: Int) = films[key]

    override fun contains(key: Int) = films.containsKey(key)

    override fun size() = films.size
}