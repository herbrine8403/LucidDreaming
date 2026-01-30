package com.luciddreaming.android.data.api

import com.luciddreaming.android.data.model.GameInfo
import retrofit2.Response
import retrofit2.http.GET

interface GameInfoApi {
    @GET("api/json")
    suspend fun getGameInfo(): Response<GameInfo>
}