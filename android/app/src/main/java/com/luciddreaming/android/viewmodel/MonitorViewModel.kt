package com.luciddreaming.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luciddreaming.android.data.model.GameInfo
import com.luciddreaming.android.data.repository.GameInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MonitorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GameInfoRepository()

    private val _gameInfo = MutableStateFlow<GameInfo?>(null)
    val gameInfo: StateFlow<GameInfo?> = _gameInfo.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _screenshot = MutableStateFlow<ByteArray?>(null)
    val screenshot: StateFlow<ByteArray?> = _screenshot.asStateFlow()

    fun loadGameInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = repository.getGameInfo()
            if (result.isSuccess) {
                _gameInfo.value = result.getOrNull()
            } else {
                _error.value = result.exceptionOrNull()?.message ?: "Failed to load game info"
            }

            _isLoading.value = false
        }
    }

    fun loadScreenshot() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = repository.getScreenshot()
            if (result.isSuccess) {
                _screenshot.value = result.getOrNull()
            }

            _isLoading.value = false
        }
    }

    fun clearError() {
        _error.value = null
    }
}