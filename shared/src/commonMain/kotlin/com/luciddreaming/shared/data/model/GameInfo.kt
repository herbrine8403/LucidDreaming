package com.luciddreaming.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GameInfo(
    val serverName: String,
    val version: String,
    val playerCount: Int,
    val uptime: Long,
    val mapName: String,
    val gameMode: String
)
