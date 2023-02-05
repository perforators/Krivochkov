package com.krivochkov.krivochkov.data.local.cache

interface InMemoryCache<Key, Value> {
    fun put(key: Key, value: Value)
    fun getAllValues(): List<Value>
    operator fun get(key: Key): Value?
    operator fun contains(key: Key): Boolean
    fun size(): Int
}