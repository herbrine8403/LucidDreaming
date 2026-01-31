package com.luciddreaming.shared.data.preferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import platform.Foundation.NSUserDefaults

class iOSConnectionPreferences : ConnectionPreferences {
    private val userDefaults = NSUserDefaults.standardUserDefaults
    private val _connectionSettings = MutableStateFlow(
        ConnectionSettings(
            isConnected = userDefaults.boolForKey("is_connected"),
            ipAddress = userDefaults.stringForKey("ip_address") ?: "",
            port = userDefaults.stringForKey("port") ?: ""
        )
    )

    override val connectionSettings: StateFlow<ConnectionSettings> = _connectionSettings

    override suspend fun updateConnectionSettings(
        isConnected: Boolean,
        ipAddress: String,
        port: String
    ) {
        userDefaults.setBool(isConnected, "is_connected")
        userDefaults.setObject(ipAddress, "ip_address")
        userDefaults.setObject(port, "port")
        userDefaults.synchronize()
        _connectionSettings.value = ConnectionSettings(isConnected, ipAddress, port)
    }

    override suspend fun clearConnectionSettings() {
        userDefaults.removeObjectForKey("is_connected")
        userDefaults.removeObjectForKey("ip_address")
        userDefaults.removeObjectForKey("port")
        userDefaults.synchronize()
        _connectionSettings.value = ConnectionSettings()
    }
}
