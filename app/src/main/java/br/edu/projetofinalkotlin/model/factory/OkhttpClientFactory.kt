package br.edu.projetofinalkotlin.model.factory

import br.edu.projetofinalkotlin.model.interceptor.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkhttpClientFactory {
    fun build(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(loggingInterceptor)

        readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
    }.build()

    private const val DEFAULT_TIMEOUT = 10L
}