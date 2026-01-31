package com.luciddreaming.shared.data.repository

import com.luciddreaming.shared.data.api.GameInfoApi
import com.luciddreaming.shared.data.api.ModuleApi
import com.luciddreaming.shared.data.api.NetworkClient
import com.luciddreaming.shared.data.api.ScreenshotApi
import com.luciddreaming.shared.data.model.GameInfo
import com.luciddreaming.shared.data.model.Module
import com.luciddreaming.shared.data.model.ModuleResponse

class GameInfoRepository {
    private val client = NetworkClient.createClient()
    private val gameInfoApi = GameInfoApi(client)
    private val moduleApi = ModuleApi(client)
    private val screenshotApi = ScreenshotApi(client)

    suspend fun getGameInfo(): Result<GameInfo> {
        return try {
            val gameInfo = gameInfoApi.getGameInfo()
            Result.success(gameInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getModules(): Result<ModuleResponse> {
        return try {
            val modules = moduleApi.getModules()
            Result.success(modules)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun toggleModule(moduleName: String, action: String = "toggle"): Result<Module> {
        return try {
            val module = moduleApi.toggleModule(moduleName, action)
            Result.success(module)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getScreenshot(): Result<ByteArray> {
        return try {
            val screenshot = screenshotApi.getScreenshot()
            Result.success(screenshot)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun updateBaseUrl(baseUrl: String) {
        NetworkClient.updateBaseUrl(baseUrl)
    }

    fun getBaseUrl(): String {
        return NetworkClient.getBaseUrl()
    }
}
