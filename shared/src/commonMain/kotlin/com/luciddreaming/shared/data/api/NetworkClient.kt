package com.luciddreaming.shared.data.api

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NetworkClient {
    companion object {
        private var baseUrl: String = "http://localhost:8080"

        fun createClient(): HttpClient {
            return HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    })
                }

                install(Logging) {
                    level = LogLevel.INFO
                }

                install(HttpTimeout) {
                    requestTimeoutMillis = 30000
                    connectTimeoutMillis = 10000
                }

                defaultRequest {
                    url(baseUrl)
                }
            }
        }

        fun updateBaseUrl(newBaseUrl: String) {
            baseUrl = newBaseUrl
        }

        fun getBaseUrl(): String {
            return baseUrl
        }
    }
}
