package com.luciddreaming.shared.data.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType

class ScreenshotApi(private val client: HttpClient) {
    suspend fun getScreenshot(): ByteArray {
        val response = client.get("/api/screenshot") {
            accept(ContentType.Image.PNG)
        }
        return response.readBytes()
    }
}
