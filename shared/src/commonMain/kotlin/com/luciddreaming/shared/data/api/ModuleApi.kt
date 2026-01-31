package com.luciddreaming.shared.data.api

import com.luciddreaming.shared.data.model.Module
import com.luciddreaming.shared.data.model.ModuleResponse
import com.luciddreaming.shared.data.model.ToggleModuleRequest
import io.ktor.client.*
import io.ktor.client.request.*

class ModuleApi(private val client: HttpClient) {
    suspend fun getModules(): ModuleResponse {
        return client.get("/api/modules")
    }

    suspend fun toggleModule(moduleName: String, action: String): Module {
        return client.post("/api/modules/$moduleName/toggle") {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(ToggleModuleRequest(action))
        }
    }
}
