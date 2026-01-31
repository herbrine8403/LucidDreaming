package com.luciddreaming.shared.data.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ScreenshotApi(private val client: HttpClient) {
    suspend fun getScreenshot(): ByteArray {
        val response = client.get("/api/screenshot") {
            accept(io.ktor.http.ContentType.Image.PNG)
        }
        return response.readBytes()
    }
}
