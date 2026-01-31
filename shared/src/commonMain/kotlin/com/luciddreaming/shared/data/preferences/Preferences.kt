package com.luciddreaming.shared.data.preferences

import kotlinx.coroutines.flow.Flow

interface ConnectionPreferences {
    val connectionSettings: Flow<ConnectionSettings>
    suspend fun updateConnectionSettings(
        isConnected: Boolean,
        ipAddress: String,
        port: String
    )
    suspend fun clearConnectionSettings()
}

interface SettingsPreferences {
    val settings: Flow<AppSettings>
    suspend fun updateSettings(
        refreshInterval: Int,
        autoRefresh: Boolean,
        darkMode: Boolean
    )
    suspend fun resetSettings()
}

data class ConnectionSettings(
    val isConnected: Boolean = false,
    val ipAddress: String = "",
    val port: String = ""
) {
    fun getBaseUrl(): String {
        return "http://$ipAddress:$port"
    }
}

data class AppSettings(
    val refreshInterval: Int = 10,
    val autoRefresh: Boolean = true,
    val darkMode: Boolean = false
)
