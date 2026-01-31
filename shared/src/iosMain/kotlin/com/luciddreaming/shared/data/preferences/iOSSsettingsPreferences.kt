package com.luciddreaming.shared.data.preferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import platform.Foundation.NSUserDefaults

class iOSSsettingsPreferences : SettingsPreferences {
    private val userDefaults = NSUserDefaults.standardUserDefaults
    private val _settings = MutableStateFlow(
        AppSettings(
            refreshInterval = userDefaults.integerForKey("refresh_interval").takeIf { it > 0 } ?: 10,
            autoRefresh = userDefaults.boolForKey("auto_refresh"),
            darkMode = userDefaults.boolForKey("dark_mode")
        )
    )

    override val settings: StateFlow<AppSettings> = _settings

    override suspend fun updateSettings(
        refreshInterval: Int,
        autoRefresh: Boolean,
        darkMode: Boolean
    ) {
        userDefaults.setInteger(refreshInterval.toLong(), "refresh_interval")
        userDefaults.setBool(autoRefresh, "auto_refresh")
        userDefaults.setBool(darkMode, "dark_mode")
        userDefaults.synchronize()
        _settings.value = AppSettings(refreshInterval, autoRefresh, darkMode)
    }

    override suspend fun resetSettings() {
        userDefaults.removeObjectForKey("refresh_interval")
        userDefaults.removeObjectForKey("auto_refresh")
        userDefaults.removeObjectForKey("dark_mode")
        userDefaults.synchronize()
        _settings.value = AppSettings()
    }
}
