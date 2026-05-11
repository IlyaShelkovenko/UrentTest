package org.example.urent_test.feature.cities_list.domain

interface CitiesRepository {

    suspend fun getCities(
        query: String? = null,
        page: Int = 1,
        limit: Int = 20
    ) : CitiesDomain
}