package br.edu.projetofinalkotlin.model.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newUrl = request.url.newBuilder()
            .addQueryParameter("key", "b64f611ff3f948f9be8112306241304")
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}