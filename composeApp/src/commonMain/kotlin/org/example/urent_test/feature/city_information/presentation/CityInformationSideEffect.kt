package org.example.urent_test.feature.city_information.presentation

sealed interface CityInformationSideEffect {
    data class OpenBrowser(val url: String) : CityInformationSideEffect
}
