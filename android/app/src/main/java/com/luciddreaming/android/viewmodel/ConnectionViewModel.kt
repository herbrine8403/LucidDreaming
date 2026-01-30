package com.luciddreaming.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luciddreaming.android.data.preferences.ConnectionPreferences
import com.luciddreaming.android.data.preferences.ConnectionSettings
import com.luciddreaming.android.data.repository.GameInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConnectionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GameInfoRepository()
    private val preferences = ConnectionPreferences(application)

    private val _connectionSettings = MutableStateFlow(ConnectionSettings())
    val connectionSettings: StateFlow<ConnectionSettings> = _connectionSettings.asStateFlow()

    private val _isConnecting = MutableStateFlow(false)
    val isConnecting: StateFlow<Boolean> = _isConnecting.asStateFlow()

    private val _connectionError = MutableStateFlow<String?>(null)
    val connectionError: StateFlow<String?> = _connectionError.asStateFlow()

    init {
        loadConnectionSettings()
    }

    private fun loadConnectionSettings() {
        viewModelScope.launch {
            preferences.connectionSettings.collect { settings ->
                _connectionSettings.value = settings
                if (settings.isConnected) {
                    repository.updateBaseUrl(settings.getBaseUrl())
                }
            }
        }
    }

    fun connect(ipAddress: String, port: String) {
        viewModelScope.launch {
            _isConnecting.value = true
            _connectionError.value = null

            val baseUrl = "http://$ipAddress:$port/"
            repository.updateBaseUrl(baseUrl)

            val result = repository.getGameInfo()
            if (result.isSuccess) {
                preferences.updateConnectionSettings(
                    isConnected = true,
                    ipAddress = ipAddress,
                    port = port
                )
                _connectionError.value = null
            } else {
                _connectionError.value = result.exceptionOrNull()?.message ?: "Connection failed"
            }

            _isConnecting.value = false
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            preferences.clearConnectionSettings()
            _connectionError.value = null
        }
    }

    fun clearError() {
        _connectionError.value = null
    }
}