package org.example.urent_test.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object CitiesList: Route

    @Serializable
    data class CityInformation(
        val name: String,
        val country: String,
        val population: String
    ): Route
}