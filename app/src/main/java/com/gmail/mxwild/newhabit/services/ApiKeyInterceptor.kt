package com.gmail.mxwild.newhabit.services

import com.gmail.mxwild.newhabit.BuildConfig.API_KEY
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val modifyUrl: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = originalRequest.newBuilder()
            .url(modifyUrl)
            .build()

        return chain.proceed(request)
    }
}