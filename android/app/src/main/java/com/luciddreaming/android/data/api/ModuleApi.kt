package com.luciddreaming.android.data.api

import com.luciddreaming.android.data.model.Module
import com.luciddreaming.android.data.model.ModuleResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ModuleApi {
    @GET("api/modules")
    suspend fun getModules(): Response<ModuleResponse>

    @POST("api/modules/{moduleName}")
    suspend fun toggleModule(
        @Path("moduleName") moduleName: String,
        @Body body: RequestBody
    ): Response<Module>
}