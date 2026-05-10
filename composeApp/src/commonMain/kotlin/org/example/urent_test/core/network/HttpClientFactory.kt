package org.example.urent_test.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(baseUrl: String): HttpClient {

    return HttpClient(platformHttpEngine()) {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        defaultRequest {
            url(baseUrl)

            contentType(ContentType.Application.Json)

            headers {
                append(HttpHeaders.Accept, "application/json")
            }
        }
    }
}