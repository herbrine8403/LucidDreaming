package com.luciddreaming.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luciddreaming.android.data.preferences.SettingsPreferences
import com.luciddreaming.android.data.preferences.AppSettings
import com.luciddreaming.android.data.preferences.ConnectionPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val settingsPreferences = SettingsPreferences(application)
    private val connectionPreferences = ConnectionPreferences(application)

    private val _appSettings = MutableStateFlow(AppSettings())
    val appSettings: StateFlow<AppSettings> = _appSettings.asStateFlow()

    private val _showUnbindDialog = MutableStateFlow(false)
    val showUnbindDialog: StateFlow<Boolean> = _showUnbindDialog.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            settingsPreferences.settings.collect { settings ->
                _appSettings.value = settings
            }
        }
    }

    fun updateRefreshInterval(interval: Int) {
        viewModelScope.launch {
            settingsPreferences.updateRefreshInterval(interval)
        }
    }

    fun showUnbindDialog() {
        _showUnbindDialog.value = true
    }

    fun hideUnbindDialog() {
        _showUnbindDialog.value = false
    }

    fun unbindConnection() {
        viewModelScope.launch {
            connectionPreferences.clearConnectionSettings()
            hideUnbindDialog()
        }
    }
}
