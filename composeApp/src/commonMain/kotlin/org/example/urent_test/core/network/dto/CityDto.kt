package org.example.urent_test.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    val id: Long,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val pop: Long
)