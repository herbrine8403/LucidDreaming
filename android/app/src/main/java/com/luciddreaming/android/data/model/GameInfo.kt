package com.luciddreaming.android.data.model

import com.google.gson.annotations.SerializedName

data class GameInfo(
    @SerializedName("player")
    val player: PlayerInfo,

    @SerializedName("game")
    val game: GameDetails,

    @SerializedName("server")
    val server: ServerInfo,

    @SerializedName("scoreboard")
    val scoreboard: List<String>,

    @SerializedName("system")
    val system: SystemInfo
)

data class PlayerInfo(
    @SerializedName("name")
    val name: String,

    @SerializedName("uuid")
    val uuid: String,

    @SerializedName("health")
    val health: Double,

    @SerializedName("maxHealth")
    val maxHealth: Double,

    @SerializedName("hunger")
    val hunger: Int,

    @SerializedName("saturation")
    val saturation: Double,

    @SerializedName("experienceLevel")
    val experienceLevel: Int,

    @SerializedName("experienceProgress")
    val experienceProgress: Double,

    @SerializedName("position")
    val position: String,

    @SerializedName("dimension")
    val dimension: String,

    @SerializedName("gameMode")
    val gameMode: String
)

data class GameDetails(
    @SerializedName("minecraftVersion")
    val minecraftVersion: String,

    @SerializedName("forgeVersion")
    val forgeVersion: String,

    @SerializedName("modVersion")
    val modVersion: String,

    @SerializedName("uptime")
    val uptime: String,

    @SerializedName("uptimeSeconds")
    val uptimeSeconds: Long,

    @SerializedName("fps")
    val fps: Int,

    @SerializedName("currentTime")
    val currentTime: String
)

data class ServerInfo(
    @SerializedName("type")
    val type: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("name")
    val name: String
)

data class SystemInfo(
    @SerializedName("osName")
    val osName: String,

    @SerializedName("osVersion")
    val osVersion: String,

    @SerializedName("javaVersion")
    val javaVersion: String
)