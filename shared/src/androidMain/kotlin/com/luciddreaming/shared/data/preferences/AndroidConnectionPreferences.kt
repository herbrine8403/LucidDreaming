package com.luciddreaming.shared.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.connectionDataStore: DataStore<Preferences> by preferencesDataStore(name = "connection_preferences")

class AndroidConnectionPreferences(private val context: Context) : ConnectionPreferences {
    override val connectionSettings: Flow<ConnectionSettings> = context.connectionDataStore.data
        .map {
            ConnectionSettings(
                isConnected = it[IS_CONNECTED] ?: false,
                ipAddress = it[IP_ADDRESS] ?: "",
                port = it[PORT] ?: ""
            )
        }

    override suspend fun updateConnectionSettings(
        isConnected: Boolean,
        ipAddress: String,
        port: String
    ) {
        context.connectionDataStore.edit {
            it[IS_CONNECTED] = isConnected
            it[IP_ADDRESS] = ipAddress
            it[PORT] = port
        }
    }

    override suspend fun clearConnectionSettings() {
        context.connectionDataStore.edit {
            it.clear()
        }
    }

    companion object {
        private val IS_CONNECTED = booleanPreferencesKey("is_connected")
        private val IP_ADDRESS = stringPreferencesKey("ip_address")
        private val PORT = stringPreferencesKey("port")
    }
}
