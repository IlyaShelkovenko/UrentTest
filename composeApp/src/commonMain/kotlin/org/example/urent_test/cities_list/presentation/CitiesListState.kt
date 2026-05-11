package org.example.urent_test.cities_list.presentation

import org.example.urent_test.cities_list.domain.City

data class CitiesListState(
    val query: String = "",
    val cities: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
