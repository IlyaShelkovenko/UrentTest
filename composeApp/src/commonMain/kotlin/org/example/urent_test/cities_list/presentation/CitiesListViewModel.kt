package org.example.urent_test.cities_list.presentation

import androidx.lifecycle.ViewModel
import org.example.urent_test.cities_list.domain.CitiesRepository
import org.example.urent_test.cities_list.domain.City
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.Syntax
import org.orbitmvi.orbit.viewmodel.container

class CitiesListViewModel(
    private val citiesRepository: CitiesRepository
) : ViewModel(), ContainerHost<CitiesListState, CitiesListSideEffect> {

    override val container: Container<CitiesListState, CitiesListSideEffect> = container(
        initialState = CitiesListState(),
        onCreate = { loadCities() }
    )

    fun onAction(action: CitiesListAction) {
        when (action) {
            is CitiesListAction.OnQueryChange -> updateQuery(action.query)
            is CitiesListAction.OnCityClick -> navigateToCity(action.city)
            CitiesListAction.OnRetryClick -> refresh()
        }
    }

    private fun updateQuery(query: String) = intent {
        reduce {
            state.copy(query = query)
        }
        loadCities()
    }

    private fun refresh() = intent {
        loadCities()
    }

    private fun navigateToCity(city: City) = intent {
        postSideEffect(CitiesListSideEffect.NavigateToCity(city))
    }

    private suspend fun Syntax<CitiesListState, CitiesListSideEffect>.loadCities() {
        reduce {
            state.copy(isLoading = true, errorMessage = null)
        }

        runCatching {
            citiesRepository.getCities(
                query = state.query.takeIf { it.isNotBlank() }
            )
        }.onSuccess { response ->
            reduce {
                state.copy(
                    cities = response.cities,
                    isLoading = false,
                    errorMessage = null
                )
            }
        }.onFailure { error ->
            reduce {
                state.copy(
                    isLoading = false,
                    errorMessage = error.message
                )
            }
        }
    }
}
