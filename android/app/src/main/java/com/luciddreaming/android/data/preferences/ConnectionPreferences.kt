package com.luciddreaming.android.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "connection_settings")

class ConnectionPreferences(private val context: Context) {

    private object PreferencesKeys {
        val IS_CONNECTED = booleanPreferencesKey("is_connected")
        val IP_ADDRESS = stringPreferencesKey("ip_address")
        val PORT = stringPreferencesKey("port")
    }

    val connectionSettings: Flow<ConnectionSettings> = context.dataStore.data.map { preferences ->
        ConnectionSettings(
            isConnected = preferences[PreferencesKeys.IS_CONNECTED] ?: false,
            ipAddress = preferences[PreferencesKeys.IP_ADDRESS] ?: "",
            port = preferences[PreferencesKeys.PORT] ?: "1122"
        )
    }

    suspend fun updateConnectionSettings(isConnected: Boolean, ipAddress: String, port: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_CONNECTED] = isConnected
            preferences[PreferencesKeys.IP_ADDRESS] = ipAddress
            preferences[PreferencesKeys.PORT] = port
        }
    }

    suspend fun clearConnectionSettings() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

data class ConnectionSettings(
    val isConnected: Boolean = false,
    val ipAddress: String = "",
    val port: String = "1122"
) {
    fun getBaseUrl(): String {
        return "http://$ipAddress:$port/"
    }
}