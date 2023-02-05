package com.krivochkov.krivochkov.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val updatedRequest = request.newBuilder()
            .header(X_API_HEADER, apiKey)
            .build()
        return chain.proceed(updatedRequest)
    }

    companion object {
        private const val X_API_HEADER = "X-API-KEY"
    }
}