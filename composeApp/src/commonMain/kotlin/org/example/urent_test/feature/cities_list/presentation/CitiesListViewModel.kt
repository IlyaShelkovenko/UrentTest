package org.example.urent_test.feature.cities_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.urent_test.feature.cities_list.domain.CitiesRepository
import org.example.urent_test.feature.cities_list.domain.City
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.Syntax
import org.orbitmvi.orbit.viewmodel.container

class CitiesListViewModel(
    private val citiesRepository: CitiesRepository
) : ViewModel(), ContainerHost<CitiesListState, CitiesListSideEffect> {

    private var searchJob: Job? = null

    override val container: Container<CitiesListState, CitiesListSideEffect> = container(
        initialState = CitiesListState(),
        onCreate = { loadCities() }
    )

    fun onAction(action: CitiesListAction) {
        when (action) {
            is CitiesListAction.OnQueryChange -> updateQuery(action.query)
            is CitiesListAction.OnCityClick -> navigateToCity(action.city)
            CitiesListAction.OnLoadNextPage -> loadNextPageIfNeeded()
            CitiesListAction.OnRetryNextPageClick -> retryNextPage()
            CitiesListAction.OnRetryClick -> refresh()
        }
    }

    private fun updateQuery(query: String) = intent {
        reduce {
            state.copy(
                query = query,
                cities = emptyList(),
                currentPage = CitiesListState.FIRST_PAGE,
                totalCount = 0,
                hasMore = true,
                errorMessage = null,
                paginationErrorMessage = null,
                isLoading = true,
                isLoadingNextPage = false
            )
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_MILLIS)
            intent {
                loadCities(page = CitiesListState.FIRST_PAGE, append = false)
            }
        }
    }

    private fun refresh() = intent {
        searchJob?.cancel()
        reduce {
            state.copy(
                cities = emptyList(),
                currentPage = CitiesListState.FIRST_PAGE,
                totalCount = 0,
                hasMore = true,
                errorMessage = null,
                paginationErrorMessage = null
            )
        }
        loadCities(page = CitiesListState.FIRST_PAGE, append = false)
    }

    private fun navigateToCity(city: City) = intent {
        postSideEffect(CitiesListSideEffect.NavigateToCity(city))
    }

    private fun loadNextPageIfNeeded() = intent {
        val shouldLoadNextPage = state.hasMore &&
            !state.isLoading &&
            !state.isLoadingNextPage &&
            state.paginationErrorMessage == null

        if (!shouldLoadNextPage) return@intent

        loadCities(page = state.currentPage + 1, append = true)
    }

    private fun retryNextPage() = intent {
        val shouldRetryNextPage = state.hasMore &&
            !state.isLoading &&
            !state.isLoadingNextPage

        if (!shouldRetryNextPage) return@intent

        loadCities(page = state.currentPage + 1, append = true)
    }

    private suspend fun Syntax<CitiesListState, CitiesListSideEffect>.loadCities(
        page: Int = CitiesListState.FIRST_PAGE,
        append: Boolean = false
    ) {
        reduce {
            if (append) {
                state.copy(
                    isLoadingNextPage = true,
                    paginationErrorMessage = null
                )
            } else {
                state.copy(
                    isLoading = true,
                    isLoadingNextPage = false,
                    errorMessage = null,
                    paginationErrorMessage = null
                )
            }
        }

        runCatching {
            citiesRepository.getCities(
                query = state.query.takeIf { it.isNotBlank() },
                page = page,
                limit = state.pageSize
            )
        }.onSuccess { response ->
            reduce {
                val newCities = if (append) {
                    state.cities + response.cities
                } else {
                    response.cities
                }

                state.copy(
                    cities = newCities,
                    isLoading = false,
                    isLoadingNextPage = false,
                    currentPage = page,
                    totalCount = response.total,
                    hasMore = newCities.size < response.total,
                    errorMessage = null,
                    paginationErrorMessage = null
                )
            }
        }.onFailure { error ->
            reduce {
                if (append) {
                    state.copy(
                        isLoadingNextPage = false,
                        paginationErrorMessage = error.message
                    )
                } else {
                    state.copy(
                        isLoading = false,
                        isLoadingNextPage = false,
                        errorMessage = error.message,
                        paginationErrorMessage = null
                    )
                }
            }
        }
    }

    private companion object {
        const val SEARCH_DEBOUNCE_MILLIS = 500L
    }
}
