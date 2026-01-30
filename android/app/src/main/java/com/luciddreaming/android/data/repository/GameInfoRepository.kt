package com.luciddreaming.android.data.repository

import com.luciddreaming.android.data.api.RetrofitClient
import com.luciddreaming.android.data.model.GameInfo
import com.luciddreaming.android.data.model.Module
import com.luciddreaming.android.data.model.ModuleResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class GameInfoRepository {

    suspend fun getGameInfo(): Result<GameInfo> {
        return try {
            val response = RetrofitClient.gameInfoApi.getGameInfo()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(IOException("Failed to get game info: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getModules(): Result<ModuleResponse> {
        return try {
            val response = RetrofitClient.moduleApi.getModules()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(IOException("Failed to get modules: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun toggleModule(moduleName: String, action: String = "toggle"): Result<Module> {
        return try {
            val json = "{\"action\":\"$action\"}"
            val requestBody = json.toRequestBody("application/json".toMediaType())
            val response = RetrofitClient.moduleApi.toggleModule(moduleName, requestBody)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(IOException("Failed to toggle module: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getScreenshot(): Result<ByteArray> {
        return try {
            val response = RetrofitClient.screenshotApi.getScreenshot()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.bytes())
            } else {
                Result.failure(IOException("Failed to get screenshot: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun updateBaseUrl(baseUrl: String) {
        RetrofitClient.updateBaseUrl(baseUrl)
    }

    fun getBaseUrl(): String {
        return RetrofitClient.getBaseUrl()
    }
}