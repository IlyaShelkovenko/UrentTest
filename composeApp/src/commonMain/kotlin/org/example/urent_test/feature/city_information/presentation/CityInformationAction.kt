package org.example.urent_test.feature.city_information.presentation

sealed interface CityInformationAction {
    data object OnSearchInformationClick : CityInformationAction
}
