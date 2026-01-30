package com.luciddreaming.android.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var baseUrl: String = "http://localhost:1122/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun updateBaseUrl(newBaseUrl: String) {
        baseUrl = newBaseUrl
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getBaseUrl(): String = baseUrl

    val gameInfoApi: GameInfoApi by lazy {
        retrofit.create(GameInfoApi::class.java)
    }

    val moduleApi: ModuleApi by lazy {
        retrofit.create(ModuleApi::class.java)
    }

    val screenshotApi: ScreenshotApi by lazy {
        retrofit.create(ScreenshotApi::class.java)
    }
}