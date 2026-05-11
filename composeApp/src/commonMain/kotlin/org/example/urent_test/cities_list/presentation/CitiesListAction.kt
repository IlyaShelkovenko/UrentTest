package org.example.urent_test.cities_list.presentation

import org.example.urent_test.cities_list.domain.City

sealed interface CitiesListAction {
    data class OnQueryChange(val query: String) : CitiesListAction
    data class OnCityClick(val city: City) : CitiesListAction
    data object OnRetryClick : CitiesListAction
}
