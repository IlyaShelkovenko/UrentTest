package org.example.urent_test.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.delay
import org.example.urent_test.core.network.dto.CitiesResponseDto

class UrentDevApi(
    private val client: HttpClient
) {

    suspend fun getCities(
        query: String? = null,
        page: Int = 1,
        limit: Int = 20
    ): CitiesResponseDto {
        delay(10000)
        return client.get(CITIES_ENDPOINT) {
            parameter(PARAM_QUERY, query)
            parameter(PARAM_PAGE, page)
            parameter(PARAM_LIMIT, limit)
        }.body()
    }

    companion object {
        const val CITIES_ENDPOINT = "cities"
        const val PARAM_QUERY = "query"
        const val PARAM_PAGE = "page"
        const val PARAM_LIMIT = "limit"
    }
}
