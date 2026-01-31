package com.luciddreaming.shared.data.api

import com.luciddreaming.shared.data.model.GameInfo
import io.ktor.client.*
import io.ktor.client.request.*

class GameInfoApi(private val client: HttpClient) {
    suspend fun getGameInfo(): GameInfo {
        return client.get("/api/game/info")
    }
}
