package com.luciddreaming.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luciddreaming.android.data.model.Module
import com.luciddreaming.android.data.model.ModuleResponse
import com.luciddreaming.android.data.repository.GameInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ModulesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GameInfoRepository()

    private val _modulesResponse = MutableStateFlow<ModuleResponse?>(null)
    val modulesResponse: StateFlow<ModuleResponse?> = _modulesResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

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
}