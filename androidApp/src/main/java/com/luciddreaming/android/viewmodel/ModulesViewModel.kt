package com.luciddreaming.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luciddreaming.shared.data.model.Module
import com.luciddreaming.shared.data.model.ModuleResponse
import com.luciddreaming.shared.data.preferences.AndroidSettingsPreferences
import com.luciddreaming.shared.data.repository.GameInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class ModulesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GameInfoRepository()
    private val settingsPreferences = AndroidSettingsPreferences(application)

    private val _modulesResponse = MutableStateFlow<ModuleResponse?>(null)
    val modulesResponse: StateFlow<ModuleResponse?> = _modulesResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var autoRefreshEnabled = false

    fun loadModules() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = repository.getModules()
            if (result.isSuccess) {
                _modulesResponse.value = result.getOrNull()
            } else {
                _error.value = result.exceptionOrNull()?.message ?: "Failed to load modules"
            }

            _isLoading.value = false
        }
    }

    fun toggleModule(moduleName: String) {
        viewModelScope.launch {
            val result = repository.toggleModule(moduleName, "toggle")
            if (result.isSuccess) {
                // Reload modules to update the UI
                loadModules()
            } else {
                _error.value = result.exceptionOrNull()?.message ?: "Failed to toggle module"
            }
        }
    }

    fun enableModule(moduleName: String) {
        viewModelScope.launch {
            val result = repository.toggleModule(moduleName, "enable")
            if (result.isSuccess) {
                loadModules()
            } else {
                _error.value = result.exceptionOrNull()?.message ?: "Failed to enable module"
            }
        }
    }

    fun disableModule(moduleName: String) {
        viewModelScope.launch {
            val result = repository.toggleModule(moduleName, "disable")
            if (result.isSuccess) {
                loadModules()
            } else {
                _error.value = result.exceptionOrNull()?.message ?: "Failed to disable module"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun startAutoRefresh() {
        if (autoRefreshEnabled) return
        autoRefreshEnabled = true

        viewModelScope.launch {
            while (autoRefreshEnabled) {
                val settings = settingsPreferences.settings.first()
                val interval = settings.refreshInterval

                delay(interval * 1000L)

                if (autoRefreshEnabled) {
                    loadModules()
                }
            }
        }
    }

    fun stopAutoRefresh() {
        autoRefreshEnabled = false
    }
}
