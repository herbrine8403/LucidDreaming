package com.luciddreaming.android.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

class SettingsPreferences(private val context: Context) {

    private object PreferencesKeys {
        val REFRESH_INTERVAL = intPreferencesKey("refresh_interval")
    }

    val settings: Flow<AppSettings> = context.settingsDataStore.data.map { preferences ->
        AppSettings(
            refreshInterval = preferences[PreferencesKeys.REFRESH_INTERVAL] ?: 5
        )
    }

    suspend fun updateRefreshInterval(interval: Int) {
        context.settingsDataStore.edit { preferences ->
            preferences[PreferencesKeys.REFRESH_INTERVAL] = interval
        }
    }
}

data class AppSettings(
    val refreshInterval: Int = 5
)
