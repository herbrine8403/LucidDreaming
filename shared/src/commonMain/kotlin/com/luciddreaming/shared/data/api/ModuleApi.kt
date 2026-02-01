package com.luciddreaming.shared.data.api

import com.luciddreaming.shared.data.model.Module
import com.luciddreaming.shared.data.model.ModuleResponse
import com.luciddreaming.shared.data.model.ToggleModuleRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType

class ModuleApi(private val client: HttpClient) {
    suspend fun getModules(): ModuleResponse {
        return client.get("/api/modules").body()
    }

    suspend fun toggleModule(moduleName: String, action: String): Module {
        return client.post("/api/modules/$moduleName/toggle") {
            contentType(ContentType.Application.Json)
            setBody(ToggleModuleRequest(action))
        }.body()
    }
}
