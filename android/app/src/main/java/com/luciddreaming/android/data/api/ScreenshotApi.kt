package com.luciddreaming.android.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ScreenshotApi {
    @GET("api/screenshot")
    suspend fun getScreenshot(): Response<ResponseBody>
}