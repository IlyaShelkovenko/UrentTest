package org.example.urent_test.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CitiesResponseDto(
    val items: List<CityDto>,
    val limit: Int,
    val page: Int,
    val total: Int
)
