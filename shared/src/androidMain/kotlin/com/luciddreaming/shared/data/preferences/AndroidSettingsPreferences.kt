package com.luciddreaming.shared.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

class AndroidSettingsPreferences(private val context: Context) : SettingsPreferences {
    override val settings: Flow<AppSettings> = context.settingsDataStore.data
        .map {
            AppSettings(
                refreshInterval = it[REFRESH_INTERVAL] ?: 10,
                autoRefresh = it[AUTO_REFRESH] ?: true,
                darkMode = it[DARK_MODE] ?: false
            )
        }

    override suspend fun updateSettings(
        refreshInterval: Int,
        autoRefresh: Boolean,
        darkMode: Boolean
    ) {
        context.settingsDataStore.edit {
            it[REFRESH_INTERVAL] = refreshInterval
            it[AUTO_REFRESH] = autoRefresh
            it[DARK_MODE] = darkMode
        }
    }

    override suspend fun resetSettings() {
        context.settingsDataStore.edit {
            it.clear()
        }
    }

    companion object {
        private val REFRESH_INTERVAL = intPreferencesKey("refresh_interval")
        private val AUTO_REFRESH = booleanPreferencesKey("auto_refresh")
        private val DARK_MODE = booleanPreferencesKey("dark_mode")
    }
}
