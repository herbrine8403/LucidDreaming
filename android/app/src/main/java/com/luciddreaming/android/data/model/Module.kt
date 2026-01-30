package com.luciddreaming.android.data.model

import com.google.gson.annotations.SerializedName

data class ModuleResponse(
    @SerializedName("modules")
    val modules: List<Module>,

    @SerializedName("categories")
    val categories: List<String>
)

data class Module(
    @SerializedName("name")
    val name: String,

    @SerializedName("localizedName")
    val localizedName: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("localizedDescription")
    val localizedDescription: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("localizedCategory")
    val localizedCategory: String,

    @SerializedName("enabled")
    val enabled: Boolean,

    @SerializedName("keybind")
    val keybind: String
)