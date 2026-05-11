package org.example.urent_test.feature.cities_list.presentation

import org.example.urent_test.feature.cities_list.domain.City

data class CitiesListState(
    val query: String = "",
    val cities: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val currentPage: Int = FIRST_PAGE,
    val pageSize: Int = PAGE_SIZE,
    val totalCount: Int = 0,
    val hasMore: Boolean = true,
    val errorMessage: String? = null,
    val paginationErrorMessage: String? = null
) {
    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
        const val PAGINATION_PREFETCH_DISTANCE = 5
    }
}
