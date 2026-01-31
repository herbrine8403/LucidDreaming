package com.luciddreaming.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Module(
    val name: String,
    val displayName: String,
    val description: String,
    val enabled: Boolean,
    val category: String,
    val version: String
)

@Serializable
data class ModuleResponse(
    val modules: List<Module>,
    val total: Int,
    val enabled: Int
)

@Serializable
data class ToggleModuleRequest(
    val action: String
)
