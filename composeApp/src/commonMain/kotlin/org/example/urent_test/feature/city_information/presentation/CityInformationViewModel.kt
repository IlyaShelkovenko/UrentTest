package org.example.urent_test.feature.city_information.presentation

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class CityInformationViewModel(
    cityParams: CityParams
) : ViewModel(), ContainerHost<CityInformationState, CityInformationSideEffect> {

    override val container: Container<CityInformationState, CityInformationSideEffect> = container(
        initialState = CityInformationState(cityParams)
    )

    fun onAction(action: CityInformationAction) {
        when (action) {
            CityInformationAction.OnSearchInformationClick -> openCitySearch()
        }
    }

    private fun openCitySearch() = intent {
        val cityName = state.cityParams.name
        postSideEffect(
            CityInformationSideEffect.OpenBrowser(
                url = GOOGLE_SEARCH_URL + cityName.encodeUrlQuery()
            )
        )
    }

    private fun String.encodeUrlQuery(): String {
        return encodeToByteArray().joinToString(separator = "") { byte ->
            val value = byte.toInt() and 0xFF
            val char = value.toChar()
            when {
                char.isLetterOrDigit() -> char.toString()
                char == ' ' -> "+"
                char in URL_SAFE_QUERY_CHARS -> char.toString()
                else -> "%${value.toString(16).uppercase().padStart(2, '0')}"
            }
        }
    }

    companion object {
        private const val GOOGLE_SEARCH_URL = "https://www.google.com/search?q="
        private val URL_SAFE_QUERY_CHARS = setOf('-', '.', '_', '~')
    }
}
