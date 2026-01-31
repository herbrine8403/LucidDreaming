package com.luciddreaming.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luciddreaming.shared.data.preferences.AndroidConnectionPreferences
import com.luciddreaming.shared.data.preferences.AndroidSettingsPreferences
import com.luciddreaming.shared.data.preferences.AppSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val settingsPreferences = AndroidSettingsPreferences(application)
    private val connectionPreferences = AndroidConnectionPreferences(application)

    private val _settings = MutableStateFlow(AppSettings())
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            settingsPreferences.settings.collect {
                _settings.value = it
            }
        }
    }

    fun updateRefreshInterval(interval: Int) {
        viewModelScope.launch {
            val currentSettings = settingsPreferences.settings.first()
            settingsPreferences.updateSettings(
                refreshInterval = interval,
                autoRefresh = currentSettings.autoRefresh,
                darkMode = currentSettings.darkMode
            )
        }
    }

    fun updateAutoRefresh(enabled: Boolean) {
        viewModelScope.launch {
            val currentSettings = settingsPreferences.settings.first()
            settingsPreferences.updateSettings(
                refreshInterval = currentSettings.refreshInterval,
                autoRefresh = enabled,
                darkMode = currentSettings.darkMode
            )
        }
    }

    fun updateDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            val currentSettings = settingsPreferences.settings.first()
            settingsPreferences.updateSettings(
                refreshInterval = currentSettings.refreshInterval,
                autoRefresh = currentSettings.autoRefresh,
                darkMode = enabled
            )
        }
    }

    fun resetSettings() {
        viewModelScope.launch {
            settingsPreferences.resetSettings()
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            connectionPreferences.clearConnectionSettings()
        }
    }
}
