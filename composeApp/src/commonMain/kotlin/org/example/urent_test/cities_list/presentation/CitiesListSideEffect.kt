package org.example.urent_test.cities_list.presentation

import org.example.urent_test.cities_list.domain.City

sealed interface CitiesListSideEffect {
    data class NavigateToCity(val city: City) : CitiesListSideEffect
}
