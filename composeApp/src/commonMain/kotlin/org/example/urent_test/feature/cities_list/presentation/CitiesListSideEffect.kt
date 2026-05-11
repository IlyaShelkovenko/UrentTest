package org.example.urent_test.feature.cities_list.presentation

import org.example.urent_test.feature.cities_list.domain.City

sealed interface CitiesListSideEffect {
    data class NavigateToCity(val city: City) : CitiesListSideEffect
}
